package com.numerical_analysis.android.activities.interpolation;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.SetXAndFXActivity;
import com.numerical_analysis.android.methods.Interpolation;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InterpolationActivity extends ListActivity {

	private Interpolation interpolation;
	static final int SET_X_AND_FX = 0;
	static final int METHOD = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String list[] = getResources()
				.getStringArray(R.array.interpolationList);

		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_interpolation, list));

		interpolation = new Interpolation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_interpolation, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String action = ((TextView) v).getText().toString();

		if (action.equals("Newton")) {
			Intent intent = new Intent(this, NewtonInterpolationActivity.class);
			intent.putExtra("interpolation", interpolation);
			startActivityForResult(intent, METHOD);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == METHOD) {
				interpolation = (Interpolation) data
						.getSerializableExtra("interpolation");
			} else if (requestCode == SET_X_AND_FX) {
				interpolation.setX(data.getDoubleArrayExtra("x"));
				interpolation.setY(data.getDoubleArrayExtra("y"));
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menu_set_x_and_fx:
			Intent intent = new Intent(this, SetXAndFXActivity.class);
			intent.putExtra("x", interpolation.getX());
			intent.putExtra("y", interpolation.getY());
			startActivityForResult(intent, SET_X_AND_FX);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
