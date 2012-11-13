package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.InputMatrixActivity;
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
	private final String ACTIVITY_NAME = "gaussianElimination";
	private static final int INPUT_MATRIX = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gaussian_elimination);
		directMethods = (DirectMethods) getIntent().getSerializableExtra(
				"directMethods");
		linearLayout = (LinearLayout) findViewById(R.id.linearLayoutResultActivityGaussianElimination);
		matrix = (Matrix) getIntent().getSerializableExtra("Matrix");
		execution = (Button) findViewById(R.id.buttonExecutionActivityGaussianElimination);
		if (matrix == null) {
			setvisibilities(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_direct_methods, menu);
		return true;
	}

	public void showMatrixExecution(View view) {
		DirectMethodsMatrixExecutionAdapter adapter = new DirectMethodsMatrixExecutionAdapter();
		Intent intent = new Intent(this, MatrixExecutionActivity.class);
		intent.putExtra("directMethods", directMethods);
		intent.putExtra("adapter", adapter);
		intent.putExtra("activityName", ACTIVITY_NAME);
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

		case R.id.menu_input_matrix:
			Intent inputMatrix = new Intent(this, InputMatrixActivity.class);
			if (matrix != null) {
				inputMatrix.putExtra("Matrix", matrix);
			}
			startActivityForResult(inputMatrix, INPUT_MATRIX);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewResultActivityGaussianElimination)
				.setVisibility(visibility);
		findViewById(R.id.scrollViewResultAcitivityGaussianElimination)
				.setVisibility(visibility);
		findViewById(R.id.buttonExecutionActivityGaussianElimination)
				.setVisibility(visibility);
		findViewById(R.id.buttonTotalPivoting).setVisibility(visibility);
		findViewById(R.id.buttonPartialPivoting).setVisibility(visibility);
		findViewById(R.id.buttonSimpleGaussianElimination).setVisibility(
				visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityGaussianElimination)
					.setVisibility(View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityGaussianElimination)
					.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == INPUT_MATRIX) {
				setMatrix((Matrix) data.getSerializableExtra("Matrix"));
				setvisibilities(View.VISIBLE);
			}
		}
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
}
