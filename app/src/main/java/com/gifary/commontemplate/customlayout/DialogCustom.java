package com.gifary.commontemplate.customlayout;

import android.app.ProgressDialog;
import android.content.Context;

import com.gifary.commontemplate.R;

/**
 * Created by gifary on 6/14/18.
 */

public class DialogCustom {
    private Context context;
    private ProgressDialog progressDialog;

    public DialogCustom(Context context,String title,String message) {
        this.context = context;
        progressDialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
    }



    public void show(){
        progressDialog.show();
    }

    public void dismiss(){
        progressDialog.dismiss();
    }
}
