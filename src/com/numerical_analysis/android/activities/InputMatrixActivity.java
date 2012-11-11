package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.utilities.Matrix;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

public class InputMatrixActivity extends Activity {

	private Matrix matrix = null;
	private int matrixSize = 1;
	private double a[][];
	private double b[];
	private GridView gridViewMatrix;
	private InputMatrixActivity inputMatrixActivity;
	private int row;
	private int column;
	private EditText[] inputs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_matrix);
		inputMatrixActivity = this;
		column = 0;
		row = 0;
		matrix = (Matrix) getIntent().getSerializableExtra("Matrix");
		if (matrix == null) {
			matrix = new Matrix(matrixSize, matrixSize);
		} else
			matrixSize = matrix.getRows();
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
					textView.setLayoutParams(new GridView.LayoutParams(90, 40));
				} else {
					textView = (TextView) convertView;
				}
				textView.setText(((matrix.getAsLinearArray())[position]) + "");
				return textView;
			}
		});
		gridViewMatrix.setNumColumns(matrixSize);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_input_matrix, menu);
		return true;
	}

	public void onFinishButtonClick(View view) {
		b = new double[matrixSize];
		for (int i = 0; i < inputs.length; i++) {
			b[i] = Double.parseDouble(inputs[i].getText().toString());
		}
		matrix.setB(b);
		matrix.setMatrix(a);
		Intent returnIntent = new Intent();
		returnIntent.putExtra("Matrix", matrix);
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
			LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutInputVectorActivityInputMatrix);
			inputs = new EditText[matrixSize];
			for (int i = 0; i < matrixSize; i++) {
				EditText editText = new EditText(this);
				editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL
						| InputType.TYPE_NUMBER_FLAG_SIGNED);
				inputs[i] = editText;
				layout.addView(inputs[i]);
			}
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
