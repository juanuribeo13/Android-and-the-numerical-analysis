package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.methods.ErrorTheory;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ErrorTheoryActivity extends Activity {

	ErrorTheory errorTheory;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_error_theory);

		errorTheory = new ErrorTheory();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_error_theory, menu);
		return true;
	}

	public void onSmallestPositiveNumberSimpleButtonClick(View view) {
		Float result = errorTheory.smallestPositiveNumberSimple();
		TextView textView = (TextView) findViewById(R.id.TextViewResultActivityErrorTheory);
		textView.setText(result.toString());
	}

	public void onSmallestPositiveNumberDoubleButtonClick(View view) {
		Double result = errorTheory.smallestPositiveNumberDouble();
		TextView textView = (TextView) findViewById(R.id.TextViewResultActivityErrorTheory);
		textView.setText(result.toString());
	}

	public void onMachineEpsilonSimpleButtonClick(View view) {
		Float result = errorTheory.machineEpsilonSimple();
		TextView textView = (TextView) findViewById(R.id.TextViewResultActivityErrorTheory);
		textView.setText(result.toString());
	}

	public void onMachineEpsilonDoubleButtonClick(View view) {
		Double result = errorTheory.machineEpsilonDouble();
		TextView textView = (TextView) findViewById(R.id.TextViewResultActivityErrorTheory);
		textView.setText(result.toString());
	}
}
