package com.alex.dbms.dao.impl;

import com.alex.dbms.dao.IMetaDataConverter;
import com.alex.dbms.exception.DAOException;
import com.alex.dbms.model.*;
import com.alex.dbms.vo.ConnParam;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 通用数据元信息查询
 * @Author:     alex
 * @CreateDate: 2019/11/14 20:14
 * @Version:    1.0
 *
*/
public class CommonDatabaseDAOImpl extends AbstractDatabaseDaoImpl{

    public CommonDatabaseDAOImpl(ConnParam connParam) {
        super(connParam);
    }

    @Override
    public List<Map<String, String>> query(String sql, String[] params) throws DAOException {
        return super.query(sql, params);
    }

    @Override
    public List<Table> getTables() throws DAOException {
        return super.getTables();
    }

    @Override
    protected String getQuerySql(String sqlKey) throws DAOException {
        return null;
    }

    @Override
    public List<Column> getColumns(String tableName) throws DAOException {
        return super.getColumns(tableName);
    }

    @Override
    public List<PrimaryKey> getPrimaryKeys(String tableName) throws DAOException {
        return super.getPrimaryKeys(tableName);
    }

    @Override
    public List<ForeignKey> getForeignKeys(String tableName) throws DAOException {
        return super.getForeignKeys(tableName);
    }

    @Override
    public List<Index> getIndexes(String tableName) throws DAOException {
        return super.getIndexes(tableName);
    }

    @Override
    public List<Trigger> getTriggers(String tableName) throws DAOException {
        return super.getTriggers(tableName);
    }

    @Override
    public Connection openConnection() throws DAOException {
        return super.openConnection();
    }

    @Override
    protected String getUrl(String host, int port, String dbName) {
        return null;
    }

    @Override
    protected String getDriver() throws DAOException {
        return null;
    }

    @Override
    public void closeConnection() throws DAOException {
        super.closeConnection();
    }

    @Override
    public void setConverter(IMetaDataConverter converter) {
        super.setConverter(converter);
    }
}
