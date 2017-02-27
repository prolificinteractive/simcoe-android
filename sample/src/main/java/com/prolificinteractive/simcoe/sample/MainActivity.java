package com.prolificinteractive.simcoe.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.prolificinteractive.simcoe.mixpanel.MixpanelAnalyticsProvider;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final Set<AnalyticsTracking> set = new HashSet<>();
    set.add(new MixpanelAnalyticsProvider("<token>"));

    final AnalyticsHelper analyticsHelper = new AnalyticsHelper(set);
    analyticsHelper.start(this);

    analyticsHelper.trackPageView("Main");

    setContentView(R.layout.activity_main);

    final View buttonEvent1 = findViewById(R.id.button_event_1);
    final View buttonEvent2 = findViewById(R.id.button_event_2);
    final View buttonEvent3 = findViewById(R.id.button_event_3);

    buttonEvent1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        analyticsHelper.trackEvent("Event 1");
      }
    });
    buttonEvent2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        analyticsHelper.trackEvent("Event 2");
      }
    });
    buttonEvent3.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        analyticsHelper.trackEvent("Event 3");
      }
    });
  }
}
