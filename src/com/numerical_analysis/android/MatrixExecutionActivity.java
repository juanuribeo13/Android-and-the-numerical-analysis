package com.numerical_analysis.android;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;
import com.numerical_analysis.android.utilities.ExpandableHeightGridView;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MatrixExecutionActivity extends Activity {

	private DirectMethods directMethods;
	private MatrixExecutionActivity matrixExecutionActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matrix_execution);
		directMethods = (DirectMethods) getIntent().getSerializableExtra(
				"directMethods");
		matrixExecutionActivity = this;
		final ArrayList<double[][]> execution = directMethods.getExecution();
		LinearLayout linearLayoutExecution = (LinearLayout) findViewById(R.id.linearLayoutExecutionMatricesActivityMatrixExecution);
		for (int i = 0; i < execution.size(); i++) {
			final int j = i;
			final double[][] m = execution.get(j);
			BaseAdapter adapter = new BaseAdapter() {
				public Object getItem(int position) {
					return null;
				}

				public int getCount() {
					return (m.length * m[0].length);
				}

				public long getItemId(int position) {
					return 0;
				}

				public View getView(int position, View convertView,
						ViewGroup parent) {
					TextView textView;
					if (convertView == null) {
						textView = new TextView(matrixExecutionActivity);
						textView.setLayoutParams(new GridView.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
					} else {
						textView = (TextView) convertView;
					}
					NumberFormat formatter = new DecimalFormat("#.#####");
					textView.setText(formatter.format(((new Matrix(m).getAsLinearArray())[position])));
					return textView;
				}
			};
			ExpandableHeightGridView gridView = new ExpandableHeightGridView(
					matrixExecutionActivity);
			gridView.setAdapter(adapter);
			gridView.setNumColumns(m[0].length);
			gridView.setExpanded(true);
			gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
			gridView.setLayoutParams(new GridView.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			linearLayoutExecution.addView(gridView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_matrix_execution, menu);
		return true;
	}
}
