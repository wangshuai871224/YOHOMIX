package com.example.dllo.yohomix.tools;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.listener.NetListener;

import java.util.HashMap;

/**
 * Created by dllo on 16/11/23.
 */

public class VolleySingleton {

    private static VolleySingleton mVolleyPost = new VolleySingleton();
    private RequestQueue mRequestQueue;


    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());
    }

    public static VolleySingleton getInstance() {
        if (mVolleyPost == null) {
            synchronized (VolleySingleton.class) {
                if (mVolleyPost == null) {
                    mVolleyPost = new VolleySingleton();
                }
            }
        }
        return mVolleyPost;
    }



    /************************私有化的方法************************************/
    private <T> void baseRequest(Request<T> request) {
        mRequestQueue.add(request);
    }

    private <T> void  baseGsonRequest(String url, Class<T> mClass, final NetListener<T> listener){

        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.GET, url
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        }, mClass, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.successListener(response);
            }
        });
        mRequestQueue.add(gsonRequest);
    }

    private <T> void baseGsonPostRequest(String url, Class<T> mClass,
                                         final NetListener<T> listener,
                                         HashMap<String,String> map){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        }, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.successListener(response);
            }
        },mClass,map);
        mRequestQueue.add(gsonRequest);
    }

    private void baseStringRequest(String url, final NetListener<String> listener){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.successListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        });
        mRequestQueue.add(stringRequest);

    }

    public static  void MySRequest(String url,NetListener<String> listener){
        getInstance().baseStringRequest(url,listener);
    }
    public static <T> void MyRequest(String url,Class<T> mClass,NetListener<T> listener){
        getInstance().baseGsonRequest(url,mClass,listener);
    }
    public static <T> void MyRequest(String url,Class<T> mClass,NetListener<T> listener,HashMap<String
            ,String> map){
        getInstance().baseGsonPostRequest(url,mClass,listener,map);

    }






}
