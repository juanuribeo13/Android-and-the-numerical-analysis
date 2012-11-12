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
		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
		layoutParams.setMargins(10, 0, 0, 0);

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_iterative_methods, null);
		TextView iteration = new TextView(activity);
		iteration.setText(activity.getString(R.string.title_table_iteration));
		iteration.setLayoutParams(layoutParams);
		tableRow.addView(iteration);

		for (int i = 1; i < columns - 1; i++) {
			TextView x = new TextView(activity);
			x.setText("X" + i);
			x.setLayoutParams(layoutParams);
			tableRow.addView(x);
		}

		TextView error = new TextView(activity);
		error.setText(activity.getString(R.string.title_table_error));
		error.setLayoutParams(layoutParams);
		tableRow.addView(error);
		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {
		NumberFormat formatter = new DecimalFormat("0.##E0");

		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
		layoutParams.setMargins(10, 0, 0, 0);

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_iterative_methods, null);
		TextView iteration = new TextView(activity);
		iteration.setText(String.valueOf(row[0].intValue()));
		iteration.setLayoutParams(layoutParams);
		tableRow.addView(iteration);

		for (int i = 1; i < row.length - 1; i++) {
			TextView x = new TextView(activity);
			x.setText(row[i].toString());
			x.setLayoutParams(layoutParams);
			tableRow.addView(x);
		}

		TextView error = new TextView(activity);
		error.setText(formatter.format(row[row.length - 1]));
		error.setLayoutParams(layoutParams);
		tableRow.addView(error);
		return tableRow;
	}

}
