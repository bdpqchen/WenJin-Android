package com.twt.service.wenjin.ui.login_sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.twt.service.wenjin.R;
import com.twt.service.wenjin.api.ApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2016/7/20.
 */
public class LoginSignActivity extends AppCompatActivity {
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.bt_signup)
    Button btSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign);
        ButterKnife.bind(this);


        ApiClient.userLogout();
    }

    @OnClick({R.id.bt_login, R.id.bt_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                LoginSignWebActivity.actionStart(this,"login");
                break;
            case R.id.bt_signup:
                LoginSignWebActivity.actionStart(this,"signup");
                break;
        }
//        finish();
    }
}
