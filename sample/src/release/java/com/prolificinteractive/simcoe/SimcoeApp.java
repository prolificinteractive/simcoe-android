package com.prolificinteractive.simcoe;

import android.app.Application;

public class SimcoeApp extends Application {

  private AnalyticsHelper helper;

  @Override public void onCreate() {
    super.onCreate();

    helper = new AnalyticsHelper();
    helper.initYourEventHandler(this);
  }

  public AnalyticsHelper getHelper() {
    return helper;
  }
}
