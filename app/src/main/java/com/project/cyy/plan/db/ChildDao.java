package com.project.cyy.plan.db;

/**
 * Created by cyy
 * on 18-5-16.
 */

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.project.cyy.plan.bean.Childs;
import com.project.cyy.plan.bean.Parents;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChildDao {
    private Dao<Childs, Integer> childDao;
    private DatabaseHelper helper;

    public ChildDao(Context contex) {
        try {
            helper = DatabaseHelper.getHelper(contex);
            childDao = helper.getDao(Childs.class);
            if (childDao == null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增
     * @param child
     */
    public void addChild(Childs child) {
        try {
            childDao.create(child);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删（通过实体）
     * @param child
     */
    public void delChild(Childs child) {
        try {
            childDao.delete(child);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删（通过id）
     * @param id
     */
    public void delChildById(Integer id) {
        try {
            childDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改
     * @param child
     */
    public void updateChild(Childs child) {
        try {
            childDao.update(child);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查
     * @return
     */
    public List<Childs> queryAllChild() {
        ArrayList<Childs> childs = null;
        try {
            childs = (ArrayList<Childs>) childDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return childs;
    }

    /**
     * 获取user
     * @param id child编号
     * @return
     */
    public Childs getChild(Integer id) {
        try {
            //父母信息为空
            return childDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取user
     * @param id child编号
     * @return
     */
    public Childs getChildAndParent(Integer id) {
        try {
            //父母信息有值，此处的refresh也可通过在数据中添加foreignAutoRefresh = true来实现
            Childs child = childDao.queryForId(id);
            helper.getDao(Parents.class).refresh(child.getParents());
            return child;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取user
     * @param name child姓名
     * @return
     */
    public List<Childs> getChildByName(String name) {
        try {
            List<Childs> childs = childDao.queryBuilder().where().eq("s_name", name).query();
            return childs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
