package com.project.cyy.plan.bean;

/**
 * Created by cyy
 * on 18-5-16.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 父母
 */
//定义表名
@DatabaseTable(tableName = "tab_parent")
public class Parents {
    //generatedId定义主键自增长，columnName定义该字段在数据库中的列名
    @DatabaseField(useGetSet=true,generatedId=true,columnName="id")
    private int id;

    @DatabaseField(useGetSet=true, columnName = "f_id")
    private String fid;//父母编号

    @DatabaseField(useGetSet=true, columnName = "f_name")
    private String fname;//父亲姓名

    @DatabaseField(useGetSet=true, columnName = "m_name")
    private String mname;//母亲姓名

    public Parents() {
    }

    public Parents(String fid, String fname, String mname) {
        this.fid = fid;
        this.fname = fname;
        this.mname = mname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "Parents{" +
                "id=" + id +
                ", fid='" + fid + '\'' +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }
}