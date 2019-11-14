package com.alex.dbms.model;

/**
 * @Description: 主键
 * @Author:     alex
 * @CreateDate: 2019/11/14 9:19
 * @Version:    1.0
 *
*/
public class PrimaryKey {

    //名称
    private String name;

    //所属表名称
    private String tableName;

    //字段
    private String column;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
