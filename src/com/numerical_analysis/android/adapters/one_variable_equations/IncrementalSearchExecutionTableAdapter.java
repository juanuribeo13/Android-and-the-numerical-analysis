package com.numerical_analysis.android.adapters.one_variable_equations;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.ExecutionTableAdapter;

import android.app.Activity;
import android.widget.TableRow;
import android.widget.TextView;

public class IncrementalSearchExecutionTableAdapter implements
		ExecutionTableAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRow getTitle(Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_incremental_search, null);
		return tableRow;
	}

	public TableRow getRow(Double[] row, Activity activity) {
		TableRow tableRow = (TableRow) activity.getLayoutInflater().inflate(
				R.layout.row_execution_table_activity_incremental_search, null);

		TextView iteration = (TextView) tableRow
				.findViewById(R.id.textViewIterationRowIncrementalSearch);
		iteration.setText(String.valueOf((row[0].intValue())));

		TextView x0 = (TextView) tableRow
				.findViewById(R.id.textViewX0RowIncrementalSearch);
		x0.setText(row[1].toString());

		TextView x1 = (TextView) tableRow
				.findViewById(R.id.textViewX1RowIncrementalSearch);
		x1.setText(row[2].toString());

		TextView y0 = (TextView) tableRow
				.findViewById(R.id.textViewY0RowIncrementalSearch);
		y0.setText(row[3].toString());

		TextView y1 = (TextView) tableRow
				.findViewById(R.id.textViewY1RowIncrementalSearch);
		y1.setText(row[4].toString());
		return tableRow;
	}

}
