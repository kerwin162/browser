package top.itjl.browser.view.weight;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author: Kerwin
 * @date: 2021/10/28
 */
public class TextWatcherAdapter implements TextWatcher {
    private CharSequence oldText;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        oldText = s;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void afterTextChanged(Editable oldText, Editable newText) {

    }
}
