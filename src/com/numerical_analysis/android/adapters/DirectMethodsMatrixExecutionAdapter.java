package com.numerical_analysis.android.adapters;

import com.numerical_analysis.android.R;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

public class DirectMethodsMatrixExecutionAdapter implements
		MatrixExecutionAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(int columns, Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_matrix_execution_activity, null);
		return tableRow;
	}

	public TableRow getRow(double[] ds, Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_iterative_methods, null);
		for (int i = 0; i < ds.length; i++) {
			TextView x = new TextView(activity);
			x.setText(ds[i] + "");
			tableRow.addView(x);
		}
		return tableRow;
	}

	public TableRow getEmptyRow(int n, Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_iterative_methods, null);
		for (int i = 0; i < n; i++) {
			TextView x = new TextView(activity);
			x.setText("");
			tableRow.addView(x);
		}
		return tableRow;
	}

}
