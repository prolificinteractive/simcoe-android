package com.prolificinteractive.sample;

import android.app.Application;
import timber.log.Timber;

public class SampleApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
