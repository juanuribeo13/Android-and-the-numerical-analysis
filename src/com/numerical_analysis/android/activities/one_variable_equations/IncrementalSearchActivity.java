package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.exceptions.RootNotFounException;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.app.Activity;
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
	 * incremental search on the OneVariableEquation class to find the interval
	 * 
	 * @param view
	 */
	public void onCalculateButtonClick(View view) {

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

			double interval[] = oneVariableEquations.incrementalSearch(x0,
					delta, iterations);

			TextView textInterval = (TextView) findViewById(R.id.textViewIntervalActivityIncrementalSearch);
			textInterval.setText("[" + interval[0] + ", " + interval[1] + "]");
		} catch (NumberFormatException e) {
			Toast.makeText(
					this,
					"You have to enter all the values for the method to be executed",
					Toast.LENGTH_LONG).show();
		} catch (RootNotFounException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

}
