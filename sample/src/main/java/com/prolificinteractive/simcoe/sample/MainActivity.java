package com.prolificinteractive.simcoe.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final AnalyticsHelper analyticsHelper = new AnalyticsHelper(null);
    analyticsHelper.start(this);

    analyticsHelper.trackEvent("Hello");
  }
}
