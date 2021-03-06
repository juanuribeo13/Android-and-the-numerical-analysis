package com.numerical_analysis.android.adapters.interpolation;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.ExecutionTableAdapter;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

public class NevilleExecutionTableAdapter implements ExecutionTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(int columns, Activity activity) {
		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
		layoutParams.setMargins(10, 0, 0, 0);

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_interpolation_methods,
				null);
		TextView x = new TextView(activity);
		x.setText(activity.getString(R.string.title_table_x0));
		x.setLayoutParams(layoutParams);
		tableRow.addView(x);

		TextView y = new TextView(activity);
		y.setText(activity.getString(R.string.title_table_y0));
		y.setLayoutParams(layoutParams);
		tableRow.addView(y);

		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {

		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
		layoutParams.setMargins(10, 0, 0, 0);

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_interpolation_methods,
				null);
		TextView x = new TextView(activity);
		x.setText(String.valueOf(row[0]));
		x.setLayoutParams(layoutParams);
		tableRow.addView(x);

		TextView y = new TextView(activity);
		y.setText(String.valueOf(row[1]));
		y.setLayoutParams(layoutParams);
		tableRow.addView(y);

		for (int i = 2; i < row.length; i++) {
			TextView d = new TextView(activity);
			d.setText(String.valueOf(row[i]));
			d.setLayoutParams(layoutParams);
			tableRow.addView(d);
		}
		return tableRow;
	}
}
