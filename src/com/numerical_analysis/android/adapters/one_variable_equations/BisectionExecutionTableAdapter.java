package com.numerical_analysis.android.adapters.one_variable_equations;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.ExecutionTableAdapter;

public class BisectionExecutionTableAdapter implements ExecutionTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_bisection, null);

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowBisection);
		x0.setText("X0");

		TextView x1 = (TextView) tableRow
				.findViewById(R.id.textViewX1RowBisection);
		x1.setText("X1");

		TextView xm = (TextView) tableRow
				.findViewById(R.id.textViewXMRowBisection);
		xm.setText("XM");

		TextView ym = (TextView) tableRow
				.findViewById(R.id.textViewYMRowBisection);
		ym.setText("F(XM)");

		TextView error = (TextView) tableRow
				.findViewById(R.id.textViewErrorRowBisection);
		error.setText("Error");

		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {
		NumberFormat formatter = new DecimalFormat("0.##E0");

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_bisection, null);

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowBisection);
		x0.setText(row[0].toString());

		TextView x1 = (TextView) tableRow
				.findViewById(R.id.textViewX1RowBisection);
		x1.setText(row[1].toString());

		TextView xm = (TextView) tableRow
				.findViewById(R.id.textViewXMRowBisection);
		xm.setText(row[2].toString());

		TextView ym = (TextView) tableRow
				.findViewById(R.id.textViewYMRowBisection);
		ym.setText(row[3].toString());

		TextView error = (TextView) tableRow
				.findViewById(R.id.textViewErrorRowBisection);
		error.setText(formatter.format(row[4]));
		return tableRow;
	}

}
