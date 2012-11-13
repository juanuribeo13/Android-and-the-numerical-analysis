package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SetIndependentTermsActivity extends Activity {

	private String[] independentTerms;
	int term;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_independent_terms);

		independentTerms = (String[]) getIntent().getSerializableExtra(
				"independentTerms");

		if (independentTerms != null) {
			term = 0;
			EditText editTerm = (EditText) findViewById(R.id.editTextTermActivitySetIndependentTerms);
			editTerm.setText(independentTerms[term]);
			setVisibilities(View.VISIBLE);

			TextView viewTerm = (TextView) findViewById(R.id.textViewTermToEnter);
			viewTerm.setText("Please insert x" + (term + 1));
		}
	}

	public void onSetButtonClick(View view) {
		EditText editTextN = (EditText) findViewById(R.id.editTextNActivitySetIndependentTerms);
		int n = Integer.valueOf(editTextN.getText().toString());
		independentTerms = new String[n];

		term = 0;
		TextView viewTerm = (TextView) findViewById(R.id.textViewTermToEnter);
		viewTerm.setText("Please insert x" + (term + 1));

		setVisibilities(View.VISIBLE);
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

			EditText editTerm = (EditText) findViewById(R.id.editTextTermActivitySetIndependentTerms);
			editTerm.setText(independentTerms[term]);
		} else {
			findViewById(R.id.buttonNextActivitySetIndependentTerms)
					.setVisibility(View.INVISIBLE);
			findViewById(R.id.buttonFinishActivitySetIndependentTerms)
					.setVisibility(View.VISIBLE);
		}
	}

	public void onFinishButtonClick(View view) {
		Intent returnIntent = new Intent();
		returnIntent.putExtra("independentTerms", independentTerms);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	private void setVisibilities(int visibility) {
		findViewById(R.id.textViewTermToEnter).setVisibility(visibility);
		findViewById(R.id.editTextTermActivitySetIndependentTerms)
				.setVisibility(visibility);
		findViewById(R.id.buttonNextActivitySetIndependentTerms).setVisibility(
				visibility);
	}
}
