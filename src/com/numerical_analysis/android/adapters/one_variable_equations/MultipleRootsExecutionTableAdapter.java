package com.numerical_analysis.android.adapters.one_variable_equations;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.ExecutionTableAdapter;

public class MultipleRootsExecutionTableAdapter implements
		ExecutionTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(int columns, Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_multiple_roots, null);
		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {
		NumberFormat formatter = new DecimalFormat("0.##E0");

		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_multiple_roots, null);

		TextView iteration = (TextView) tableRow
				.findViewById(R.id.textViewIterationRowMultipleRoots);
		iteration.setText(String.valueOf(row[0].intValue()));

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowMultipleRoots);
		x0.setText(row[1].toString());

		TextView y0 = (TextView) tableRow
				.findViewById(R.id.textViewY0RowMultipleRoots);
		y0.setText(row[2].toString());

		TextView dy0 = (TextView) tableRow
				.findViewById(R.id.textViewDY0RowMultipleRoots);
		dy0.setText(row[3].toString());

		TextView ddy0 = (TextView) tableRow
				.findViewById(R.id.textViewDDY0RowMultipleRoots);
		ddy0.setText(row[4].toString());

		TextView error = (TextView) tableRow
				.findViewById(R.id.textViewErrorRowMultipleRoots);
		error.setText(formatter.format(row[5]));

		return tableRow;
	}

}
