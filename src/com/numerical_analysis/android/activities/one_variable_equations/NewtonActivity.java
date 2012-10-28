package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.exceptions.RootFoundException;
import com.numerical_analysis.android.exceptions.RootNotFoundException;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewtonActivity extends Activity {

	private OneVariableEquations oneVariableEquations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newton);

		TextView function = (TextView) findViewById(R.id.textViewFunctionActivityNewton);
		oneVariableEquations = (OneVariableEquations) getIntent()
				.getSerializableExtra("oneVariableEquations");
		function.setText(oneVariableEquations.getFunction());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_newton, menu);
		return true;
	}

	public void onCalculateButtonClick(View view) {

		TextView textRoot = (TextView) findViewById(R.id.textViewRootActivityNewton);
		try {
			// Get the values entered by the user
			double x0 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX0ActivityNewton))
							.getText().toString());

			int iterations = Integer
					.parseInt(((EditText) findViewById(R.id.editTextIterationsActivityNewton))
							.getText().toString());
			double tolerance = Double
					.parseDouble(((EditText) findViewById(R.id.editTextToleranceActivityNewton))
							.getText().toString());

			String df = ((EditText) findViewById(R.id.editTextDFActivityNewton))
					.getText().toString();

			// Show the root
			double root[] = oneVariableEquations.fixedPoint(x0, iterations,
					tolerance, df);
			textRoot.setText(root[0] + " is root with tolerance " + root[1]);
		} catch (NumberFormatException e) {
			Toast.makeText(this, getString(R.string.invalid_parameters),
					Toast.LENGTH_LONG).show();
		} catch (RootFoundException e) {
			textRoot.setText(e.getMessage());
		} catch (RootNotFoundException e) {
			textRoot.setText(e.getMessage());
		}
	}
}