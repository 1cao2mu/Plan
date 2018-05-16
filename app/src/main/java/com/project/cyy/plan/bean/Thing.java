package com.project.cyy.plan.bean;

/**
 * Created by cyy
 * on 18-5-16.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 搞事情
 */
@DatabaseTable(tableName = "tab_thing")
public class Thing {

    @DatabaseField(useGetSet=true,generatedId=true,columnName="id")
    private int id;

    @DatabaseField(useGetSet=true, columnName = "content")
    private String content;
    @DatabaseField(useGetSet=true, columnName = "title")
    private String title;
    @DatabaseField(useGetSet=true, columnName = "finish")
    private String finish;



    public Thing() {

    }

    public Thing(String content, String title, String finish) {
        this.content = content;
        this.title = title;
        this.finish = finish;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}
