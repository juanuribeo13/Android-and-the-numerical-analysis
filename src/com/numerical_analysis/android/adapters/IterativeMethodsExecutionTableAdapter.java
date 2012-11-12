package com.numerical_analysis.android.adapters;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.numerical_analysis.android.R;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

public class IterativeMethodsExecutionTableAdapter implements
		ExecutionTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(int columns, Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_iterative_methods, null);
		TextView iteration = new TextView(activity);
		iteration.setText(activity.getString(R.string.title_table_iteration));
		tableRow.addView(iteration);

		for (int i = 1; i < columns; i++) {
			TextView x = new TextView(activity);
			x.setText("X" + i);
			tableRow.addView(x);
		}

		TextView error = new TextView(activity);
		error.setText(activity.getString(R.string.title_table_error));
		tableRow.addView(error);
		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {
		NumberFormat formatter = new DecimalFormat("0.##E0");

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_iterative_methods, null);
		TextView iteration = new TextView(activity);
		iteration.setText(String.valueOf(row[0].intValue()));
		tableRow.addView(iteration);

		for (int i = 1; i < row.length - 1; i++) {
			TextView x = new TextView(activity);
			x.setText(row[i].toString());
			tableRow.addView(x);
		}

		TextView error = new TextView(activity);
		error.setText(formatter.format(row[row.length - 1]));
		tableRow.addView(error);
		return tableRow;
	}

}
