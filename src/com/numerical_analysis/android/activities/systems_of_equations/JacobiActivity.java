package com.numerical_analysis.android.activities.systems_of_equations;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.activities.HelpActivity;
import com.numerical_analysis.android.activities.SetIndependentTermsActivity;
import com.numerical_analysis.android.adapters.IterativeMethodsExecutionTableAdapter;
import com.numerical_analysis.android.exceptions.MaximumNumberOfIterationsExceededExeption;
import com.numerical_analysis.android.methods.systems_of_equations.IterativeMethods;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JacobiActivity extends Activity {

	private IterativeMethods iterativeMethods;
	static final int SET_INDEPENDENT_TERMS = 0;
	int position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jacobi);

		iterativeMethods = (IterativeMethods) getIntent().getSerializableExtra(
				"iterativeMethods");

		position = 0;
		TextView viewValue = (TextView) findViewById(R.id.textViewValueToEnterActivityJacobi);
		viewValue.setText("Please insert x" + (position + 1));
		if (iterativeMethods.getIndependentTerms() == null) {
			setvisibilities(View.GONE);
		} else {
			Double[] initialValues = new Double[iterativeMethods
					.getIndependentTerms().length];
			iterativeMethods.setInitialValues(initialValues);
		}
		findViewById(R.id.buttonPreviousActivityJacobi).setVisibility(
				View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_iterative_methods, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menu_set_independent_terms:
			Intent intent = new Intent(this, SetIndependentTermsActivity.class);
			intent.putExtra("independentTerms",
					iterativeMethods.getIndependentTerms());
			startActivityForResult(intent, SET_INDEPENDENT_TERMS);
			return true;
		case R.id.menu_help:
			intent = new Intent(this, HelpActivity.class);
			intent.putExtra(
					"url",
					"https://sites.google.com/site/numericalanalysiseafit/topics/systems-of-equations/iterative-methods/jacobi-method");
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == SET_INDEPENDENT_TERMS) {
				iterativeMethods.setIndependentTerms(data
						.getStringArrayExtra("independentTerms"));
				Double[] initialValues = new Double[iterativeMethods
						.getIndependentTerms().length];
				iterativeMethods.setInitialValues(initialValues);
				setvisibilities(View.VISIBLE);
				findViewById(R.id.buttonPreviousActivityJacobi).setVisibility(
						View.INVISIBLE);
			}
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("iterativeMethods", iterativeMethods);
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}

	public void onPreviousButtonClick(View view) {
		// In case that the next button where invisible
		findViewById(R.id.buttonNextActivityJacobi).setVisibility(View.VISIBLE);

		Double[] initialValues = iterativeMethods.getInitialValues();
		EditText editValue = (EditText) findViewById(R.id.editTextValueActivityJacobi);
		initialValues[position] = Double
				.valueOf(editValue.getText().toString());
		iterativeMethods.setInitialValues(initialValues);
		position--;

		TextView viewValue = (TextView) findViewById(R.id.textViewValueToEnterActivityJacobi);
		viewValue.setText("Please insert x" + (position + 1));

		if (initialValues[position] != null) {
			editValue.setText(initialValues[position].toString());
		}
		if (position == 0) {
			findViewById(R.id.buttonPreviousActivityJacobi).setVisibility(
					View.INVISIBLE);
		}
	}

	public void onNextButtonClick(View view) {
		// In case that the previous button where invisible
		findViewById(R.id.buttonPreviousActivityJacobi).setVisibility(
				View.VISIBLE);

		Double[] initialValues = iterativeMethods.getInitialValues();
		EditText editValue = (EditText) findViewById(R.id.editTextValueActivityJacobi);
		initialValues[position] = Double
				.valueOf(editValue.getText().toString());
		iterativeMethods.setInitialValues(initialValues);
		position++;

		TextView viewValue = (TextView) findViewById(R.id.textViewValueToEnterActivityJacobi);
		viewValue.setText("Please insert x" + (position + 1));

		if (initialValues[position] != null) {
			editValue.setText(initialValues[position].toString());
		}
		if (position == initialValues.length - 1) {
			findViewById(R.id.buttonNextActivityJacobi).setVisibility(
					View.INVISIBLE);
		}
	}

	public void onCalculateButtonClick(View view) {
		LinearLayout layoutResults = (LinearLayout) findViewById(R.id.linearLayoutResultsActivityJacobi);
		layoutResults.removeAllViews();

		try {
			int iterations = Integer
					.parseInt(((EditText) findViewById(R.id.editTextIterationsActivityJacobi))
							.getText().toString());
			double tolerance = Double
					.parseDouble(((EditText) findViewById(R.id.editTextToleranceActivityJacobi))
							.getText().toString());
			double lambda = Double
					.parseDouble(((EditText) findViewById(R.id.editTextRelaxationActivityJacobi))
							.getText().toString());
			ArrayList<Double> results = iterativeMethods.jacobi(lambda,
					iterations, tolerance);

			for (int i = 0; i < results.size() - 1; i++) {
				Double result = results.get(i);
				TextView textResult = new TextView(this);
				textResult.setText("X" + (i + 1) + " = " + result);
				layoutResults.addView(textResult);
			}
			NumberFormat formatter = new DecimalFormat("0.##E0");
			TextView textError = new TextView(this);
			textError.setText("Error = "
					+ formatter.format(results.get(results.size() - 1)));
			layoutResults.addView(textError);
			enableExecutionTable();
		} catch (NumberFormatException e) {
			Toast.makeText(this, getString(R.string.invalid_parameters),
					Toast.LENGTH_LONG).show();
		} catch (MaximumNumberOfIterationsExceededExeption e) {
			enableExecutionTable();
			TextView textResult = new TextView(this);
			textResult.setText(e.getMessage());
			layoutResults.addView(textResult);
		}
	}

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewValueToEnterActivityJacobi).setVisibility(
				visibility);
		findViewById(R.id.editTextValueActivityJacobi)
				.setVisibility(visibility);
		findViewById(R.id.buttonNextActivityJacobi).setVisibility(visibility);
		findViewById(R.id.buttonPreviousActivityJacobi).setVisibility(
				visibility);
		findViewById(R.id.editTextIterationsActivityJacobi).setVisibility(
				visibility);
		findViewById(R.id.editTextToleranceActivityJacobi).setVisibility(
				visibility);
		findViewById(R.id.editTextRelaxationActivityJacobi).setVisibility(
				visibility);
		findViewById(R.id.buttonCalculateActivityJacobi).setVisibility(
				visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityJacobi).setVisibility(
					View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityJacobi).setVisibility(
					View.VISIBLE);
		}
	}

	public void showExecutionTable(View view) {
		IterativeMethodsExecutionTableAdapter adapter = new IterativeMethodsExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", iterativeMethods);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityJacobi).setVisibility(
				View.VISIBLE);
	}
}
