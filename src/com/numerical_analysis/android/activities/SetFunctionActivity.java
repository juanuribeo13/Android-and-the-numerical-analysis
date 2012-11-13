package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class SetFunctionActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_function);

		String function = (String) getIntent().getSerializableExtra("function");
		if (function != null) {
			EditText textFunction = (EditText) findViewById(R.id.editTextFunctionActivitySetFunction);
			textFunction.setText(function);
		}
	}

	/**
	 * Called when the user clicks on the set button and returns the function
	 * entered by the user to the caller
	 * 
	 * @param view
	 */
	public void onSetButtonClick(View view) {
		EditText function = (EditText) findViewById(R.id.editTextFunctionActivitySetFunction);

		Intent returnIntent = new Intent();
		returnIntent.putExtra("Function", function.getText().toString());
		setResult(RESULT_OK, returnIntent);
		finish();
	}
}
