package com.example.dllo.yohomix.tools;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/11/28.
 */

public class GsonRequest<T> extends Request<T>{
    private Gson mGson;
    private Class<T> mClass;
    private Response.Listener<T> mListener;
    private HashMap<String, String> map;

    public GsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    public GsonRequest(int method, String url, Response.ErrorListener listener, Class<T> aClass, Response.Listener<T> listener1) {
        super(method, url, listener);
        mClass = aClass;
        mListener = listener1;
        mGson = new Gson();
    }

    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> listener1, Class<T> aClass, HashMap<String, String> map) {
        super(method, url, listener);
        mGson = new Gson();
        mClass = aClass;
        mListener = listener1;
        this.map = map;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            if (parsed.startsWith("(")){
                parsed = parsed.replace("(", "");
                parsed = parsed.replace(")", "");
            }
            return Response.success(mGson.fromJson(parsed, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    // 返回值
    @Override
    protected void deliverResponse(T response) {
       mListener.onResponse(response);
    }

    // 数据缓存
    @Override
    public void deliverError(VolleyError error) {
        // 判断 没有网络连接  我们使用缓存
        // instanceof  前者是否属于后者的子类
        if (error instanceof NoConnectionError) {
            //获取缓存数据
            Cache.Entry entry = this.getCacheEntry();
            //创建请求 请求的数据就是我们自己缓存的数据
            Response<T> response = parseNetworkResponse(new NetworkResponse(entry.data,entry.responseHeaders));
            deliverResponse(response.result);
        }
        super.deliverError(error);
    }
}
