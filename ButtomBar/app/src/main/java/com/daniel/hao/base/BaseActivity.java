package com.daniel.hao.base;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by 95 on 2016/9/19.
 */
public class BaseActivity extends AppCompatActivity {

    Toast mToast;

    public void showToast(final String text) {
        if (!TextUtils.isEmpty(text) && !isFinishingActivity()) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

    public void showToast(final int resId) {
        if (resId != 0 && !isFinishingActivity()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), resId,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(resId);
                    }
                    mToast.show();
                }
            });
        }
    }

    public boolean isFinishingActivity() {
        return this.isFinishing();
    }

}
