package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SystemsOfEquationsActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String list[] = getResources().getStringArray(
				R.array.systemsOfEquationsList);

		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_systems_of_equations, list));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_systems_of_equations, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String action = ((TextView) v).getText().toString();

		if (action.equals("Direct Methods")) {
			Intent intent = new Intent(this, DirectMethodsActivity.class);
			startActivity(intent);
		}
	}
}
