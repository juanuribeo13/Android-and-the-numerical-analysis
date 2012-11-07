package com.numerical_analysis.android.activities;

import java.util.ArrayList;
import com.numerical_analysis.android.R;
import com.numerical_analysis.android.adapters.ExecutionTableAdapter;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;

public class ExecutionTableActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_execution_table);

		OneVariableEquations oneVariableEquations = (OneVariableEquations) getIntent()
				.getSerializableExtra("oneVariableEquations");

		ExecutionTableAdapter adapter = (ExecutionTableAdapter) getIntent()
				.getSerializableExtra("adapter");

		showTable(oneVariableEquations.getExecutionTable(), adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_execution_table, menu);
		return true;
	}

	private void showTable(ArrayList<Double[]> executionTable,
			ExecutionTableAdapter adapter) {
		TableLayout table = (TableLayout) findViewById(R.id.tableLayoutExecution);
		table.addView(adapter.getTitle(this));
		for (Double[] doubles : executionTable) {
			TableRow row = adapter.getRow(doubles, this);
			table.addView(row);
		}
	}
}
