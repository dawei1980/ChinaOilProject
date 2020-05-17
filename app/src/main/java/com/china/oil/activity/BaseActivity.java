package com.china.oil.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /** 显示吐司 **/
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
