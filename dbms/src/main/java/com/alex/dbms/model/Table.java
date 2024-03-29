package com.alex.dbms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 数据表
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:54
 * @Version:    1.0
 *
*/
public class Table {

    //名称
    private String name;

    //描述
    private String description;

    //表空间
    private String tablespace;

    //索引字段列表
    private List<Column> columns = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTablespace() {
        return tablespace;
    }

    public void setTablespace(String tablespace) {
        this.tablespace = tablespace;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
