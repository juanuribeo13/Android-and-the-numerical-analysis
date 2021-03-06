package com.numerical_analysis.android.activities.interpolation;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.HelpActivity;
import com.numerical_analysis.android.activities.SetXAndFXActivity;
import com.numerical_analysis.android.methods.Interpolation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LagrangeActivity extends Activity {

	static final int SET_X_AND_FX = 0;
	private Interpolation interpolation;
	private String[] equations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lagrange);

		interpolation = (Interpolation) getIntent().getSerializableExtra(
				"interpolation");

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
		case R.id.menu_help:
			intent = new Intent(this, HelpActivity.class);
			intent.putExtra(
					"url",
					"https://sites.google.com/site/numericalanalysiseafit/topics/interpolation/lagrange");
			startActivity(intent);
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
		equations = interpolation.lagrange();
		textPolynomial.setText("P(x)=" + equations[0]);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLActivityLagrange);
		linearLayout.removeAllViews();
		for (int i = 1; i < equations.length; i++) {
			TextView textView = new TextView(this);
			textView.setText("L" + (i - 1) + "(x)=" + equations[i]);
			linearLayout.addView(textView);
		}
	}

	public void onCalculateButtonClick(View view) {
		try {
			EditText editValue = (EditText) findViewById(R.id.editTextXActivityLagrange);
			double value = Double.valueOf(editValue.getText().toString());
			double[] results = interpolation.lagrange(equations, value);

			TextView textPolynomial = (TextView) findViewById(R.id.textViewPolynomialActivityLagrange);
			textPolynomial.setText("P(x)=" + results[0]);
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLActivityLagrange);
			linearLayout.removeAllViews();
			for (int i = 1; i < equations.length; i++) {
				TextView textView = new TextView(this);
				textView.setText("L" + (i - 1) + "(x)=" + results[i]);
				linearLayout.addView(textView);
			}
		} catch (NumberFormatException e) {
			Toast.makeText(this, "Please enter X0 to continue",
					Toast.LENGTH_SHORT).show();
		}
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
}
