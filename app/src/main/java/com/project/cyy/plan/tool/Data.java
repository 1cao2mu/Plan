package com.project.cyy.plan.tool;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.cyy.plan.db.MySqliteHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class Data {
    public static JSONArray getData(int count) {
        JSONArray array = new JSONArray();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        try {
            for (int i = 0; i < count; i++) {
                JSONObject object = new JSONObject();
                object.put("url", "http://finance.gucheng.com/UploadFiles_7830/201504/2015040209572762.jpg");
                object.put("Content", i + ".咱们的程序功能太多了啊");
                object.put("Count", i);
                object.put("Tel", "15093427944");
                object.put("Name", "编号" + i);
                object.put("Thing", "失物" + i);
                object.put("Address", i + "楼");
                object.put("AddTime", date);
                if (i % 2 == 0) {
                    object.put("ReContent", "你的问题问的真好啊");
                    object.put("ReTime", date);
                    object.put("image", "http://finance.gucheng.com/UploadFiles_7830/201504/2015040209572762.jpg");
                }
                array.put(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static JSONArray getLossThingData(MySqliteHelper helper, int page) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("loseThing", null, null, null, null, null, "_id desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("image", cursor.getString(cursor.getColumnIndex("image")));
            object.put("name", cursor.getString(cursor.getColumnIndex("name")));
            object.put("thingName", cursor.getString(cursor.getColumnIndex("thingName")));
            object.put("tel", cursor.getString(cursor.getColumnIndex("tel")));
            object.put("address", cursor.getString(cursor.getColumnIndex("address")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }

    public static JSONArray getMyLossThingData(MySqliteHelper helper, int page, String loginId) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("loseThing", null, "uid = " + loginId, null, null, null, "_id desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("image", cursor.getString(cursor.getColumnIndex("image")));
            object.put("name", cursor.getString(cursor.getColumnIndex("name")));
            object.put("thingName", cursor.getString(cursor.getColumnIndex("thingName")));
            object.put("tel", cursor.getString(cursor.getColumnIndex("tel")));
            object.put("address", cursor.getString(cursor.getColumnIndex("address")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }

    public static boolean removeMyLossThingData(MySqliteHelper helper, int id, String loginId) throws JSONException {
        SQLiteDatabase db4 = helper.getReadableDatabase();
        /**
         * 参数1：数据库表名
         * 参数2：where 条件语句  _id = ?
         * 参数3： 条件语句的值   new String []{"1"}
         */
        int num1 = db4.delete("loseThing", "_id = ?", new String[]{String.valueOf(id)});
        db4.close();
        if (num1 > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean addHelp(MySqliteHelper helper, String loginId, String time, String content) throws JSONException {
        SQLiteDatabase db1 = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("uid", String.valueOf(loginId));
        values.put("time", time);
        values.put("content", content);
        long i = db1.insert("help", null, values);
        db1.close();
        if (i == -1) {
            //失败
            return false;
        } else {
            //成功
            return true;
        }
    }

    public static JSONArray getMyHelpData(MySqliteHelper helper, int page, String loginId) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("help", null, "uid = " + loginId, null, null, null, "_id desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("time", cursor.getString(cursor.getColumnIndex("time")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }

    public static boolean addFindTab(MySqliteHelper helper, String loginId, String uname, String uimage, String image, String content) throws JSONException {
        SQLiteDatabase db1 = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("uid", String.valueOf(loginId));
        values.put("uname", uname);
        values.put("uimage", uimage);
        values.put("content", content);
        values.put("image", image);
        long i = db1.insert("findTab", null, values);
        db1.close();
        if (i == -1) {
            //失败
            return false;
        } else {
            //成功
            return true;
        }
    }

    public static boolean addAdviseTab(MySqliteHelper helper, String loginId, String uname, String uimage, String image, String content) throws JSONException {
        SQLiteDatabase db1 = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("uid", String.valueOf(loginId));
        values.put("uname", uname);
        values.put("uimage", uimage);
        values.put("content", content);
        values.put("image", image);
        long i = db1.insert("adviseTab", null, values);
        db1.close();
        if (i == -1) {
            //失败
            return false;
        } else {
            //成功
            return true;
        }
    }

    public static boolean addNewTab(MySqliteHelper helper, String loginId, String uname, String uimage, String image, String content) throws JSONException {
        SQLiteDatabase db1 = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("uid", String.valueOf(loginId));
        values.put("uname", uname);
        values.put("uimage", uimage);
        values.put("content", content);
        values.put("image", image);
        long i = db1.insert("newTab", null, values);
        db1.close();
        if (i == -1) {
            //失败
            return false;
        } else {
            //成功
            return true;
        }
    }

    public static JSONArray getFindTabData(MySqliteHelper helper, int page) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("findTab", null, null, null, null, null, "fid desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("fid", cursor.getString(cursor.getColumnIndex("fid")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("uimage", cursor.getString(cursor.getColumnIndex("uimage")));
            object.put("uname", cursor.getString(cursor.getColumnIndex("uname")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            object.put("image", cursor.getString(cursor.getColumnIndex("image")));
            object.put("topCount", cursor.getString(cursor.getColumnIndex("topCount")));
            object.put("bottomCount", cursor.getString(cursor.getColumnIndex("bottomCount")));
            object.put("commitCount", cursor.getString(cursor.getColumnIndex("commitCount")));
            object.put("topId", cursor.getString(cursor.getColumnIndex("topId")));
            object.put("bottomId", cursor.getString(cursor.getColumnIndex("bottomId")));
            object.put("commitId", cursor.getString(cursor.getColumnIndex("commitId")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }
    public static JSONArray getAdviseTabData(MySqliteHelper helper, int page) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("adviseTab", null, null, null, null, null, "fid desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("fid", cursor.getString(cursor.getColumnIndex("fid")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("uimage", cursor.getString(cursor.getColumnIndex("uimage")));
            object.put("uname", cursor.getString(cursor.getColumnIndex("uname")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            object.put("image", cursor.getString(cursor.getColumnIndex("image")));
            object.put("topCount", cursor.getString(cursor.getColumnIndex("topCount")));
            object.put("bottomCount", cursor.getString(cursor.getColumnIndex("bottomCount")));
            object.put("commitCount", cursor.getString(cursor.getColumnIndex("commitCount")));
            object.put("topId", cursor.getString(cursor.getColumnIndex("topId")));
            object.put("bottomId", cursor.getString(cursor.getColumnIndex("bottomId")));
            object.put("commitId", cursor.getString(cursor.getColumnIndex("commitId")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }
    public static JSONArray getNewTabData(MySqliteHelper helper, int page) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("newTab", null, null, null, null, null, "fid desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("fid", cursor.getString(cursor.getColumnIndex("fid")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("uimage", cursor.getString(cursor.getColumnIndex("uimage")));
            object.put("uname", cursor.getString(cursor.getColumnIndex("uname")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            object.put("image", cursor.getString(cursor.getColumnIndex("image")));
            object.put("topCount", cursor.getString(cursor.getColumnIndex("topCount")));
            object.put("bottomCount", cursor.getString(cursor.getColumnIndex("bottomCount")));
            object.put("commitCount", cursor.getString(cursor.getColumnIndex("commitCount")));
            object.put("topId", cursor.getString(cursor.getColumnIndex("topId")));
            object.put("bottomId", cursor.getString(cursor.getColumnIndex("bottomId")));
            object.put("commitId", cursor.getString(cursor.getColumnIndex("commitId")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }

    public static JSONArray getCommitData(MySqliteHelper helper, int page, int fid) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("findTabCommit", null, "fid = " + fid, null, null, null, "_id desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("fid", cursor.getString(cursor.getColumnIndex("fid")));
            object.put("uname", cursor.getString(cursor.getColumnIndex("uname")));
            object.put("uimage", cursor.getString(cursor.getColumnIndex("uimage")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }

    public static JSONArray getCommitData2(MySqliteHelper helper, int page, int fid) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("adviseTabCommit", null, "fid = " + fid, null, null, null, "_id desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("fid", cursor.getString(cursor.getColumnIndex("fid")));
            object.put("uname", cursor.getString(cursor.getColumnIndex("uname")));
            object.put("uimage", cursor.getString(cursor.getColumnIndex("uimage")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }

    public static JSONArray getCommitData3(MySqliteHelper helper, int page, int fid) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         *
         */
        Cursor cursor = db.query("newTabCommit", null, "fid = " + fid, null, null, null, "_id desc");
        Utils.sysout("+++++" + cursor.getCount());
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("_id", cursor.getString(cursor.getColumnIndex("_id")));
            object.put("uid", cursor.getString(cursor.getColumnIndex("uid")));
            object.put("fid", cursor.getString(cursor.getColumnIndex("fid")));
            object.put("uname", cursor.getString(cursor.getColumnIndex("uname")));
            object.put("uimage", cursor.getString(cursor.getColumnIndex("uimage")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        db.close();
        return jsonArray;
    }

}
