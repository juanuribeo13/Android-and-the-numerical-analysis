package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.activities.HelpActivity;
import com.numerical_analysis.android.activities.PlotterActivity;
import com.numerical_analysis.android.activities.SetFunctionActivity;
import com.numerical_analysis.android.adapters.one_variable_equations.MultipleRootsExecutionTableAdapter;
import com.numerical_analysis.android.exceptions.DivisionByZeroException;
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

public class MultipleRootsActivity extends Activity {

	static final int SET_FUNCTION = 0;
	private OneVariableEquations oneVariableEquations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_roots);

		TextView function = (TextView) findViewById(R.id.textViewFunctionActivityMultipleRoots);
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
					"https://sites.google.com/site/numericalanalysiseafit/topics/one-variable-equations/multiple-roots");
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
				TextView function = (TextView) findViewById(R.id.textViewFunctionActivityMultipleRoots);
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

		TextView textRoot = (TextView) findViewById(R.id.textViewRootActivityMultipleRoots);
		try {
			// Get the values entered by the user
			double x0 = Double
					.parseDouble(((EditText) findViewById(R.id.editTextX0ActivityMultipleRoots))
							.getText().toString());

			int iterations = Integer
					.parseInt(((EditText) findViewById(R.id.editTextIterationsActivityMultipleRoots))
							.getText().toString());
			double tolerance = Double
					.parseDouble(((EditText) findViewById(R.id.editTextToleranceActivityMultipleRoots))
							.getText().toString());

			String df = ((EditText) findViewById(R.id.editTextDFActivityMultipleRoots))
					.getText().toString();

			String ddf = ((EditText) findViewById(R.id.editTextDDFActivityMultipleRoots))
					.getText().toString();

			// Show the root
			double root[] = oneVariableEquations.multipleRoots(x0, iterations,
					tolerance, df, ddf);
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
		} catch (DivisionByZeroException e) {
			textRoot.setText(e.getMessage());
			enableExecutionTable();
		}
	}

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewFunctionActivityMultipleRoots).setVisibility(
				visibility);
		findViewById(R.id.editTextX0ActivityMultipleRoots).setVisibility(
				visibility);
		findViewById(R.id.editTextIterationsActivityMultipleRoots)
				.setVisibility(visibility);
		findViewById(R.id.editTextToleranceActivityMultipleRoots)
				.setVisibility(visibility);
		findViewById(R.id.editTextDFActivityMultipleRoots).setVisibility(
				visibility);
		findViewById(R.id.editTextDDFActivityMultipleRoots).setVisibility(
				visibility);
		findViewById(R.id.buttonCalculateActivityMultipleRoots).setVisibility(
				visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityMultipleRoots)
					.setVisibility(View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityMultipleRoots)
					.setVisibility(View.VISIBLE);
		}
	}

	public void showExecutionTable(View view) {
		MultipleRootsExecutionTableAdapter adapter = new MultipleRootsExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", oneVariableEquations);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityMultipleRoots)
				.setVisibility(View.VISIBLE);
	}
}
