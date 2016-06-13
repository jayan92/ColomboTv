package com.xicigny.colombotv.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xicigny.colombotv.R;

import im.delight.android.webview.AdvancedWebView;


public class WebActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        switch (query){
            case "Adaheraya":
                break;
            case "Ape Uruma Kama":
                break;
            case "Athinatha":
                break;
            case "Colombo Kitchen":
                break;
            case "Colombo Paththgara":
                break;
            case "Colombo Sajje":
                break;
            case "Colombo Top 10":
                break;
            case "Internet":
                break;
            case "Lassana Baila":
                break;
            case "Nadun Sewana":
                break;
            case "Charikawa":
                break;
            case "Ratu Katta":
                break;
            case "Sihina Maliga":
                break;
            case "Ras":
                break;
            case "Back Stage":
                break;
            case "Wedalla":
                break;
            default:
                break;
        }
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl("https://www.youtube.com/channel/UCP_kTskrJPfLiYO6ua7OitA/search?query="+query);

        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) { }

    @Override
    public void onPageFinished(String url) { }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) { }

    @Override
    public void onDownloadRequested(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) { }

    @Override
    public void onExternalPageRequest(String url) { }
}
