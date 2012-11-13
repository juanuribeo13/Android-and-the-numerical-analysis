package com.numerical_analysis.android.activities.interpolation;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.R.layout;
import com.numerical_analysis.android.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewtonInterpolationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_interpolation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_newton_interpolation, menu);
        return true;
    }
}
