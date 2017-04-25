package com.example.ilia.testactivity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView test;
    private long currentTime;
    private static final String TIME = "TIME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (TextView) findViewById(R.id.test);
        if (savedInstanceState == null) {

            currentTime = System.currentTimeMillis();
            test.setText(Long.toString(currentTime));
        } else {
            currentTime = savedInstanceState.getLong(TIME);
            test.setText(Long.toString(currentTime));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(TIME, currentTime);
    }
}
