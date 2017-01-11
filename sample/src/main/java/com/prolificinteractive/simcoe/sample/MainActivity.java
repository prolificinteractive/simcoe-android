package com.prolificinteractive.simcoe.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.prolificinteractive.simcoe.mixpanel.MixpanelAnalyticsProvider;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final Set<AnalyticsTracking> set = new HashSet<>();
    set.add(new MixpanelAnalyticsProvider("<token>"));
    final AnalyticsHelper analyticsHelper = new AnalyticsHelper(set);
    analyticsHelper.start(this);

    analyticsHelper.trackEvent("Hello");
  }
}
