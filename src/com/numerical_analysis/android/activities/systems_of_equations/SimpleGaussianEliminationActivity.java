package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleGaussianEliminationActivity extends Activity {

	private DirectMethods directMethods;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_gaussian_elimination);
		directMethods = (DirectMethods) getIntent().getSerializableExtra(
				"directMethods");
		Matrix matrix = (Matrix) getIntent().getSerializableExtra("Matrix");
		LinearLayout linearLayoutResult = (LinearLayout) findViewById(R.id.linearLayoutResultActivitySimpleGaussianElimination);
		TextView[] results=new TextView[matrix.getRows()];
		double[] x=directMethods.simpleGaussianElimination(matrix, matrix.getB());
		for (int i = 0; i < x.length; i++) {
			TextView textView=new TextView(this);
			textView.setText(x[i]+"");
			results[i]=textView;
			linearLayoutResult.addView(textView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_simple_gaussian_elimination,
				menu);
		return true;
	}
}
