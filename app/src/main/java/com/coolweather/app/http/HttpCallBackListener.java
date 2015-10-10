package com.coolweather.app.http;

/**
 * Created by ice_coffee on 2015/10/10.
 * 疑问之处 $$$
 */
public interface HttpCallBackListener
{
    void onFinish(String response);

    void onError(Exception e);
}
