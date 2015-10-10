package com.coolweather.app.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ice_coffee on 2015/10/10.
 * 疑问之处 $$$
 */
public class HttpUtil
{
    public static void sendHttpReuqest(final String address, final HttpCallBackListener listener)
    {
        new Thread(new Runnable(){
            @Override
            public void run()
            {
                HttpURLConnection connection = null;

                try
                {
                    URL url = new URL(address);

                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");

                    connection.setConnectTimeout(8000);

                    connection.setReadTimeout(8000);

                    InputStream in = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

//                    StringBuffer
                }catch (Exception e)
                {
                    if (null != listener)
                    {
                        listener.onError(e);
                    }
                }finally
                {
                    if (null != connection)
                    {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}

