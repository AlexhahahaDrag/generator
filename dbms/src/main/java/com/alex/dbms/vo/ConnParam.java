package com.alex.dbms.vo;

import java.io.Serializable;

/**
 * @Description: 数据库连接参数封装
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:47
 * @Version:    1.0
 *
*/
public class ConnParam implements Serializable {

    private static final long serialVersionUID = 1L;

    //数据库类型
    private String dbType;

    //数据库地址
    private String host;

    //数据库端口
    private Integer port;

    //数据库实例名
    private String dbName;

    //用户名
    private String userName;

    //密码
    private String password;

    public ConnParam() {
    }

    public ConnParam(String dbType, String host, Integer port, String dbName, String userName, String password) {
        this.dbType = dbType;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
