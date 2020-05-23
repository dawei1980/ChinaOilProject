package com.china.oil.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.china.oil.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_username_et)
    EditText login_username_et;
    @BindView(R.id.login_password_et)
    EditText login_password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initView();
    }

    @SuppressLint("MissingPermission")
    private void initView() {


    }

    @OnClick({R.id.login_ok_btn,R.id.back_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.login_ok_btn:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
