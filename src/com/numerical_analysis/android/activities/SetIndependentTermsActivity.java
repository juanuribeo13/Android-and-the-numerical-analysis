package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.methods.systems_of_equations.IterativeMethods;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SetIndependentTermsActivity extends Activity {

	private IterativeMethods iterativeMethods;
	private String[] independentTerms;
	int term;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_independent_terms);

		iterativeMethods = (IterativeMethods) getIntent().getSerializableExtra(
				"iterativeMethods");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_set_independent_terms, menu);
		return true;
	}

	public void onSetButtonClick(View view) {
		EditText editTextN = (EditText) findViewById(R.id.editTextNActivitySetIndependentTerms);
		int n = Integer.valueOf(editTextN.getText().toString());
		independentTerms = new String[n];

		term = 0;
		TextView viewTerm = (TextView) findViewById(R.id.textViewTermToEnter);
		viewTerm.setText("Please insert x" + (term + 1));

		viewTerm.setVisibility(View.VISIBLE);
		findViewById(R.id.editTextTermActivitySetIndependentTerms)
				.setVisibility(View.VISIBLE);
		findViewById(R.id.buttonNextActivitySetIndependentTerms).setVisibility(
				View.VISIBLE);
	}

	public void onNextButtonClick(View view) {
		if (term < independentTerms.length) {
			EditText editTextTerm = (EditText) findViewById(R.id.editTextTermActivitySetIndependentTerms);
			independentTerms[term] = editTextTerm.getText().toString();
			term++;
		}
		if (term != independentTerms.length) {
			TextView viewTerm = (TextView) findViewById(R.id.textViewTermToEnter);
			viewTerm.setText("Please insert x" + (term + 1));
		} else {
			findViewById(R.id.buttonNextActivitySetIndependentTerms)
					.setVisibility(View.INVISIBLE);
			findViewById(R.id.buttonFinishActivitySetIndependentTerms)
					.setVisibility(View.VISIBLE);
		}
	}

	public void onFinishButtonClick(View view) {
		iterativeMethods.setIndependentTerms(independentTerms);
		Intent returnIntent = new Intent();
		setResult(RESULT_OK, returnIntent);
		finish();
	}
}
