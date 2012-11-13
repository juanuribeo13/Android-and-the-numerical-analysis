package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.activities.PlotterActivity;
import com.numerical_analysis.android.activities.SetFunctionActivity;
import com.numerical_analysis.android.adapters.one_variable_equations.FalseRuleExecutionTableAdapter;
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

public class FalseRuleActivity extends Activity {

	static final int SET_FUNCTION = 0;
	private OneVariableEquations oneVariableEquations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_false_rule);

		TextView function = (TextView) findViewById(R.id.textViewFunctionActivityFalseRule);
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
				TextView function = (TextView) findViewById(R.id.textViewFunctionActivityFalseRule);
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

		TextView textRoot = (TextView) findViewById(R.id.textViewRootActivityFalseRule);
		try {
			// Get the values entered by the user
			double x0 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX0ActivityFalseRule))
							.getText().toString());
			double x1 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX1ActivityFalseRule))
							.getText().toString());
			int iterations = Integer
					.parseInt(((EditText) findViewById(R.id.editTextIterationsActivityFalseRule))
							.getText().toString());
			double tolerance = Double
					.parseDouble(((EditText) findViewById(R.id.editTextToleranceActivityFalseRule))
							.getText().toString());

			// Show the root
			double root[] = oneVariableEquations.falseRule(x0, x1, iterations,
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
		}
	}

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewFunctionActivityFalseRule).setVisibility(
				visibility);
		findViewById(R.id.editTextX0ActivityFalseRule)
				.setVisibility(visibility);
		findViewById(R.id.editTextX1ActivityFalseRule)
				.setVisibility(visibility);
		findViewById(R.id.editTextIterationsActivityFalseRule).setVisibility(
				visibility);
		findViewById(R.id.editTextToleranceActivityFalseRule).setVisibility(
				visibility);
		findViewById(R.id.buttonCalculateActivityFalseRule).setVisibility(
				visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityFalseRule).setVisibility(
					View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityFalseRule).setVisibility(
					View.VISIBLE);
		}
	}

	public void showExecutionTable(View view) {
		FalseRuleExecutionTableAdapter adapter = new FalseRuleExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", oneVariableEquations);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityFalseRule).setVisibility(
				View.VISIBLE);
	}
}
