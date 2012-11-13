package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import android.os.Bundle;
import android.webkit.WebView;
import android.annotation.SuppressLint;
import android.app.Activity;

public class HelpActivity extends Activity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		String url = getIntent().getStringExtra("url");
		WebView webView = (WebView) findViewById(R.id.webViewHelp);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
	}
}
