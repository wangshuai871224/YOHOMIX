package com.example.dllo.yohomix.tools;

import android.app.Application;
import android.content.Context;

import com.example.dllo.yohomix.bean.DaoMaster;
import com.example.dllo.yohomix.bean.DaoSession;

/**
 * Created by dllo on 16/11/23.
 */

public class MyApp extends Application{

    private static Context mContext;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "Collect.db", null);
        mDaoMaster = new DaoMaster(helper.getWritableDb());
        return mDaoMaster;
    }

    public static DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
        }
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }


}
