package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.adapters.one_variable_equations.BisectionExecutionTableAdapter;
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

public class BisectionActivity extends Activity {

	private OneVariableEquations oneVariableEquations;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bisection);

		TextView function = (TextView) findViewById(R.id.textViewFunctionActivityBisection);
		oneVariableEquations = (OneVariableEquations) getIntent()
				.getSerializableExtra("oneVariableEquations");
		function.setText(oneVariableEquations.getFunction());
	}

	/**
	 * Called when the user clicks on the Calculate button and calls the
	 * bisection method on the OneVariableEquation class to find the root
	 * 
	 * @param view
	 */
	public void onCalculateButtonClick(View view) {

		TextView textRoot = (TextView) findViewById(R.id.textViewRootActivityBisection);
		try {
			// Get the values entered by the user
			double x0 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX0ActivityBisection))
							.getText().toString());
			double x1 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX1ActivityBisection))
							.getText().toString());
			int iterations = Integer
					.parseInt(((EditText) findViewById(R.id.editTextIterationsActivityBisection))
							.getText().toString());
			double tolerance = Double
					.parseDouble(((EditText) findViewById(R.id.editTextToleranceActivityBisection))
							.getText().toString());

			// Show the root
			double root[] = oneVariableEquations.bisection(x0, x1, iterations,
					tolerance);
			textRoot.setText(root[0] + " is root with tolerance " + root[1]);

			enableExecutionTable();

		} catch (NumberFormatException e) {
			Toast.makeText(this, getString(R.string.invalid_parameters),
					Toast.LENGTH_LONG).show();
		} catch (RootNotFoundException e) {
			textRoot.setText(e.getMessage());
			enableExecutionTable();
		} catch (RootFoundException e) {
			textRoot.setText(e.getMessage());
			enableExecutionTable();
		}
	}

	public void showExecutionTable(View view) {
		BisectionExecutionTableAdapter adapter = new BisectionExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", oneVariableEquations);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityBisection).setVisibility(
				View.VISIBLE);
	}

}
