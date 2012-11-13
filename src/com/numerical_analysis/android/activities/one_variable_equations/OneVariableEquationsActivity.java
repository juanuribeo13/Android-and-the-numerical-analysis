package com.numerical_analysis.android.activities.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.HelpActivity;
import com.numerical_analysis.android.activities.PlotterActivity;
import com.numerical_analysis.android.activities.SetFunctionActivity;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OneVariableEquationsActivity extends ListActivity {

	static final int SET_FUNCTION = 0;
	static final int METHOD = 1;

	private OneVariableEquations oneVariableEquations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String list[] = getResources().getStringArray(
				R.array.oneVariableEquationList);

		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_one_variable_equations, list));

		oneVariableEquations = new OneVariableEquations();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_one_variable_equations, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String action = ((TextView) v).getText().toString();

		if (action.equals("Incremental search")) {
			Intent intent = new Intent(this, IncrementalSearchActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivityForResult(intent, METHOD);
		} else if (action.equals("Bisection")) {
			Intent intent = new Intent(this, BisectionActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivityForResult(intent, METHOD);
		} else if (action.equals("False rule")) {
			Intent intent = new Intent(this, FalseRuleActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivityForResult(intent, METHOD);
		} else if (action.equals("Fixed point")) {
			Intent intent = new Intent(this, FixedPointActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivityForResult(intent, METHOD);
		} else if (action.equals("Newton")) {
			Intent intent = new Intent(this, NewtonActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivityForResult(intent, METHOD);
		} else if (action.equals("Secant")) {
			Intent intent = new Intent(this, SecantActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivityForResult(intent, METHOD);
		} else if (action.equals("Multiple Roots")) {
			Intent intent = new Intent(this, MultipleRootsActivity.class);
			intent.putExtra("oneVariableEquations", oneVariableEquations);
			startActivityForResult(intent, METHOD);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == SET_FUNCTION) {
				oneVariableEquations.setFunction(data
						.getStringExtra("Function"));
			} else if (requestCode == METHOD) {
				oneVariableEquations = (OneVariableEquations) data
						.getSerializableExtra("oneVariableEquations");
			}
		}
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
					"https://sites.google.com/site/numericalanalysiseafit/topics/one-variable-equations");
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
