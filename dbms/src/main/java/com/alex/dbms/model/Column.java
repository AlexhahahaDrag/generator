package com.alex.dbms.model;

/**
 * @Description: 字段
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:58
 * @Version:    1.0
 *
*/
public class Column {

    //名称
    private String name;

    //表名称
    private String tableName;

    //数据类型
    private String dataType;

    //是否可空
    private Boolean isNullable;

    //默认值
    private String defaultValue;

    //长度
    private String length;

    //小数位
    private String precision;

    //描述
    private String description;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getNullable() {
        return isNullable;
    }

    public void setNullable(Boolean nullable) {
        isNullable = nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
