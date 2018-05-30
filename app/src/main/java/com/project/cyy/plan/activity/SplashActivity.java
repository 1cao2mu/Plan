package com.project.cyy.plan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.project.cyy.plan.R;

/**
 * Created by cyy
 * on 18-5-29下午5:43
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
