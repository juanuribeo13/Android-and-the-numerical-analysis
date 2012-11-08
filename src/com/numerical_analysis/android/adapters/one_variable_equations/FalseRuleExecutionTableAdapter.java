package com.numerical_analysis.android.adapters.one_variable_equations;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.ExecutionTableAdapter;

public class FalseRuleExecutionTableAdapter implements ExecutionTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_false_rule, null);

		TextView iteration = (TextView) tableRow
				.findViewById(R.id.textViewIterationRowFalseRule);
		iteration.setText(activity.getString(R.string.title_table_iteration));

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowFalseRule);
		x0.setText(activity.getString(R.string.title_table_x0));

		TextView x1 = (TextView) tableRow
				.findViewById(R.id.textViewX1RowFalseRule);
		x1.setText(activity.getString(R.string.title_table_x1));

		TextView xm = (TextView) tableRow
				.findViewById(R.id.textViewXMRowFalseRule);
		xm.setText(activity.getString(R.string.title_table_xm));

		TextView ym = (TextView) tableRow
				.findViewById(R.id.textViewYMRowFalseRule);
		ym.setText(activity.getString(R.string.title_table_ym));

		TextView error = (TextView) tableRow
				.findViewById(R.id.textViewErrorRowFalseRule);
		error.setText(activity.getString(R.string.title_table_error));

		TextView y0 = (TextView) tableRow
				.findViewById(R.id.textViewY0RowFalseRule);
		y0.setText(activity.getString(R.string.title_table_y0));

		TextView y1 = (TextView) tableRow
				.findViewById(R.id.textViewY1RowFalseRule);
		y1.setText(activity.getString(R.string.title_table_y1));

		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {
		NumberFormat formatter = new DecimalFormat("0.##E0");

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_false_rule, null);

		TextView iteration = (TextView) tableRow
				.findViewById(R.id.textViewIterationRowFalseRule);
		iteration.setText(String.valueOf(row[0].intValue()));

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowFalseRule);
		x0.setText(row[1].toString());

		TextView x1 = (TextView) tableRow
				.findViewById(R.id.textViewX1RowFalseRule);
		x1.setText(row[2].toString());

		TextView xm = (TextView) tableRow
				.findViewById(R.id.textViewXMRowFalseRule);
		xm.setText(row[3].toString());

		TextView ym = (TextView) tableRow
				.findViewById(R.id.textViewYMRowFalseRule);
		ym.setText(row[4].toString());

		TextView error = (TextView) tableRow
				.findViewById(R.id.textViewErrorRowFalseRule);
		error.setText(formatter.format(row[5]));

		TextView y0 = (TextView) tableRow
				.findViewById(R.id.textViewY0RowFalseRule);
		y0.setText(row[6].toString());

		TextView y1 = (TextView) tableRow
				.findViewById(R.id.textViewY1RowFalseRule);
		y1.setText(row[7].toString());

		return tableRow;
	}

}
