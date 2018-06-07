package com.gifary.commontemplate.app.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gifary.commontemplate.BaseActivity;
import com.gifary.commontemplate.LoginActivity;
import com.gifary.commontemplate.R;

import butterknife.BindView;

public class ForgotActivityActivity extends BaseActivity {

    @BindView(R.id.input_email)
    EditText _inputEmail;

    @BindView(R.id.link_sigin)
    TextView _linkSigin;

    @BindView(R.id.btn_forgot)
    Button _btnForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_activity);
        context =this;
        activity = ForgotActivityActivity.this;


        _linkSigin.setOnClickListener(new View.OnClickListener() {
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
                showProgressDialog("Connection","Reset password");
                showProgress();
                checkEmail();
            }
        });
    }

    private void checkEmail(){
        if(!validate()){
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
        String email = _inputEmail.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _inputEmail.setError("enter a valid email address");
            valid = false;
        } else {
            _inputEmail.setError(null);
        }

        return valid;
    }
}
