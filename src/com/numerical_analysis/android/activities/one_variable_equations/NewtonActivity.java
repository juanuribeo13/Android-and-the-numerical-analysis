package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.activities.PlotterActivity;
import com.numerical_analysis.android.activities.SetFunctionActivity;
import com.numerical_analysis.android.adapters.one_variable_equations.NewtonExecutionTableAdapter;
import com.numerical_analysis.android.exceptions.MultipleRootFoundException;
import com.numerical_analysis.android.exceptions.RootFoundException;
import com.numerical_analysis.android.exceptions.RootNotFoundException;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewtonActivity extends Activity {

	static final int SET_FUNCTION = 0;
	private OneVariableEquations oneVariableEquations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newton);

		TextView function = (TextView) findViewById(R.id.textViewFunctionActivityNewton);
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
				TextView function = (TextView) findViewById(R.id.textViewFunctionActivityNewton);
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
			double root[] = oneVariableEquations.newton(x0, iterations,
					tolerance, df);
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

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewFunctionActivityNewton).setVisibility(
				visibility);
		findViewById(R.id.editTextX0ActivityNewton).setVisibility(visibility);
		findViewById(R.id.editTextIterationsActivityNewton).setVisibility(
				visibility);
		findViewById(R.id.editTextToleranceActivityNewton).setVisibility(
				visibility);
		findViewById(R.id.editTextDFActivityNewton).setVisibility(visibility);
		findViewById(R.id.buttonCalculateActivityNewton).setVisibility(
				visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityNewton).setVisibility(
					View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityNewton).setVisibility(
					View.VISIBLE);
		}
	}

	public void showExecutionTable(View view) {
		NewtonExecutionTableAdapter adapter = new NewtonExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", oneVariableEquations);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityNewton).setVisibility(
				View.VISIBLE);
	}
}
