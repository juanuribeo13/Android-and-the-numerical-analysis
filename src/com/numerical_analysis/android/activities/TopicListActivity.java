package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.one_variable_equations.OneVariableEquationsActivity;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TopicListActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String topics[] = getResources().getStringArray(R.array.topics);

		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_topic_list, topics));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String topic = ((TextView) v).getText().toString();

		if (topic.equals("One Variable Equation")) {
			Intent intent = new Intent(this, OneVariableEquationsActivity.class);
			startActivity(intent);
		}
	}

}
