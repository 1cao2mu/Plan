package com.project.cyy.plan.tool;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.cyy.plan.db.MySqliteHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cyy
 * on 18-5-30下午3:09
 */
public class Data {


    public static JSONArray getThingListData(String table,MySqliteHelper helper, int page) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /*
         * 参数1：表名
         * 参数2：字段数组
         * 参数2：字段数组
         * 参数3：查询的条件  _id = ?
         * 参数4：查询条件的值  new String{"1"}
         * 参数5：分组字段
         * 参数6：在where条件后再次筛选
         * 参数7：查询排序
         */
        Cursor cursor = db.query(table, null, null, null, null, null, "finish asc,tid desc");
        cursor.moveToPosition((page - 1) * Constants.PAGE_SIZE - 1);
        while (cursor.moveToNext()) {
            JSONObject object = new JSONObject();
            object.put("tid", cursor.getString(cursor.getColumnIndex("tid")));
            object.put("content", cursor.getString(cursor.getColumnIndex("content")));
            object.put("finish", cursor.getString(cursor.getColumnIndex("finish")));
            jsonArray.put(object);
            if (jsonArray.length() == Constants.PAGE_SIZE) {
                break;
            }
        }
        cursor.close();
        db.close();
        return jsonArray;
    }

    public static JSONArray updateAndThingList(String table,MySqliteHelper helper, JSONObject jsonObject) throws JSONException {
        int finish = jsonObject.getString("finish").equalsIgnoreCase("1") ? 0 : 1;
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("finish", finish);
        db.update(table, values, "tid = ?", new String[]{jsonObject.getString("tid")});

        db.close();
        return getThingListData(table,helper, 1);
    }

    public static JSONArray removeAndThingList(String table,MySqliteHelper helper, JSONObject jsonObject) throws JSONException {
        SQLiteDatabase db = helper.getReadableDatabase();
        db.delete(table,"tid = ?", new String[]{jsonObject.getString("tid")});
        db.close();
        return getThingListData(table,helper, 1);
    }


    public static boolean addThing(String table,MySqliteHelper helper, String content) throws JSONException {
        SQLiteDatabase db1 = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", content);
        values.put("finish", 0);
        long num = db1.insert(table, null, values);
        db1.close();
        return num != 0;

    }

    public static boolean removeMyLossThingData(MySqliteHelper helper, int id, String loginId) throws JSONException {
        SQLiteDatabase db4 = helper.getReadableDatabase();
        /*
         * 参数1：数据库表名
         * 参数2：where 条件语句  _id = ?
         * 参数3： 条件语句的值   new String []{"1"}
         */
        int num = db4.delete("loseThing", "_id = ?", new String[]{String.valueOf(id)});
        db4.close();
        return num > 0;
    }


    public static boolean addFindTab(MySqliteHelper helper, String loginId, String uname, String uimage, String image, String content) throws JSONException {
        SQLiteDatabase db1 = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("uid", String.valueOf(loginId));
        values.put("uname", uname);
        values.put("uimage", uimage);
        values.put("content", content);
        values.put("image", image);
        long num = db1.insert("findTab", null, values);
        db1.close();
        return num != 0;
//        if (i == -1) {
//            //失败
//            return false;
//        } else {
//            //成功
//            return true;
//        }
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
        return i != 0;
//        if (i == -1) {
//            //失败
//            return false;
//        } else {
//            //成功
//            return true;
//        }
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
        return i != 0;
    }


    public static JSONArray getAdviseTabData(MySqliteHelper helper, int page) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /*
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
        cursor.close();
        db.close();
        return jsonArray;
    }

    public static JSONArray getNewTabData(MySqliteHelper helper, int page) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        SQLiteDatabase db = helper.getReadableDatabase();
        /*
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
        cursor.close();
        db.close();
        return jsonArray;
    }


}
