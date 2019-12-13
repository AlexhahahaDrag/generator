package com.alex.generator.vo;

import com.alex.dbms.vo.ConnParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代码生成数据模型
 * @Author:     alex
 * @CreateDate: 2019/11/13 17:23
 * @Version:    1.0
 *
*/
public class GenerateModel {

    private String outPutFolderPath;

    private String basePackage = "com.alex.alexadmin";

    private ConnParam connParam;

    private List<TableModel> tableModels = new ArrayList<>();

    public String getOutPutFolderPath() {
        return outPutFolderPath;
    }

    public void setOutPutFolderPath(String outPutFolderPath) {
        this.outPutFolderPath = outPutFolderPath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public ConnParam getConnParam() {
        return connParam;
    }

    public void setConnParam(ConnParam connParam) {
        this.connParam = connParam;
    }

    public List<TableModel> getTableModels() {
        return tableModels;
    }

    public void setTableModels(List<TableModel> tableModels) {
        this.tableModels = tableModels;
    }
}
