package com.project.cyy.plan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 建一个Sqlite帮助类
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    //数据库的名称
    private static String NAME = "plan.db";
    //数据库的版本
    private static int VERSION = 1;

    public MySqliteHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "create table learnTab(tid integer primary key autoincrement,content text,finish integer)";
        db.execSQL(sql);

        sql = "create table workTab(tid integer primary key autoincrement,content text,finish integer)";
        db.execSQL(sql);

        sql = "create table lifeTab(tid integer primary key autoincrement,content text,finish integer)";
        db.execSQL(sql);

        sql = "create table enterTab(tid integer primary key autoincrement,content text,finish integer)";
        db.execSQL(sql);

    }

    /**
     * 数据库版本发生改变时调用  newVersion>oldVersion  数据库版本升级
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.i("==onUpgrade==", "数据库版本升级");
        }
    }

    /**
     * 数据库版本发生改变时调用   newVersion<oldVersion 数据库版本降级
     * <p/>
     * 只有发生重大错误时才调用此方法
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        if (newVersion < oldVersion) {
            Log.i("==onDowngrade==", "数据库版本降级");
        }
    }

    /**
     * 每次打开数据库时调用的方法   主要是验证数据库打没打开
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i("==onOpen==", "数据库打开");
    }

}
