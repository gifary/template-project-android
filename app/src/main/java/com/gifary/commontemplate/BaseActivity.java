package com.gifary.commontemplate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import es.dmoral.toasty.Toasty;

/**
 * Created by gifary on 6/7/18.
 */

public class BaseActivity extends AppCompatActivity {

    private String TAG = BaseActivity.class.getSimpleName();
    protected Context context;
    protected Activity activity;
    private ProgressDialog progressDialog;
    protected Intent intent;

    public BaseActivity() {

    }

    public void showInfoToast(String text){
        Toasty.info(context,text).show();
    }

    public void showDangerToast(String text){
        Toasty.error(context,text).show();
    }

    public void showProgressDialog(String title, String message){
        progressDialog = new ProgressDialog(context,R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
    }

    public void showProgress(){
        progressDialog.show();
    }

    public void hiddenProgress(){
        progressDialog.dismiss();
    }

    public void initIntent(Class nextClass){
        intent = new Intent(context,nextClass);
    }

    public void nextActivity(boolean statusFinish){
        startActivity(intent);
        if(statusFinish){
            finish();
        }
    }

    public void addStringExtra(String key, String content){
        intent.putExtra(key,content);
    }

    public void addIntExtra(String key, int content){
        intent.putExtra(key,content);
    }

    public String getStringExtra(String key){
        return intent.getStringExtra(key);
    }

    public int getIntExtra(String key){
        return intent.getIntExtra(key,0);
    }

    public Object getObjectExtra(String key){
        return intent.getParcelableExtra(key);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
