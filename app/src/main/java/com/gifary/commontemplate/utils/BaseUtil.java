package com.gifary.commontemplate.utils;

import android.content.SharedPreferences;

/**
 * Created by gifary on 6/14/18.
 */

public class BaseUtil {
    protected SharedPreferences preferences;

    protected String getStringProperty(String key) {
        return preferences.getString(key, "");
    }

    protected void setStringProperty(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value).apply();
    }

    protected boolean getBooleanProperty(String key) {
        return preferences.getBoolean(key, false);
    }

    protected void setBooleanProperty(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value).apply();
    }

    protected int getIntProperty(String key) {
        return preferences.getInt(key, 0);
    }

    protected void setIntProperty(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value).apply();
    }

    protected float getFloatProperty(String key) {
        return preferences.getFloat(key, 0.0F);
    }

    protected void setFloatProperty(String key, float value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value).apply();
    }

    /**
     * Clearing all saved preferences, used for logging out
     */
    protected void reset() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
    }

    protected void removeByKey(String key){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key).commit();
    }
}
