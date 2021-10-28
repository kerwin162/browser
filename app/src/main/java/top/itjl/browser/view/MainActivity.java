package top.itjl.browser.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import top.itjl.browser.R;
import top.itjl.browser.view.base.BaseActivity;
import top.itjl.browser.view.weight.WebTitleBar;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.web_content)
    WebView mVebView;
    @BindView(R.id.main_layout_title)
    WebTitleBar webTitleBar;
    @BindView(R.id.main_loading_progress)
    ProgressBar loadingProgressBar;

    private final MWebViewClient webViewClient = new MWebViewClient();
    private final MWebChromeClient webChromeClient = new MWebChromeClient();

    private boolean canExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webTitleBar.setWebView(mVebView);
        initView();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    private void initView() {
        loadingProgressBar.setMax(100);
        WebSettings setting = mVebView.getSettings();
        setting.setAllowFileAccess(true);
        setting.setCacheMode(WebSettings.LOAD_NORMAL);

        mVebView.setWebViewClient(webViewClient);
        mVebView.setWebChromeClient(webChromeClient);

        String ua = setting.getUserAgentString();
        log.error("UA => " + ua);

        webTitleBar.loadHome();
    }

    @Override
    public void onBackPressed() {
        if (mVebView.canGoBack()) {
            mVebView.goBack();
        } else {
            if (canExit) {
                super.onBackPressed();
            } else {
                canExit = true;
                Toast.makeText(this, "再次返回将退出应用！", Toast.LENGTH_LONG).show();
                mVebView.postDelayed(() -> canExit = false, 1500);
            }
        }
    }

    private class MWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadingProgressBar.setVisibility(View.GONE);
            webTitleBar.pageLoaded();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webTitleBar.updateUrl(url,favicon);
            loadingProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private class MWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            loadingProgressBar.setProgress(newProgress);
            if (newProgress >= 100) webViewClient.onPageFinished(view, "");
        }
    }
}