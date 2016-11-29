package com.example.dllo.yohomix.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/11/23.
 */

public class MyApp extends Application{

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }


}
