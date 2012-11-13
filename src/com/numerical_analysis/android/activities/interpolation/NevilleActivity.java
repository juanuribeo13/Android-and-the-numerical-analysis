package com.numerical_analysis.android.activities.interpolation;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.activities.ExecutionTableActivity;
import com.numerical_analysis.android.activities.SetXAndFXActivity;
import com.numerical_analysis.android.adapters.interpolation.NevilleExecutionTableAdapter;
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

public class NevilleActivity extends Activity {

	static final int SET_X_AND_FX = 0;
	private Interpolation interpolation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_neville);

		interpolation = (Interpolation) getIntent().getSerializableExtra(
				"interpolation");

		if (interpolation.getX() == null) {
			setvisibilities(View.GONE);
		} else {
			setvisibilities(View.VISIBLE);
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

	public void onCalculateButtonClick(View view) {
		TextView textResult = (TextView) findViewById(R.id.textViewEvaluationActivityNeville);
		EditText editValue = (EditText) findViewById(R.id.editTextXActivityNeville);
		double value = Double.valueOf(editValue.getText().toString());
		double result;
		try {
			result = interpolation.neville(value);
			textResult.setText(String.valueOf(result));
			enableExecutionTable();
		} catch (DivisionByZeroException e) {
			textResult.setText(e.getMessage());
			enableExecutionTable();
		}

	}

	private void setvisibilities(int visibility) {
		findViewById(R.id.textViewEvaluationActivityNeville).setVisibility(
				visibility);
		findViewById(R.id.editTextXActivityNeville).setVisibility(visibility);
		findViewById(R.id.buttonCalculateActivityNeville).setVisibility(
				visibility);
		if (visibility == View.VISIBLE) {
			findViewById(R.id.textViewWarningActivityNeville).setVisibility(
					View.GONE);
		} else {
			findViewById(R.id.textViewWarningActivityNeville).setVisibility(
					View.VISIBLE);
		}
	}

	public void showExecutionTable(View view) {
		NevilleExecutionTableAdapter adapter = new NevilleExecutionTableAdapter();
		Intent intent = new Intent(this, ExecutionTableActivity.class);
		intent.putExtra("methodGroup", interpolation);
		intent.putExtra("adapter", adapter);
		startActivity(intent);
	}

	private void enableExecutionTable() {
		findViewById(R.id.buttonExecutionTableActivityNeville).setVisibility(
				View.VISIBLE);
	}
}
