package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.SetIndependentTermsActivity;
import com.numerical_analysis.android.methods.systems_of_equations.IterativeMethods;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class IterativeMethodsActivity extends ListActivity {

	private IterativeMethods iterativeMethods;
	static final int SET_INDEPENDENT_TERMS = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String iterativeMethods[] = getResources().getStringArray(
				R.array.iterativeMethodsList);
		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_iterative_methods, iterativeMethods));

		this.iterativeMethods = new IterativeMethods();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_iterative_methods, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menu_set_independent_terms:
			Intent intent = new Intent(this, SetIndependentTermsActivity.class);
			intent.putExtra("iterativeMethods", iterativeMethods);
			startActivityForResult(intent, SET_INDEPENDENT_TERMS);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
