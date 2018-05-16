package com.project.cyy.plan.db;

/**
 * Created by cyy
 * on 18-5-16.
 */

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.project.cyy.plan.bean.Parents;

import java.sql.SQLException;

public class ParentDao {
    private Dao<Parents, Integer> parentDao;
    private DatabaseHelper helper;

    public ParentDao(Context contex) {
        try {
            helper = DatabaseHelper.getHelper(contex);
            parentDao = helper.getDao(Parents.class);
            if (parentDao == null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增
     * @param parent
     */
    public void addParent(Parents parent) {
        try {
            parentDao.create(parent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
