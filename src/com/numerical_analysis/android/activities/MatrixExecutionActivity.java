package com.numerical_analysis.android.activities;

import java.util.ArrayList;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.DirectMethodsMatrixExecutionAdapter;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MatrixExecutionActivity extends Activity {

	private DirectMethods directMethods;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matrix_execution);
		directMethods = (DirectMethods) getIntent().getSerializableExtra(
				"directMethods");

		DirectMethodsMatrixExecutionAdapter adapter = (DirectMethodsMatrixExecutionAdapter) getIntent()
				.getSerializableExtra("adapter");
		showTable(directMethods.getExecution(), adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_execution_table, menu);
		return true;
	}

	private void showTable(ArrayList<double[][]> execution,
			DirectMethodsMatrixExecutionAdapter adapter) {
		TableLayout table = (TableLayout) findViewById(R.id.tableLayoutMatrixExecution);
		table.addView(adapter.getTitle(execution.get(0).length, this));
		for (int i = 0; i < execution.size(); i++) {
			for (int j = 0; j < execution.get(i).length; j++) {
				TableRow row = adapter.getRow(execution.get(i)[j], this);
				table.addView(row);
			}
			table.addView(adapter.getEmptyRow(execution.get(i).length, this));
		}
	}
}
