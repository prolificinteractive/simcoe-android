package com.prolificinteractive.simcoe.firebase;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.prolificinteractive.simcoe.api.Analytics;
import com.prolificinteractive.simcoe.api.Ignore;

@Analytics
public class AnalyticsHelper {
  public static final String SCREEN = "screen";

  private FirebaseAnalytics tracker;

  @Ignore
  public void initYourEventHandler(final Context context) {
    tracker = FirebaseAnalytics.getInstance(context);
  }

  public void trackSomething(final String name) {
    final Bundle bundle = new Bundle();
    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
    tracker.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
  }

  public void trackAnotherThing(final String name, final int count) {
    final Bundle bundle = new Bundle();
    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
    bundle.putLong(FirebaseAnalytics.Param.QUANTITY, transformCount(count));
    tracker.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
  }

  public void trackScreen(final String name) {
    final Bundle bundle = new Bundle();
    bundle.putString(SCREEN, name);
    tracker.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
  }

  @Ignore
  private int transformCount(final int count) {
    return count + 1;
  }
}
