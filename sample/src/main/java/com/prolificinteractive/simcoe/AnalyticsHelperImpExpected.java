package com.prolificinteractive.simcoe;

public class AnalyticsHelperImpExpected extends AnalyticsHelper {

  @Override public void trackSomething(String name) {
    log();
    super.trackSomething(name);
  }

  @Override public void trackAnotherThing(String name, String count) {
    log();
    super.trackAnotherThing(name, count);
  }

  @Override public void trackScreen(String name) {
    log();
    super.trackScreen(name);
  }
}
