package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class IterativeMethodsActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String iterativeMethods[] = getResources().getStringArray(
				R.array.iterativeMethodsList);
		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_iterative_methods, iterativeMethods));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_iterative_methods, menu);
		return true;
	}
}
