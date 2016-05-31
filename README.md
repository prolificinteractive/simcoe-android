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

You can also modify the HelperImpl class parameters on the fly using the static `edit()` method. `edit()` will create a new `Builder` using the current parameters of the Helper class.
For example, you can simply enable/disable the log from a debug drawer or, give a different tag name for easier debugging.
```java
AnalyticsHelperImpl.edit()
    .tag("Something else: ")
    .build();
```

### TODO

* Toast using a Toaster the same way we do log with Logger(?)
* More dev tools!!! (Feed your ideas)


Contributing
============

Would you like to contribute? Fork us and send a pull request! Be sure to checkout our issues first.

## License

Simcoe is Copyright (c) 2016 Prolific Interactive. It may be redistributed under the terms specified in the [LICENSE] file.

[LICENSE]: /LICENSE

## Maintainers

![prolific](https://s3.amazonaws.com/prolificsitestaging/logos/Prolific_Logo_Full_Color.png)

Simcoe is maintained and funded by Prolific Interactive. The names and logos are trademarks of Prolific Interactive.


