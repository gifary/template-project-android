package com.gifary.commontemplate.customlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.gifary.commontemplate.R;

/**
 * Created by gifary on 6/7/18.
 */

public class MyTextView extends AppCompatTextView {
    String customFont;

    public MyTextView(Context context) {
        super(context);

    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        style(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        style(context, attrs);
    }

    private void style(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MyTextView);
        int cf = a.getInteger(R.styleable.MyTextView_fontName, 0);
        int fontName = 0;
        switch (cf)
        {
            case 1:
                fontName = R.string.Roboto_Bold;
                break;
            case 2:
                fontName = R.string.Roboto_Italic;
                break;
            case 3:
                fontName = R.string.Roboto_Light;
                break;
            case 4:
                fontName = R.string.Roboto_Medium;
                break;
            case 5:
                fontName = R.string.Roboto_Regular;
                break;
            case 6:
                fontName = R.string.Roboto_Thin;
                break;
            default:
                fontName = R.string.Roboto_Regular;
                break;
        }

        customFont = getResources().getString(fontName);

        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "font/" + customFont + ".ttf");
        setTypeface(tf);
        a.recycle();
    }
}
