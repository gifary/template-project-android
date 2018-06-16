package com.gifary.commontemplate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gifary.commontemplate.app.MainActivity;
import com.gifary.commontemplate.app.user.ForgotActivity;
import com.gifary.commontemplate.app.user.RegisterActivity;
import com.gifary.commontemplate.configuration.EnableModule;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity  {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;
    @BindView(R.id.link_forgot_password) TextView _forgotPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context =this;
        activity = LoginActivity.this;

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        if(!EnableModule.REGISTER){
            _signupLink.setVisibility(View.GONE);
        }

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                initIntent(RegisterActivity.class);
                addStringExtra("email",_emailText.getText().toString());
                nextActivity(false);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        if(!EnableModule.RESET_PASSWORD){
            _forgotPassword.setVisibility(View.GONE);
        }
        _forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                initIntent(ForgotActivity.class);
                nextActivity(false);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        initProgressDialog("Connecting","Try to loggin");
        showProgress();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        hiddenProgress();

                    }
                }, 3000);
    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        showInfoToast("Success login");
        _loginButton.setEnabled(true);
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        showDangerToast("failed login");
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}

