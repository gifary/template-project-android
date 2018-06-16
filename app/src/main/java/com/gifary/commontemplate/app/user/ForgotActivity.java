package com.gifary.commontemplate.app.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gifary.commontemplate.BaseActivity;
import com.gifary.commontemplate.LoginActivity;
import com.gifary.commontemplate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotActivity extends BaseActivity {

    @BindView(R.id.et_email) EditText etEmail;

    @BindView(R.id.link_login) TextView _linkLogin;

    @BindView(R.id.btn_forgot) Button _btnForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_activity);

        ButterKnife.bind(this);

        context =this;
        activity = ForgotActivity.this;

        _linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initIntent(LoginActivity.class);
                nextActivity(false);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initProgressDialog("Connection","Reset password");
                showProgress();
                checkEmail();
            }
        });
    }

    private void checkEmail(){
        if(!validate()){
            hiddenProgress();
            return;
        }

        new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {
                    // On complete call either onLoginSuccess or onLoginFailed

                    hiddenProgress();

                }
            }, 3000);
    }

    private boolean validate(){
        boolean valid =true;
        String email = etEmail.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        return valid;
    }
}
