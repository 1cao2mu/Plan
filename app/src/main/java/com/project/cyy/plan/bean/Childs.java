package com.project.cyy.plan.bean;

/**
 * Created by cyy
 * on 18-5-16.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 孩子
 */
@DatabaseTable(tableName = "tab_child")
public class Childs {

    @DatabaseField(useGetSet=true,generatedId=true,columnName="id")
    private int id;

    @DatabaseField(useGetSet=true, columnName = "s_name")
    private String sname;//姓名

    @DatabaseField(useGetSet=true, columnName = "s_gender")
    private String sgender;//性别

    @DatabaseField(useGetSet=true, columnName = "s_age")
    private String sage;//年龄

    @DatabaseField(useGetSet=true, columnName = "s_phone")
    private String sphone;//联系方式

    //引入外键(columnName值默认存储父类中的主键对应的值，也可以换成foreignColumnName指定父类字段，格式为foreignColumnName = "父表里的字段"
    //如foreignColumnName = "f_name",此时将会存储父表里对应的字段内容)
    @DatabaseField(useGetSet=true, canBeNull = true, foreign = true, columnName = "s_parent")
    private Parents parents;//父母信息

    public Childs() {

    }

    public Childs(String sname, String sgender, String sage) {
        this.sname = sname;
        this.sgender = sgender;
        this.sage = sage;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSgender() {
        return sgender;
    }

    public void setSgender(String sgender) {
        this.sgender = sgender;
    }

    public String getSage() {
        return sage;
    }

    public void setSage(String sage) {
        this.sage = sage;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "Childs{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", sgender='" + sgender + '\'' +
                ", sage='" + sage + '\'' +
                ", parents=" + parents +
                '}';
    }
}
