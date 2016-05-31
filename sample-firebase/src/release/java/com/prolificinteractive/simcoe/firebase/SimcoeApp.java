package com.prolificinteractive.simcoe;

import android.app.Application;

public class SimcoeApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    AnalyticsHelperImpl.builder().build();
    AnalyticsHelperImpl.getInstance().initYourEventHandler(this);
  }
}
