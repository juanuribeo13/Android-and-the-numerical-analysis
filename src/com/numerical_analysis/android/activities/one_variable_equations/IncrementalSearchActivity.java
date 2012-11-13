package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.activities.HelpActivity;
import com.numerical_analysis.android.activities.PlotterActivity;
import com.numerical_analysis.android.activities.SetFunctionActivity;
import com.numerical_analysis.android.adapters.one_variable_equations.IncrementalSearchExecutionTableAdapter;
import com.numerical_analysis.android.exceptions.RootFoundException;
import com.numerical_analysis.android.exceptions.RootNotFoundException;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IncrementalSearchActivity extends Activity {

	static final int SET_FUNCTION = 0;
	private OneVariableEquations oneVariableEquations;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incremental_search);

		TextView function = (TextView) findViewById(R.id.textViewFunctionActivityIncrementalSearch);
		oneVariableEquations = (OneVariableEquations) getIntent()
				.getSerializableExtra("oneVariableEquations");
		function.setText(oneVariableEquations.getFunction());
		if (oneVariableEquations.getFunction() == null) {
			setvisibilities(View.GONE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_one_variable_equations, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		Intent intent;
		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menu_plot:
			intent = new Intent(this, PlotterActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivity(intent);
			return true;

		case R.id.menu_set_function:
			intent = new Intent(this, SetFunctionActivity.class);
			intent.putExtra("function", oneVariableEquations.getFunction());
			startActivityForResult(intent, SET_FUNCTION);
			return true;
		case R.id.menu_help:
			intent = new Intent(this, HelpActivity.class);
			intent.putExtra(
					"url",
					"https://sites.google.com/site/numericalanalysiseafit/topics/one-variable-equations/incremental-search");
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == SET_FUNCTION) {
				oneVariableEquations.setFunction(data
						.getStringExtra("Function"));
				setvisibilities(View.VISIBLE);
				TextView function = (TextView) findViewById(R.id.textViewFunctionActivityIncrementalSearch);
				function.setText(oneVariableEquations.getFunction());
			}
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("oneVariableEquations", oneVariableEquations);
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}

	/**
	 * Called when the user clicks on the Calculate button and calls the
	 * incremental search method on the OneVariableEquation class to find the
	 * interval
	 * 
	 * @param view
	 */
	public void onCalculateButtonClick(View view) {

		TextView textInterval = (TextView) findViewById(R.id.textViewIntervalActivityIncrementalSearch);
		try {
			// Get the values entered by the user
			double x0 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX0ActivityIncrementalSearch))
							.getText().toString());
			double delta = Double
					.parseDouble(((EditText) findViewById(R.id.editTextDeltaActivityIncrementalSearch))
							.getText().toString());
			int iterations = Integer
					.parseInt(((EditText) findViewById(R.id.editTextIterationsActivityIncrementalSearch))
							.getText().toString());

			// Show the interval
			double interval[] = oneVariableEquations.incrementalSearch(x0,
					delta, iterations);
			textInterval.setText("[" + interval[0] + ", " + interval[1] + "]");

			enableExecutionTable();

		} catch (NumberFormatException e) {
			Toast.makeText(this, getString(R.string.invalid_parameters),
					Toast.LENGTH_LONG).show();
		} catch (RootNotFoundException e) {
			textInterval.setText(e.getMessage());
			enableExecutionTable();
		} catch (RootFoundException e) {
			textInterval.setText(e.getMessage());
			enableExecutionTable();
		}
	}

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewFunctionActivityIncrementalSearch)
				.setVisibility(visibility);
		findViewById(R.id.editTextX0ActivityIncrementalSearch).setVisibility(
				visibility);
		findViewById(R.id.editTextDeltaActivityIncrementalSearch)
				.setVisibility(visibility);
		findViewById(R.id.editTextIterationsActivityIncrementalSearch)
				.setVisibility(visibility);
		findViewById(R.id.buttonCalculateActivityIncrementalSearch)
				.setVisibility(visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityIncrementalSearch)
					.setVisibility(View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityIncrementalSearch)
					.setVisibility(View.VISIBLE);
		}
	}

	public void showExecutionTable(View view) {
		IncrementalSearchExecutionTableAdapter adapter = new IncrementalSearchExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", oneVariableEquations);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityIncrementalSearch)
				.setVisibility(View.VISIBLE);
	}
}
