package com.prolificinteractive.simcoe.firebase;

import android.app.Application;
import com.prolificinteractive.simcoe.api.Logger;
import timber.log.Timber;

public class SimcoeApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    Timber.plant(new Timber.DebugTree());

    AnalyticsHelperImpl.builder()
        .tag("Analytics: ")
        .logger(new Logger() {
          @Override public void log(String value) {
            Timber.e(value);
          }
        })
        .build();

    AnalyticsHelperImpl.edit()
        .tag("Something else: ")
        .build();

    AnalyticsHelperImpl.getInstance().initYourEventHandler(this);
  }
}
