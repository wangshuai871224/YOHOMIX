package com.example.dllo.yohomix.tools;

import com.litesuits.orm.LiteOrm;

/**
 * Created by dllo on 16/12/6.
 */

public class DBLiteOrm {
    private static DBLiteOrm mDBLiteOrm;
    private LiteOrm liteOrm;

    private DBLiteOrm() {
        liteOrm = LiteOrm.newSingleInstance(MyApp.getContext(), "dateBase.db");
    }

    public static DBLiteOrm getInstance() {
        if (mDBLiteOrm == null) {
            synchronized (DBLiteOrm.class) {
                if (mDBLiteOrm == null) {
                    mDBLiteOrm = new DBLiteOrm();
                }
            }
        }
        return mDBLiteOrm;
    }
}
