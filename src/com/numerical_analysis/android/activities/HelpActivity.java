package com.numerical_analysis.android.activities;

import com.numerical_analysis.android.R;
import android.os.Bundle;
import android.webkit.WebView;
import android.app.Activity;

public class HelpActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		String url = getIntent().getStringExtra("url");
		WebView webView = (WebView) findViewById(R.id.webViewHelp);
		webView.loadUrl(url);
	}
}
