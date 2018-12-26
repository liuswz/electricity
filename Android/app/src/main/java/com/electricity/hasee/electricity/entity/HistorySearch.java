package com.electricity.hasee.electricity.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "historysearch")
public class HistorySearch {
    @Column(name = "id",isId = true,autoGen=true)//表中的id列，name自定
    private int id;



    @Column(name = "value")//表中的url列
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
