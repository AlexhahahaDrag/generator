package com.alex.generator.vo;

/**
 * @Description: 代码生成模型-字段
 * @Author:     alex
 * @CreateDate: 2019/11/13 17:04
 * @Version:    1.0
 *
*/
public class ColumnModel {

    //属性名
    private String fieldName;

    //java类型
    private String javaType;

    //jdbc类型
    private String jdbcType;

    //是否主键
    private Boolean isPrimaryKey;

    //getter名称
    private String getter;

    //setter名称
    private String setter;

    //字段名
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getSetter() {
        return setter;
    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

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

    public Boolean isNullable() {
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

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
