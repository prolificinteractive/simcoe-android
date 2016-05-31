package com.prolificinteractive.simcoe.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.prolificinteractive.simcoe.api.Analytics;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    AnalyticsHelperImpl.getInstance().trackSomething("Hello World");
    AnalyticsHelperImpl.getInstance().trackScreen("MainActivity");
  }
}
