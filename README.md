# Simcoe

A light __Android__ analytics wrapped aimed at making analytics implementation and debugging as simple and
streamlined as possible -- especially for projects utilizing multiple analytics providers.

## Usage

Create an `AnalyticsHelper` class, then annotate this class with `@Analytics` annotation.

This will generate an `Impl` class that you can then use to call your methods.

Here is an example of an `AnalyticsHelper` class:

```java
@Analytics
public class AnalyticsHelper {

  @Ignore
  public void initYourEventHandler(final Context context) {
    MyAnalyticsTool.init(context);
  }

  public void trackSomething(final String name) {
    MyAnalyticsTool.track("name", name);
  }

  public void trackScreen(final String name) {
    MyAnalyticsTool.track("screen", name);
  }
}
```

Use the `@Ignore` annotation to prevent from generating a method.

Once the `Impl` class is generated, use the builder to create the instance in your application,
like this:
```java
public class SimcoeApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    AnalyticsHelperImpl.builder()
        .tag("Analytics: ")
        .logger(new Logger() {
          @Override public void log(String value) {
            Log.e(TAG, value);
          }
        })
        .build();

    AnalyticsHelperImpl.getInstance().initYourEventHandler(this);
  }
}
```

Finally, simply call your tracking method just like this:
```java
myButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        AnalyticsHelperImpl.getInstance().trackSomething("Button Clicked!");
      }
    });
```

Contributing
============

Would you like to contribute? Fork us and send a pull request! Be sure to checkout our issues first.

## License

Swipe Action Layout is Copyright (c) 2016 Prolific Interactive. It may be redistributed under the terms specified in the [LICENSE] file.

[LICENSE]: /LICENSE

## Maintainers

![prolific](https://s3.amazonaws.com/prolificsitestaging/logos/Prolific_Logo_Full_Color.png)

Swipe Action Layout is maintained and funded by Prolific Interactive. The names and logos are trademarks of Prolific Interactive.


