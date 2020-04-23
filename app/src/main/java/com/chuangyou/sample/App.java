package com.chuangyou.sample;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.chuangyou.sample.greendao.DaoMaster;
import com.chuangyou.sample.greendao.DaoSession;

public class App extends Application {

    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this,"notes.db",null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }

}
