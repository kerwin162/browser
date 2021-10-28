package top.itjl.browser.view.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.browser.R;

/**
 * @author: Kerwin
 * @date: 2021/10/28
 */
public class WebTitleBar extends CardView implements View.OnClickListener {
    private final static int SEARCH_MODEL = 0x001;
    private final static int REFRESH_MODEL = 0x002;

    private WebView mWebView;
    private View homeBtn, menuBtn;
    private ImageView searchBtn;
    private EditText inputEdit;
    private boolean pageLoaded;
    private int currentSearchModel = SEARCH_MODEL;
    private TitleMenuListener listener;
    private boolean menuClosed = true;

    private String homeUrl = "http://www.baidu.com";


    public WebTitleBar(@NonNull Context context) {
        super(context, null);
    }

    public WebTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
//        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WebTitleBar, defStyleAttr, 0);
        LayoutInflater.from(context).inflate(R.layout.weight_web_title_bar, this, true);
//        setElevation(DisplayUtils.px2dip(context, 10));
//        rootView = inflate(context, R.layout.include_web_title_bar, this);
        homeBtn = findViewById(R.id.web_title_iv_btn_home);
        menuBtn = findViewById(R.id.web_title_iv_btn_menu);
        inputEdit = findViewById(R.id.web_title_et_url_input);
        searchBtn = findViewById(R.id.web_title_iv_search_btn);

        homeBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        inputEdit.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSearchModel(SEARCH_MODEL);
            }
        });
        inputEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                //处理事件
                updateSearchModel(SEARCH_MODEL);
                onSearchClicked();
            }
            return false;
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_title_iv_btn_menu:
                onMenuClicked();
                break;
            case R.id.web_title_iv_btn_home:
                onHomeClicked();
                break;
            case R.id.web_title_iv_search_btn:
                onSearchClicked();
        }
    }

    public void setListener(TitleMenuListener listener) {
        this.listener = listener;
    }

    public void setWebView(WebView webView) {
        this.mWebView = webView;
    }

    public void pageLoaded() {
        this.pageLoaded = true;
        updateSearchModel(REFRESH_MODEL);
    }

    private void onSearchClicked() {
        if (currentSearchModel == SEARCH_MODEL) {
            mWebView.loadUrl(inputEdit.getText().toString());
            updateSearchModel(REFRESH_MODEL);
        } else {
            startRefreshAnimation();
            mWebView.reload();
        }
    }

    private void startRefreshAnimation() {

    }

    private void endRefreshAnimation() {

    }

    private void onHomeClicked() {
        mWebView.loadUrl(homeUrl);
    }

    private void onMenuClicked() {
        menuClosed = !menuClosed;
        if (listener != null) listener.onMenuOption(menuClosed);

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void updateSearchModel(int model) {
        currentSearchModel = model;
        searchBtn.post(() -> searchBtn.setImageDrawable(
                getContext().getDrawable(
                        currentSearchModel == SEARCH_MODEL
                                ? R.drawable.ic_baseline_search_24
                                : R.drawable.ic_baseline_refresh_24)));
    }

    public void loadHome() {
        onHomeClicked();
    }

    public void updateUrl(String url, Bitmap favicon) {
        inputEdit.setText(url);
        inputEdit.setCompoundDrawables(new BitmapDrawable(favicon), null, null, null);
    }

    interface TitleMenuListener {
        void onMenuOption(boolean open);
    }
}
