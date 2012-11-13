package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.MatrixExecutionActivity;
import com.numerical_analysis.android.adapters.DirectMethodsMatrixExecutionAdapter;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LUMatrixFactorizationWithPartialPivoting extends Activity {

	private DirectMethods directMethods;
	private LinearLayout linearLayout;
	private Matrix matrix;
	private final String ACTIVITY_NAME = "lUMatrixFactorizationWithPartialPivoting";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lumatrix_factorization_with_partial_pivoting);
		directMethods = (DirectMethods) getIntent().getSerializableExtra(
				"directMethods");
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutResultActivityLUMatrixFactorizationWithPartialPivoting);
		matrix = (Matrix) getIntent().getSerializableExtra("Matrix");
		linearLayout.removeAllViewsInLayout();
		double[] x = directMethods.lUMatrixFactorizationWithPartialPivoting(
				matrix, matrix.getB());
		TextView[] results = new TextView[x.length];
		int[] marks = matrix.getMarks();
		for (int i = 0; i < x.length; i++) {
			TextView textView = new TextView(this);
			textView.setText("x" + marks[i] + " = " + x[i]);
			results[i] = textView;
			linearLayout.addView(textView);
			linearLayout.invalidate();
		}
	}

	public void showMatrixExecutionActivityLUMatrixFactorizationWithPartialPivoting(
			View view) {
		DirectMethodsMatrixExecutionAdapter adapter = new DirectMethodsMatrixExecutionAdapter();
		Intent intent = new Intent(this, MatrixExecutionActivity.class);
		intent.putExtra("directMethods", directMethods);
		intent.putExtra("adapter", adapter);
		intent.putExtra("activityName", ACTIVITY_NAME);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(
				R.menu.activity_lumatrix_factorization_with_partial_pivoting,
				menu);
		return true;
	}
}
