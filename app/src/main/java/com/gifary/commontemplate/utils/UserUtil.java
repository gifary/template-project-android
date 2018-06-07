package com.gifary.commontemplate.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.gifary.commontemplate.configuration.Constants;
import com.gifary.commontemplate.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gifary on 6/7/18.
 */

public class UserUtil {
    private static UserUtil instance;

    private SharedPreferences preferences;

    public static UserUtil getInstance(Context context) {
        if (instance == null) {
            instance = new UserUtil();
            instance.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }

        return instance;
    }

    public String getStringProperty(String key) {
        return preferences.getString(key, "");
    }

    public void setStringProperty(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value).apply();
    }

    public boolean getBooleanProperty(String key) {
        return preferences.getBoolean(key, false);
    }

    public void setBooleanProperty(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value).apply();
    }

    public int getIntProperty(String key) {
        return preferences.getInt(key, 0);
    }

    public void setIntProperty(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value).apply();
    }

    public float getFloatProperty(String key) {
        return preferences.getFloat(key, 0.0F);
    }

    public void setFloatProperty(String key, float value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value).apply();
    }

    /**
     * Clearing all saved preferences, used for logging out
     */
    public void reset() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
    }

    public void removeByKey(String key){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key).commit();
    }

    public boolean isLoggedIn() {
        return !getEmail().isEmpty();
    }

    public void signIn(User user) {
        setIntProperty(Constants.USER_ID,user.getId());
        setStringProperty(Constants.USER_EMAIL,user.getEmail());
        setStringProperty(Constants.USERNAME,user.getUsername());
        setStringProperty(Constants.JWTACCESSTOKEN,user.getJwtAccessToken());
        setStringProperty(Constants.NAME,user.getName());
        setStringProperty(Constants.NIP,user.getNip());
        setStringProperty(Constants.PHONE,user.getPhone());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        setStringProperty(Constants.LAST_LOGIN,dateFormat.format(date));
    }

    public String getLastLogin(){
        return getStringProperty(Constants.LAST_LOGIN);
    }

    public void signOut() {
        reset();
    }

    public String getJWTTOken(){
        return getStringProperty(Constants.JWTACCESSTOKEN);
    }

    public String getToken() {
        return getStringProperty(Constants.USER_TOKEN);
    }

    public String getEmail() {
        return getStringProperty(Constants.USER_EMAIL);
    }

    public String getUsername() {
        return getStringProperty(Constants.USERNAME);
    }

    public String getPhone() {
        return getStringProperty(Constants.PHONE);
    }

    public String getNip() {
        return getStringProperty(Constants.NIP);
    }

    public String getId() {
        return getStringProperty(Constants.USER_ID);
    }

    public String getRoleUser(){
        return getStringProperty(Constants.USER_ROLE);
    }

}
