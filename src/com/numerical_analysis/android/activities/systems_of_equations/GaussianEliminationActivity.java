package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.MatrixExecutionActivity;
import com.numerical_analysis.android.adapters.DirectMethodsMatrixExecutionAdapter;
import com.numerical_analysis.android.exceptions.NoUniqueSolutionException;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GaussianEliminationActivity extends Activity {

	private DirectMethods directMethods;
	private Matrix matrix;
	private LinearLayout linearLayout;
	private Button execution;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gaussian_elimination);
		directMethods = (DirectMethods) getIntent().getSerializableExtra(
				"directMethods");
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutResultActivitySimpleGaussianElimination);
		matrix = (Matrix) getIntent().getSerializableExtra("Matrix");
		execution = (Button) findViewById(R.id.buttonExecutionActivityGaussianElimination);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_simple_gaussian_elimination,
				menu);
		return true;
	}

	public void showMatrixExecution(View view) {
		DirectMethodsMatrixExecutionAdapter adapter = new DirectMethodsMatrixExecutionAdapter();
		Intent intent = new Intent(this, MatrixExecutionActivity.class);
		intent.putExtra("directMethods", directMethods);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	public void onPartialPivotingButtonClick(View view) {
		Matrix matrix = (Matrix) this.matrix.clone();
		linearLayout.removeAllViewsInLayout();
		try {
			double[] x = directMethods.gaussianEliminationWithPartialPivoting(
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
		} catch (NoUniqueSolutionException e) {
			TextView textView = new TextView(this);
			textView.setText(e.getMessage());
			linearLayout.addView(textView);
			linearLayout.invalidate();
		}
		execution.setVisibility(Button.VISIBLE);
	}

	public void onTotalPivotingButtonClick(View view) {
		Matrix matrix = (Matrix) this.matrix.clone();
		linearLayout.removeAllViewsInLayout();
		try {
			double[] x = directMethods.gaussianEliminationWithTotalPivoting(
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
		} catch (NoUniqueSolutionException e) {
			TextView textView = new TextView(this);
			textView.setText(e.getMessage());
			linearLayout.addView(textView);
			linearLayout.invalidate();
		}
		execution.setVisibility(Button.VISIBLE);
	}

	public void onSimpleGaussianEliminationButtonClick(View view) {
		Matrix matrix = (Matrix) this.matrix.clone();
		linearLayout.removeAllViewsInLayout();
		double[] x = directMethods.simpleGaussianElimination(matrix,
				matrix.getB());
		TextView[] results = new TextView[x.length];
		int[] marks = matrix.getMarks();
		for (int i = 0; i < x.length; i++) {
			TextView textView = new TextView(this);
			textView.setText("x" + marks[i] + " = " + x[i]);
			results[i] = textView;
			linearLayout.addView(textView);
			linearLayout.invalidate();
		}
		execution.setVisibility(Button.VISIBLE);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {
		}

		return super.onOptionsItemSelected(item);
	}
}
