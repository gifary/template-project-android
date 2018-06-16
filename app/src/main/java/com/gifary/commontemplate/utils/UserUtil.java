package com.gifary.commontemplate.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.gifary.commontemplate.models.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gifary on 6/7/18.
 */

public class UserUtil extends BaseUtil {
    private static UserUtil instance;
    public static final String USER_ID="id";
    public static final String USER_ROLE="user_role";
    public static final String USER_TOKEN = "token_key";
    public static final String USER_EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String NAME = "name";
    public static final String NIP = "nip";
    public static final String PHONE = "phone";
    public static final String LAST_LOGIN = "last_login";
    public static final String JWTACCESSTOKEN = "jwt_access_token";

    public static UserUtil getInstance(Context context) {
        if (instance == null) {
            instance = new UserUtil();
            instance.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }

        return instance;
    }

    public boolean isLoggedIn() {
        return !getEmail().isEmpty();
    }

    public void signIn(User user) {
        setIntProperty(USER_ID,user.getId());
        setStringProperty(USER_EMAIL,user.getEmail());
        setStringProperty(USERNAME,user.getUsername());
        setStringProperty(JWTACCESSTOKEN,user.getJwtAccessToken());
        setStringProperty(NAME,user.getName());
        setStringProperty(NIP,user.getNip());
        setStringProperty(PHONE,user.getPhone());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        setStringProperty(LAST_LOGIN,dateFormat.format(date));
    }

    public String getLastLogin(){
        return getStringProperty(LAST_LOGIN);
    }

    public void signOut() {
        reset();
    }

    public String getJWTTOken(){
        return getStringProperty(JWTACCESSTOKEN);
    }

    public String getToken() {
        return getStringProperty(USER_TOKEN);
    }

    public String getEmail() {
        return getStringProperty(USER_EMAIL);
    }

    public String getUsername() {
        return getStringProperty(USERNAME);
    }

    public String getPhone() {
        return getStringProperty(PHONE);
    }

    public String getNip() {
        return getStringProperty(NIP);
    }

    public String getId() {
        return getStringProperty(USER_ID);
    }

    public String getRoleUser(){
        return getStringProperty(USER_ROLE);
    }

}
