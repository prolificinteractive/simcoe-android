package com.prolificinteractive.simcoe;

import android.content.Context;
import com.prolificinteractive.simcoe.api.Analytics;
import com.prolificinteractive.simcoe.api.Ignore;

@Analytics
public class AnalyticsHelper {

  @Ignore
  public void initYourEventHandler(final Context context) {
  }

  public void trackSomething(String name) {
  }

  public void trackAnotherThing(String name, String count) {
  }

  public void trackScreen(String name) {
  }
}
