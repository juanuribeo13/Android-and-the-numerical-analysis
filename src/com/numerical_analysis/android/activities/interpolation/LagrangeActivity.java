package com.numerical_analysis.android.activities.interpolation;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.activities.SetXAndFXActivity;
import com.numerical_analysis.android.adapters.interpolation.NewtonExecutionTableAdapter;
import com.numerical_analysis.android.exceptions.DivisionByZeroException;
import com.numerical_analysis.android.methods.Interpolation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LagrangeActivity extends Activity {

	static final int SET_X_AND_FX = 0;
	private Interpolation interpolation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lagrange);

		interpolation = (Interpolation) getIntent().getSerializableExtra(
				"interpolation");

		double[] x = new double[5];
		double[] y = new double[5];

		x[0] = 2.0;
		y[0] = 8.0;
		x[1] = 2.2;
		y[1] = 13.584;
		x[2] = 2.4;
		y[2] = 20.432;
		x[3] = 2.6;
		y[3] = 28.688;
		x[4] = 2.8;
		y[4] = 38.496;

		interpolation.setX(x);
		interpolation.setY(y);

		if (interpolation.getX() == null) {
			setvisibilities(View.GONE);
		} else {
			setvisibilities(View.VISIBLE);
			executeMethod();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_interpolation, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == SET_X_AND_FX) {
				interpolation.setX(data.getDoubleArrayExtra("x"));
				interpolation.setY(data.getDoubleArrayExtra("y"));
				setvisibilities(View.VISIBLE);
				executeMethod();
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {

		case R.id.menu_set_x_and_fx:
			Intent intent = new Intent(this, SetXAndFXActivity.class);
			intent.putExtra("x", interpolation.getX());
			intent.putExtra("y", interpolation.getY());
			startActivityForResult(intent, SET_X_AND_FX);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("interpolation", interpolation);
		setResult(RESULT_OK, intent);
		super.onBackPressed();
	}

	private void executeMethod() {
		TextView textPolynomial = (TextView) findViewById(R.id.textViewPolynomialActivityLagrange);
		try {
			// TODO change for Lagrange
			String polynomial = interpolation.newton();
			textPolynomial.setText(polynomial);
		} catch (DivisionByZeroException e) {
			textPolynomial.setText(e.getMessage());
		}
		enableExecutionTable();
	}

	public void onCalculateButtonClick(View view) {
		TextView textPolynomial = (TextView) findViewById(R.id.textViewPolynomialActivityLagrange);
		String polynomial = textPolynomial.getText().toString();
		TextView textResult = (TextView) findViewById(R.id.textViewEvaluationActivityLagrange);
		EditText editValue = (EditText) findViewById(R.id.editTextXActivityLagrange);
		double value = Double.valueOf(editValue.getText().toString());
		textResult.setText(String.valueOf(interpolation.evaluatePolynomial(
				polynomial, value)));
	}

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewPolynomialActivityLagrange).setVisibility(
				visibility);
		findViewById(R.id.textViewEvaluationActivityLagrange).setVisibility(
				visibility);
		findViewById(R.id.editTextXActivityLagrange).setVisibility(visibility);
		findViewById(R.id.buttonCalculateActivityLagrange).setVisibility(
				visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityLagrange).setVisibility(
					View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityLagrange).setVisibility(
					View.VISIBLE);
		}
	}

	public void showExecutionTable(View view) {
		NewtonExecutionTableAdapter adapter = new NewtonExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", interpolation);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityLagrange).setVisibility(
				View.VISIBLE);
	}
}
