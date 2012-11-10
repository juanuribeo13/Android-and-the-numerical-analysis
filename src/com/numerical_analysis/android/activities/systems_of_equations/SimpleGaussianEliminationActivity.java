package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SimpleGaussianEliminationActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_gaussian_elimination);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_simple_gaussian_elimination, menu);
        return true;
    }
}
