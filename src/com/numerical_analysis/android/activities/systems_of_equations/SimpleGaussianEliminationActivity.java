package com.numerical_analysis.android.activities.systems_of_equations;

import java.util.ArrayList;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.methods.systems_of_equations.DirectMethods;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleGaussianEliminationActivity extends Activity {

	private DirectMethods directMethods;
	private SimpleGaussianEliminationActivity simpleGaussianEliminationActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_gaussian_elimination);
		directMethods = (DirectMethods) getIntent().getSerializableExtra(
				"directMethods");
		Matrix matrix = (Matrix) getIntent().getSerializableExtra("Matrix");
		LinearLayout linearLayoutExecution = (LinearLayout) findViewById(R.id.linearLayoutExecutionMatricesActivitySimpleGaussianElimination);
		LinearLayout linearLayoutResult = (LinearLayout) findViewById(R.id.linearLayoutResultActivitySimpleGaussianElimination);

		double[] x = directMethods.simpleGaussianElimination(matrix,
				matrix.getB());
		TextView[] results = new TextView[x.length];

		int[] marks = matrix.getMarks();
		for (int i = 0; i < x.length; i++) {
			TextView textView = new TextView(this);
			textView.setText("x" + marks[i] + " = " + x[i]);
			results[i] = textView;
			linearLayoutResult.addView(textView);
		}
		simpleGaussianEliminationActivity = this;
		final ArrayList<double[][]> execution = directMethods.getExecution();
		for (int i = 0; i < execution.size(); i++) {
			final int j = i;
			final double[][] m = execution.get(j);
			BaseAdapter adapter = new BaseAdapter() {
				public Object getItem(int position) {
					return null;
				}

				public int getCount() {
					return m.length * m[0].length;
				}

				public long getItemId(int position) {
					return 0;
				}

				public View getView(int position, View convertView,
						ViewGroup parent) {
					TextView textView;
					if (convertView == null) {
						textView = new TextView(
								simpleGaussianEliminationActivity);
						textView.setLayoutParams(new GridView.LayoutParams(90,
								40));
					} else {
						textView = (TextView) convertView;
					}
					textView.setText(((new Matrix(m).getAsLinearArray())[position])
							+ "");
					return textView;
				}
			};
			GridView gridView = new GridView(simpleGaussianEliminationActivity);
			gridView.setAdapter(adapter);
			gridView.setNumColumns(m[0].length);
			linearLayoutExecution.addView(gridView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_simple_gaussian_elimination,
				menu);
		return true;
	}
}
