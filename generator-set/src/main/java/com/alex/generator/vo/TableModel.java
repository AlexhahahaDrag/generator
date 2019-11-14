package com.alex.generator.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代码生成模型-表
 * @Author:     alex
 * @CreateDate: 2019/11/13 17:14
 * @Version:    1.0
 *
*/
public class TableModel {

    //Model包名
    private String modelPackageName;

    //Dao包名
    private String daoPackageName;

    //sqlMap xml包名
    private String sqlMapPackageName;

    //service包名
    private String servicePackageName;

    //serviceImpl包名
    private String serviceImplPackageName;

    //controller包名
    private String controllerPackageName;

    //view目录名
    private String viewPackageName;

    //类名
    private String className;

    //实例名
    private String objectName;

    //主键列
    private ColumnModel primaryKey;

    //表名
    private String name;

    //描述
    private String description;

    //表空间
    private String tablespace;

    //索引字段列表
    private List<ColumnModel> columnModels = new ArrayList<>();
    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getDaoPackageName() {
        return daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getSqlMapPackageName() {
        return sqlMapPackageName;
    }

    public void setSqlMapPackageName(String sqlMapPackageName) {
        this.sqlMapPackageName = sqlMapPackageName;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getServiceImplPackageName() {
        return serviceImplPackageName;
    }

    public void setServiceImplPackageName(String serviceImplPackageName) {
        this.serviceImplPackageName = serviceImplPackageName;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public void setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }

    public String getViewPackageName() {
        return viewPackageName;
    }

    public void setViewPackageName(String viewPackageName) {
        this.viewPackageName = viewPackageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public ColumnModel getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnModel primaryKey) {
        this.primaryKey = primaryKey;
    }

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

    public List<ColumnModel> getColumnModels() {
        return columnModels;
    }

    public void setColumnModels(List<ColumnModel> columnModels) {
        this.columnModels = columnModels;
    }
}
