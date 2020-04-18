package com.chuangyou.sample;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.chuangyou.sample.greendao.DaoMaster;
import com.chuangyou.sample.greendao.DaoSession;

public class App extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mHelper = new DaoMaster.DevOpenHelper(this,"notes.db",null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }

}
