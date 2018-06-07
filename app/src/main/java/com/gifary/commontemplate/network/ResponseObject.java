package com.gifary.commontemplate.network;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gifary on 6/7/18.
 * example response from server
 * {
 *     data:{
 *         "key1":value1,
 *         "key2":value2
 *     },
 *     error: 0
 *     message: "string"
 * }
 */

public class ResponseObject {
    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private int error;

    @SerializedName("data")
    private JsonObject data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
