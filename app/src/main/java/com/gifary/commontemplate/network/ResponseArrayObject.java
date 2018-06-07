package com.gifary.commontemplate.network;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gifary on 6/7/18.
 * example response from server
 * {
 *     data:[],
 *     error: 0
 *     message: "string"
 * }
 */

public class ResponseArrayObject {
    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private int error;

    @SerializedName("data")
    private JsonArray data;

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

    public JsonArray getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }
}
