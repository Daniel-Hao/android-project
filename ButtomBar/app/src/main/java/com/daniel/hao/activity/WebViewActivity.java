package com.daniel.hao.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daniel.hao.base.BaseActivity;


/**
 * Created by zhuyifei on 2016/5/12.
 */
public class WebViewActivity extends BaseActivity {

    private static final String EXTRA_URL = "WebViewActivity_url";
    private static final String EXTRA_TITLE = "WebViewActivity_title";

    private WebView mWebView;
    private ProgressDialog mProgressDialog;
    private String mUrl;
    private String mTitle;

    public static void startWebViewActivity(Context context, String url) {
        startWebViewActivity(context, url, null);
    }

    public static void startWebViewActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        handlerIntent();
        actionBar.setTitle((TextUtils.isEmpty(mTitle) ? "" : mTitle));
        mWebView = (WebView) findViewById(R.id.webview);

        initData();

    }


    private void initData() {
        createWebViewClient();
        initWebView();
        startLoadUrl();
    }

    private void startLoadUrl() {
        if (haveNetworkConnection()) {
            if (!TextUtils.isEmpty(mUrl)) {
                mWebView.loadUrl(mUrl);
            } else {
                showToast("url cannot be null");
                finish();
            }
        } else {
            mWebView.loadUrl("file:///android_asset/error.html");
        }
    }

    private void handlerIntent() {
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void createWebViewClient() {
        mWebView.setWebChromeClient(new WebChromeClient()); //辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        mWebView.setWebViewClient(new WebViewClient() {  //处理各种通知、请求事件的

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if (mProgressDialog == null) {
                    // in standard case YourActivity.this
                    mProgressDialog = new ProgressDialog(WebViewActivity.this);
                    mProgressDialog.setMessage("正在加载。。。");
                    mProgressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(false);
        mWebView.getSettings().setBuiltInZoomControls(true);
        //TODO 根据需要设置
       /* mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setLayoutAlgorithm(mWebView.getSettings().getLayoutAlgorithm().NORMAL);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(false);
        mWebView.setSoundEffectsEnabled(true);
        mWebView.setHorizontalFadingEdgeEnabled(false);
        mWebView.setKeepScreenOn(true);
        mWebView.setScrollbarFadingEnabled(true);
        mWebView.setVerticalFadingEdgeEnabled(false);*/
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}
