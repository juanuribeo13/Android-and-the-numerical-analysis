package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.InputMatrixActivity;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DirectMethodsActivity extends ListActivity {

	private static final int INPUT_MATRIX = 0;
	private DirectMethods directMethods;
	private Matrix matrix = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String list[] = getResources()
				.getStringArray(R.array.directMethodsList);

		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_direct_methods, list));
		directMethods = new DirectMethods();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_direct_methods, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String action = ((TextView) v).getText().toString();

		if (action.equals("Input Matrix")) {
			Intent intent = new Intent(this, InputMatrixActivity.class);
			startActivityForResult(intent, INPUT_MATRIX);
		} else if (matrix == null) {
			Toast.makeText(this,
					"Please set the function before you use any method",
					Toast.LENGTH_LONG).show();
		} else if (action.equals("Simple Gaussian Elimination")) {
			Intent intent = new Intent(this,
					SimpleGaussianEliminationActivity.class);
			intent.putExtra("directMethods", directMethods);
			startActivity(intent);
		}
	}

}
