package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.HelpActivity;
import com.numerical_analysis.android.activities.SetIndependentTermsActivity;
import com.numerical_analysis.android.methods.systems_of_equations.IterativeMethods;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class IterativeMethodsActivity extends ListActivity {

	private IterativeMethods iterativeMethods;
	static final int SET_INDEPENDENT_TERMS = 0;
	static final int METHOD = 1;

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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String action = ((TextView) v).getText().toString();
		if (action.equals("Jacobi")) {
			Intent intent = new Intent(this, JacobiActivity.class);
			intent.putExtra("iterativeMethods", iterativeMethods);
			startActivityForResult(intent, METHOD);
		} else if (action.equals("Gauss Seidel")) {
			Intent intent = new Intent(this, GaussSeidelActivity.class);
			intent.putExtra("iterativeMethods", iterativeMethods);
			startActivityForResult(intent, METHOD);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == SET_INDEPENDENT_TERMS) {
				iterativeMethods.setIndependentTerms(data
						.getStringArrayExtra("independentTerms"));
			} else if (requestCode == METHOD) {
				iterativeMethods = (IterativeMethods) data
						.getSerializableExtra("iterativeMethods");
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menu_set_independent_terms:
			Intent intent = new Intent(this, SetIndependentTermsActivity.class);
			intent.putExtra("independentTerms",
					iterativeMethods.getIndependentTerms());
			startActivityForResult(intent, SET_INDEPENDENT_TERMS);
			return true;
		case R.id.menu_help:
			intent = new Intent(this, HelpActivity.class);
			intent.putExtra(
					"url",
					"https://sites.google.com/site/numericalanalysiseafit/topics/systems-of-equations/iterative-methods");
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
