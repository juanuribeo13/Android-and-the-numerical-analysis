package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SetXAndFXActivity extends Activity {

	private double[] x;
	private double[] y;
	int position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_x_and_fx);

		x = getIntent().getDoubleArrayExtra("x");
		y = getIntent().getDoubleArrayExtra("y");

		if (x != null && y != null) {
			position = 0;
			EditText editX = (EditText) findViewById(R.id.editTextXActivitySetXAndFX);
			editX.setText(String.valueOf(x[position]));

			TextView viewX = (TextView) findViewById(R.id.textViewXActivitySetXAndFX);
			viewX.setText("Please insert x" + (position + 1));

			EditText editY = (EditText) findViewById(R.id.editTextYActivitySetXAndFX);
			editY.setText(String.valueOf(y[position]));

			TextView viewY = (TextView) findViewById(R.id.textViewYActivitySetXAndFX);
			viewY.setText("Please insert y" + (position + 1));

			setVisibilities(View.VISIBLE);
		} else {
			setVisibilities(View.INVISIBLE);
		}
	}

	public void onSetButtonClick(View view) {
		EditText editTextN = (EditText) findViewById(R.id.editTextNActivitySetXAndFX);
		int n = Integer.valueOf(editTextN.getText().toString());
		x = new double[n];
		y = new double[n];

		position = 0;
		TextView viewX = (TextView) findViewById(R.id.textViewXActivitySetXAndFX);
		viewX.setText("Please insert x" + (position + 1));
		TextView viewY = (TextView) findViewById(R.id.textViewYActivitySetXAndFX);
		viewY.setText("Please insert y" + (position + 1));

		EditText editX = (EditText) findViewById(R.id.editTextXActivitySetXAndFX);
		editX.setText(String.valueOf(x[position]));
		EditText editY = (EditText) findViewById(R.id.editTextYActivitySetXAndFX);
		editY.setText(String.valueOf(y[position]));

		setVisibilities(View.VISIBLE);
	}

	public void onPreviousButtonClick(View view) {
		EditText editTextX = (EditText) findViewById(R.id.editTextXActivitySetXAndFX);
		x[position] = Double.valueOf(editTextX.getText().toString());
		EditText editTextY = (EditText) findViewById(R.id.editTextYActivitySetXAndFX);
		y[position] = Double.valueOf(editTextY.getText().toString());
		if (position > 0) {
			position--;
			TextView viewX = (TextView) findViewById(R.id.textViewXActivitySetXAndFX);
			viewX.setText("Please insert x" + (position + 1));
			TextView viewY = (TextView) findViewById(R.id.textViewYActivitySetXAndFX);
			viewY.setText("Please insert y" + (position + 1));

			EditText editX = (EditText) findViewById(R.id.editTextXActivitySetXAndFX);
			editX.setText(String.valueOf(x[position]));
			EditText editY = (EditText) findViewById(R.id.editTextYActivitySetXAndFX);
			editY.setText(String.valueOf(y[position]));
		}
	}

	public void onNextButtonClick(View view) {

		EditText editTextX = (EditText) findViewById(R.id.editTextXActivitySetXAndFX);
		x[position] = Double.valueOf(editTextX.getText().toString());
		EditText editTextY = (EditText) findViewById(R.id.editTextYActivitySetXAndFX);
		y[position] = Double.valueOf(editTextY.getText().toString());
		if (position < x.length - 1) {
			position++;
			TextView viewX = (TextView) findViewById(R.id.textViewXActivitySetXAndFX);
			viewX.setText("Please insert x" + (position + 1));
			TextView viewY = (TextView) findViewById(R.id.textViewYActivitySetXAndFX);
			viewY.setText("Please insert y" + (position + 1));

			EditText editX = (EditText) findViewById(R.id.editTextXActivitySetXAndFX);
			editX.setText(String.valueOf(x[position]));
			EditText editY = (EditText) findViewById(R.id.editTextYActivitySetXAndFX);
			editY.setText(String.valueOf(y[position]));
		}
		if (position == x.length - 1) {
			findViewById(R.id.buttonFinishActivitySetXAndFX).setVisibility(
					View.VISIBLE);
		}
	}

	public void onFinishButtonClick(View view) {
		EditText editTextX = (EditText) findViewById(R.id.editTextXActivitySetXAndFX);
		x[position] = Double.valueOf(editTextX.getText().toString());
		EditText editTextY = (EditText) findViewById(R.id.editTextYActivitySetXAndFX);
		y[position] = Double.valueOf(editTextY.getText().toString());

		Intent returnIntent = new Intent();
		returnIntent.putExtra("x", x);
		returnIntent.putExtra("y", y);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	private void setVisibilities(int visibility) {
		findViewById(R.id.textViewXActivitySetXAndFX).setVisibility(visibility);
		findViewById(R.id.editTextXActivitySetXAndFX).setVisibility(visibility);
		findViewById(R.id.textViewYActivitySetXAndFX).setVisibility(visibility);
		findViewById(R.id.editTextYActivitySetXAndFX).setVisibility(visibility);
		findViewById(R.id.buttonNextActivitySetXAndFX)
				.setVisibility(visibility);
		findViewById(R.id.buttonPreviousActivitySetXAndFX).setVisibility(
				visibility);
	}
}
