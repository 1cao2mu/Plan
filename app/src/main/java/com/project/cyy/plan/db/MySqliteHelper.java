package com.project.cyy.plan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 建一个Sqlite帮助类
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    //数据库的名称
    private static String NAME = "school.db";
    //数据库的版本
    private static int VERSION = 1;
    /**
     * @param context 上下文对象
     * @param name    数据库的名称
     * @param factory 游标工厂
     * @param version 数据库的版本
     */
    public MySqliteHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }
    public MySqliteHelper(Context context) {
        super(context, NAME, null, VERSION);
    }
    /**
     * 数据库第一次创建时   回调此方法
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table person(_id integer primary key autoincrement,tel varchar(11),pwd varchar(16),name varchar(16),number varchar(16),image varchar(160))";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into person values(1,'15538039917','123456','管理员','1201120066','')";
        //执行数据库语句
        db.execSQL(sql);

        sql = "create table loseThing(_id integer primary key autoincrement,uid integer,image varchar(160),name varchar(16),thingName varchar(16),tel varchar(11),address varchar(50))";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into loseThing values(1,1,'','管理员','管理员的心','15538039917','十八层地狱')";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into loseThing values(2,1,'','管理员','管理员的肝','15538039917','十七层地狱')";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into loseThing values(3,1,'','管理员','管理员的脾','15538039917','十六层地狱')";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into loseThing values(4,1,'','管理员','管理员的肺','15538039917','十五层地狱')";
        //执行数据库语句
        db.execSQL(sql);
        sql = "create table help(_id integer primary key autoincrement,uid integer,time varchar(16),content varchar(160))";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into help values(1,1,'','程序没问题,我就是试试')";
        //执行数据库语句
        db.execSQL(sql);

        sql = "create table findTab(fid integer primary key autoincrement,uid integer,uname varchar(16),uimage varchar(160),content text,image varchar(160),topCount integer,bottomCount integer,commitCount integer,topId text,bottomId text,commitId text)";
        //执行数据库语句
        db.execSQL(sql);
        sql = "create table findTabCommit(_id integer primary key autoincrement,fid integer,uid integer,uname varchar(16),uimage varchar(160),content text)";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into findTab values(1,1,'管理员','','程序没问题,我就是试试','',1,0,0,'[1]','','')";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into findTab values(2,1,'管理员','','程序没问题,我就是试试','',0,1,0,'','[1]','')";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into findTab values(3,1,'管理员','','程序没问题,我就是试试','',0,0,1,'','','[1]')";
        //执行数据库语句
        db.execSQL(sql);
        sql = "insert into findTabCommit values(1,1,1,'管理员','','你们这样做是不对的')";
        //执行数据库语句
        db.execSQL(sql);


        sql = "create table adviseTab(fid integer primary key autoincrement,uid integer,uname varchar(16),uimage varchar(160),content text,image varchar(160),topCount integer,bottomCount integer,commitCount integer,topId text,bottomId text,commitId text)";
        //执行数据库语句
        db.execSQL(sql);
        sql = "create table adviseTabCommit(_id integer primary key autoincrement,fid integer,uid integer,uname varchar(16),uimage varchar(160),content text)";
        //执行数据库语句
        db.execSQL(sql);
        sql = "create table newTab(fid integer primary key autoincrement,uid integer,uname varchar(16),uimage varchar(160),content text,image varchar(160),topCount integer,bottomCount integer,commitCount integer,topId text,bottomId text,commitId text)";
        //执行数据库语句
        db.execSQL(sql);
        sql = "create table newTabCommit(_id integer primary key autoincrement,fid integer,uid integer,uname varchar(16),uimage varchar(160),content text)";
        //执行数据库语句
        db.execSQL(sql);
    }

    /**
     * 数据库版本发生改变时调用  newVersion>oldVersion  数据库版本升级
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        super.onOpen(db);
        Log.i("==onOpen==", "数据库打开");
    }

}
