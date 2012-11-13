package com.numerical_analysis.android.activities.systems_of_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.HelpActivity;
import com.numerical_analysis.android.activities.InputMatrixActivity;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DirectMethodsActivity extends ListActivity {

	private static final int INPUT_MATRIX = 0;
	private DirectMethods directMethods;
	private Matrix matrix = null;

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String list[] = getResources()
				.getStringArray(R.array.directMethodsList);

		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_direct_methods, list));
		directMethods = new DirectMethods();
		// // This is only for testing remove it when testing be done
		// double[][] m = { { 20, -1, 1, -1 }, { 5, 35, -4, 8 },
		// { 2, -10, 75, -1 }, { 3, -7, 4, -27 } };
		// double[] b = { 8, 50, -114, 10 };
		// matrix = new Matrix(m);
		// matrix.setB(b);
		// // Testing
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

		if (action.equals("Gaussian Elimination")) {
			Intent intent = new Intent(this, GaussianEliminationActivity.class);
			intent.putExtra("Matrix", matrix);
			intent.putExtra("directMethods", directMethods);
			startActivity(intent);
		} else if (action.equals("Direct Matrix Factorization")) {
			Intent intent = new Intent(this,
					DirectMatrixFactorizationActivity.class);
			intent.putExtra("Matrix", matrix);
			intent.putExtra("directMethods", directMethods);
			startActivity(intent);
		} else if (action.equals("LU Matrix Factorization")) {
			Intent intent = new Intent(this,
					LUMatrixFactorizationActivity.class);
			intent.putExtra("Matrix", matrix);
			intent.putExtra("directMethods", directMethods);
			startActivity(intent);
		} else if (action
				.equals("LU Matrix Factorization with Partial Pivoting")) {
			Intent intent = new Intent(this,
					LUMatrixFactorizationWithPartialPivoting.class);
			intent.putExtra("Matrix", matrix);
			intent.putExtra("directMethods", directMethods);
			startActivity(intent);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == INPUT_MATRIX) {
				setMatrix((Matrix) data.getSerializableExtra("Matrix"));
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menu_input_matrix:
			Intent inputMatrix = new Intent(this, InputMatrixActivity.class);
			if (matrix != null) {
				inputMatrix.putExtra("Matrix", matrix);
			}
			startActivityForResult(inputMatrix, INPUT_MATRIX);
			return true;
		case R.id.menu_help:
			Intent intent = new Intent(this, HelpActivity.class);
			intent.putExtra(
					"url",
					"https://sites.google.com/site/numericalanalysiseafit/topics/systems-of-equations/direct-methods");
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
