package com.prolificinteractive.simcoe.compiler;

import com.google.auto.service.AutoService;
import com.prolificinteractive.simcoe.api.Analytics;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import static javax.lang.model.SourceVersion.latestSupported;

@AutoService(Processor.class)
public class AnalyticsProcessor extends AbstractProcessor {
  private static final String SUFFIX = "Impl";

  private Types typeUtils;
  private Elements elementUtils;
  private Filer filer;
  private Messager messager;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    typeUtils = processingEnv.getTypeUtils();
    elementUtils = processingEnv.getElementUtils();
    filer = processingEnv.getFiler();
    messager = processingEnv.getMessager();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> annotations = new LinkedHashSet<>();
    annotations.add(Analytics.class.getCanonicalName());
    //annotations.add(Ignore.class.getCanonicalName());
    return annotations;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return latestSupported();
  }

  @Override public boolean process(Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv) {
    // Iterate over all @Analytics annotated elements
    Observable.from(roundEnv.getElementsAnnotatedWith(Analytics.class))
        // Check if an interface has been annotated with @ApiClient
        .filter(new Func1<Element, Boolean>() {
          @Override public Boolean call(Element annotatedElement) {
            return annotatedElement.getKind().isClass();
          }
        })
        .subscribe(new Action1<Element>() {
          @Override public void call(final Element annotatedElement) {
            final TypeElement typeElement = (TypeElement) annotatedElement;
            final String packageName =
                elementUtils.getPackageOf(typeElement).getQualifiedName().toString();

            // create a new public class that extends
            final TypeSpec.Builder builder =
                TypeSpec.classBuilder(typeElement.getSimpleName() + SUFFIX)
                    .addModifiers(Modifier.PUBLIC);

            // add all new methods to this class
            Observable
                .create(getMethodsFrom(annotatedElement))
                .subscribe(new Action1<MethodSpec>() {
                  @Override public void call(MethodSpec methodSpec) {
                    builder.addMethod(methodSpec);
                  }
                });

            // Write file
            try {
              JavaFile.builder(packageName, builder.build())
                  .build()
                  .writeTo(filer);
            } catch (final IOException e) {
              e.printStackTrace();
            }
          }
        });

    return false;
  }

  private Observable.OnSubscribe<MethodSpec> getMethodsFrom(final Element annotatedElement) {
    return new Observable.OnSubscribe<MethodSpec>() {
      @Override public void call(final Subscriber<? super MethodSpec> subscriber) {
        Observable.from(annotatedElement.getEnclosedElements())
            .subscribe(new Action1<Element>() {
              @Override public void call(Element e) {
                try {
                  subscriber.onNext(buildMethod((ExecutableElement) e));
                } catch (final ClassCastException ignored) {
                  //ignoring TypeElement and VariableElement
                }
              }
            });
        subscriber.onCompleted();
      }
    };
  }

  // return the client's method from the interface's method
  private MethodSpec buildMethod(final ExecutableElement method) {

    final String methodName = method.getSimpleName().toString();

    final MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName)
        .addModifiers(Modifier.PUBLIC)
        .addStatement(getFormattedStatement(method));

    Observable.from(method.getParameters())
        .subscribe(new Action1<VariableElement>() {
          @Override public void call(VariableElement ve) {
            builder.addParameter(
                TypeName.get(ve.asType()),
                ve.getSimpleName().toString(),
                Modifier.FINAL
            );
          }
        });

    return builder.build();
  }

  private String getFormattedStatement(final ExecutableElement method) {
    final String params = joinMethodParameters(method.getParameters());
    return String.format("super.%s(%s);", method.getSimpleName().toString(),
        params);
  }

  private String joinMethodParameters(List<? extends VariableElement> params) {
    final StringBuilder stringBuilder = new StringBuilder();

    if (!params.isEmpty()) { stringBuilder.append(params.get(0)); }

    for (int i = 1; i < params.size(); i++) {
      stringBuilder.append(", ").append(params.get(i).getSimpleName());
    }

    return stringBuilder.toString();
  }
}
