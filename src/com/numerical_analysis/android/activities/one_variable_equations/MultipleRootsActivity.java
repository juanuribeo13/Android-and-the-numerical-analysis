package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MultipleRootsActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_roots);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_multiple_roots, menu);
		return true;
	}
}
