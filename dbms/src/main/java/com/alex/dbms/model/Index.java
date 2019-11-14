package com.alex.dbms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 索引
 * @Author:     alex
 * @CreateDate: 2019/11/14 9:26
 * @Version:    1.0
 *
*/
public class Index {

    //名称
    private String name;

    //所属类型
    private String indexType;

    //所属表名称
    private String tableName;

    //是否唯一索引
    private Boolean unique;

    //字段列
    private List<String> columns = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void addColumn(String column) {
        this.columns.add(column);
    }
}
