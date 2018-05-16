package com.project.cyy.plan.db;

/**
 * Created by cyy
 * on 18-5-16.
 */


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseUtil {
     static void updateColumn(SQLiteDatabase db, String tableName,
                                    String columnName, String columnType, Object defaultField) {
        try {
            if (db != null) {
                Cursor c = db.rawQuery("SELECT * from " + tableName
                        + " limit 1 ", null);
                boolean flag = false;

                if (c != null) {
                    for (int i = 0; i < c.getColumnCount(); i++) {
                        if (columnName.equalsIgnoreCase(c.getColumnName(i))) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        String sql = "alter table " + tableName + " add "
                                + columnName + " " + columnType + " default "
                                + defaultField;
                        db.execSQL(sql);
                    }
                    c.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecordsWithoutForeignKey(SQLiteDatabase db, String tableName) {
        if (db != null) {
            String sql = "DELETE from " + tableName;
            db.execSQL(sql);
        }
    }
}
