package com.prolificinteractive.simcoe;

import android.app.Application;
import com.prolificinteractive.simcoe.api.Logger;
import timber.log.Timber;

public class SimcoeApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    Timber.plant(new Timber.DebugTree());

    AnalyticsHelperImpl.builder()
        .setTag("Analytics: ")
        .setLogger(new Logger() {
          @Override public void log(String value) {
            Timber.e(value);
          }
        })
        .build();
  }
}
