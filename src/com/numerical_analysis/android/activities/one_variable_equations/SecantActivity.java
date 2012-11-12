package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.adapters.one_variable_equations.SecantExecutionTableAdapter;
import com.numerical_analysis.android.exceptions.MultipleRootFoundException;
import com.numerical_analysis.android.exceptions.RootFoundException;
import com.numerical_analysis.android.exceptions.RootNotFoundException;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecantActivity extends Activity {

	private OneVariableEquations oneVariableEquations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secant);

		TextView function = (TextView) findViewById(R.id.textViewFunctionActivitySecant);
		oneVariableEquations = (OneVariableEquations) getIntent()
				.getSerializableExtra("oneVariableEquations");
		function.setText(oneVariableEquations.getFunction());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_secant, menu);
		return true;
	}

	public void onCalculateButtonClick(View view) {

		TextView textRoot = (TextView) findViewById(R.id.textViewRootActivitySecant);
		try {
			// Get the values entered by the user
			double x0 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX0ActivitySecant))
							.getText().toString());
			double x1 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX1ActivitySecant))
							.getText().toString());
			int iterations = Integer
					.parseInt(((EditText) findViewById(R.id.editTextIterationsActivitySecant))
							.getText().toString());
			double tolerance = Double
					.parseDouble(((EditText) findViewById(R.id.editTextToleranceActivitySecant))
							.getText().toString());

			// Show the root
			double root[] = oneVariableEquations.secant(x0, x1, iterations,
					tolerance);
			textRoot.setText(root[0] + " is root with tolerance " + root[1]);

			enableExecutionTable();
		} catch (NumberFormatException e) {
			Toast.makeText(this, getString(R.string.invalid_parameters),
					Toast.LENGTH_LONG).show();
		} catch (RootFoundException e) {
			textRoot.setText(e.getMessage());
			enableExecutionTable();
		} catch (RootNotFoundException e) {
			textRoot.setText(e.getMessage());
			enableExecutionTable();
		} catch (MultipleRootFoundException e) {
			textRoot.setText(e.getMessage());
			enableExecutionTable();
		}
	}

	public void showExecutionTable(View view) {
		SecantExecutionTableAdapter adapter = new SecantExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", oneVariableEquations);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivitySecant).setVisibility(
				View.VISIBLE);
	}
}
