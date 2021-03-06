package com.prolificinteractive.sample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.prolificinteractive.simcoe.EmptyProvider;
import com.prolificinteractive.simcoe.trackers.AnalyticsTracking;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final Set<AnalyticsTracking> set = new HashSet<>();
    set.add(new EmptyProvider());
    //set.add(new MParticleAnalyticsProvider());

    final SampleAnalyticsHelper sampleAnalyticsHelper = new SampleAnalyticsHelper(set);
    sampleAnalyticsHelper.start(this);

    sampleAnalyticsHelper.trackPageView("Main");

    setContentView(R.layout.activity_main);

    final View buttonEvent1 = findViewById(R.id.button_event_1);
    final View buttonEvent2 = findViewById(R.id.button_event_2);
    final View buttonEvent3 = findViewById(R.id.button_event_3);

    final Map map = new HashMap<>();

    map.put("Hey", "it's me");

    buttonEvent1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        sampleAnalyticsHelper.trackEvent("Event 1");
      }
    });
    buttonEvent2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        sampleAnalyticsHelper.trackEvent("Event 2");
      }
    });
    buttonEvent3.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View view) {
        sampleAnalyticsHelper.trackEvent("Event 3", map);
      }
    });
  }

  @Override public void onSaveInstanceState(
      final Bundle outState,
      final PersistableBundle outPersistentState) {
    super.onSaveInstanceState(outState, outPersistentState);
  }
}
