package com.numerical_analysis.android.adapters.one_variable_equations;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.ExecutionTableAdapter;

public class NewtonExecutionTableAdapter implements ExecutionTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_newton, null);

		TextView iteration = (TextView) tableRow
				.findViewById(R.id.textViewIterationRowNewton);
		iteration.setText(activity.getString(R.string.title_table_iteration));

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowNewton);
		x0.setText(activity.getString(R.string.title_table_x0));

		TextView y0 = (TextView) tableRow
				.findViewById(R.id.textViewY0RowNewton);
		y0.setText(activity.getString(R.string.title_table_y0));

		TextView dy0 = (TextView) tableRow
				.findViewById(R.id.textViewDY0RowNewton);
		dy0.setText(activity.getString(R.string.title_table_dy0));

		TextView error = (TextView) tableRow
				.findViewById(R.id.textViewErrorRowNewton);
		error.setText(activity.getString(R.string.title_table_error));

		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {
		NumberFormat formatter = new DecimalFormat("0.##E0");

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_newton, null);

		TextView iteration = (TextView) tableRow
				.findViewById(R.id.textViewIterationRowNewton);
		iteration.setText(String.valueOf(row[0].intValue()));

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowNewton);
		x0.setText(row[1].toString());

		TextView y0 = (TextView) tableRow
				.findViewById(R.id.textViewY0RowNewton);
		y0.setText(row[2].toString());

		TextView dy0 = (TextView) tableRow
				.findViewById(R.id.textViewDY0RowNewton);
		dy0.setText(row[3].toString());

		TextView error = (TextView) tableRow
				.findViewById(R.id.textViewErrorRowNewton);
		error.setText(formatter.format(row[4]));

		return tableRow;
	}

}
