package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class InputMatrixActivity extends Activity {

	private Matrix matrix = null;
	private int matrixSize = 3;
	private double a[][];
	private GridView gridViewMatrix;
	private InputMatrixActivity inputMatrixActivity;
	private int row = 0;
	private int column = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_matrix);
		inputMatrixActivity = this;
		matrix = new Matrix(matrixSize, matrixSize);
		a = matrix.getMatrix();
		gridViewMatrix = (GridView) findViewById(R.id.gridViewActivityInputMatrix);
		gridViewMatrix.setAdapter(new BaseAdapter() {

			public Object getItem(int position) {
				return null;
			}

			public int getCount() {
				return matrixSize * matrixSize;
			}

			public long getItemId(int position) {
				return 0;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				TextView textView;
				if (convertView == null) {
					textView = new TextView(inputMatrixActivity);
					textView.setLayoutParams(new GridView.LayoutParams(40, 40));
				} else {
					textView = (TextView) convertView;
				}
				textView.setText((matrix.getAsLinearArray())[position] + "");
				return textView;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_input_matrix, menu);
		return true;
	}

	public void onFinishButtonClick(View view) {
		Intent returnIntent = new Intent();
		// returnIntent.putExtra("Matrix", ???getMatrix???);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	public void onNextButtonClick(View view) {
		TextView currentField = (TextView) findViewById(R.id.textViewCurrentMatrixFieldActivityInputMatrix);
		EditText matrixField = (EditText) findViewById(R.id.editTextMatrixFieldActivityInputMatrix);

		a[row][column] = Double.parseDouble(matrixField.getText().toString());
		column++;
		if (column == matrixSize) {
			column = 0;
			row++;
		}
		if ((row == matrixSize && column == 0)) {
			((Button) findViewById(R.id.buttonNextActivityInputMatrix))
					.setEnabled(false);
			((Button) findViewById(R.id.buttonFinishActivityInputMatrix))
					.setEnabled(true);
		}
		currentField.setText("Please enter A" + (row + 1) + "" + (column + 1));
		matrixField.setText("");
		ListAdapter adapter = gridViewMatrix.getAdapter();
		gridViewMatrix.invalidateViews();
		gridViewMatrix.setAdapter(adapter);
		gridViewMatrix.refreshDrawableState();

	}

	public void onSetButtonClick(View view) {
		EditText editTextMatrixSize = (EditText) findViewById(R.id.editTextMatrixSizeActivityInputMatrix);
		this.matrixSize = Integer.parseInt(editTextMatrixSize.getText()
				.toString());
		matrix = new Matrix(matrixSize);
		a = matrix.getMatrix();
		gridViewMatrix.setNumColumns(matrixSize);
		EditText matrixField = (EditText) findViewById(R.id.editTextMatrixFieldActivityInputMatrix);
		if (!matrixField.equals("") && !matrixField.equals(null)) {
			((Button) findViewById(R.id.buttonNextActivityInputMatrix))
					.setEnabled(true);
		}

	}

}
