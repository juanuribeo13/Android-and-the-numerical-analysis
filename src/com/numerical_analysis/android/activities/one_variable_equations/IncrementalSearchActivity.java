package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.adapters.one_variable_equations.IncrementalSearchExecutionTableAdapter;
import com.numerical_analysis.android.exceptions.RootFoundException;
import com.numerical_analysis.android.exceptions.RootNotFoundException;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IncrementalSearchActivity extends Activity {

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
