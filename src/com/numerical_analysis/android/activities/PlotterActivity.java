package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import com.numerical_analysis.android.methods.OneVariableEquations;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class PlotterActivity extends Activity {

	private OneVariableEquations oneVariableEquations;
	private WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plotter);
		oneVariableEquations = (OneVariableEquations) getIntent()
				.getSerializableExtra("oneVariableEquations");

		webView = (WebView) findViewById(R.id.webview_plotter);
		webView.getSettings().setJavaScriptEnabled(true);

		webView.loadUrl("http://192.168.43.12/graphplotter/newgraph.php?highquality=1&zoom=50&func0="
				+ oneVariableEquations.getFunction());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_plotter, menu);
		return true;
	}
}
