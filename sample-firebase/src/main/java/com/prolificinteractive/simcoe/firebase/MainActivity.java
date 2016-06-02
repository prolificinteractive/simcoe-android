package com.prolificinteractive.simcoe.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    AnalyticsHelperImpl.getInstance().trackSomething("Hello World");
    AnalyticsHelperImpl.getInstance().trackScreen("MainActivity");

    final Button button = (Button) findViewById(R.id.button);

    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        AnalyticsHelperImpl.getInstance().trackSomething(button.getText().toString());
      }
    });
  }
}
