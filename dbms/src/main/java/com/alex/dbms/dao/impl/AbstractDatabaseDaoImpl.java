package com.alex.dbms.dao.impl;

import com.alex.dbms.dao.IDatabaseDao;
import com.alex.dbms.dao.IMetaDataConverter;
import com.alex.dbms.exception.DAOException;
import com.alex.dbms.exception.QueryDAOException;
import com.alex.dbms.model.*;
import com.alex.dbms.vo.ConnParam;

import java.sql.*;
import java.util.*;

/**
 * @Description: 抽象数据库元信息查询类
 * @Author:     alex
 * @CreateDate: 2019/11/14 19:06
 * @Version:    1.0
 *
 */
public abstract class AbstractDatabaseDaoImpl implements IDatabaseDao {

    private IMetaDataConverter converter;

    protected ConnParam connParam;

    private Connection connection;

    protected static final String DRIVER = "driver";

    protected static final String URL = "url";

    protected static final String QUERY_TABLE = "query_table";

    protected static final String QUERY_COLUMN = "query_column";

    protected static final String QUERY_INDEX = "query_index";

    protected static final String QUERY_PRIMARY_KEY = "query_primary_key";

    protected static final String QUERY_FOREIGN_KEY = "query_foreign_key";

    protected static final String QUERY_TRIGGER = "query_trigger";

    public AbstractDatabaseDaoImpl(ConnParam connParam) {
        this.connParam = connParam;
    }

    @Override
    public List<Map<String, String>> query(String sql, String[] params) throws DAOException {
        if (sql == null) {
            Exception exception = new IllegalArgumentException("输入的sql查询语句为空");
            throw new DAOException(DAOException.QUERY_EXCEPTION, exception.getMessage(), exception);
        }
        List<Map<String, String>> result = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int paramIndex = 0; paramIndex < params.length; paramIndex++)
                    preparedStatement.setString(paramIndex + 1, params[paramIndex]);
            }
            rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnSize = resultSetMetaData.getColumnCount();
            while(rs.next()) {
                Map<String, String> map = new HashMap<>();
                for (int columnIndex = 1; columnIndex <= columnSize; columnIndex++) {
                    String columnName = resultSetMetaData.getColumnLabel(columnIndex);
                    String columnValue = rs.getString(columnName);
                    map.put(columnName, columnValue);
                }
                result.add(map);
            }
        } catch (SQLException e) {
            throw new DAOException(DAOException.QUERY_EXCEPTION, e.getMessage(), e);
        } finally {
            close(rs, preparedStatement);
        }
        return result;
    }

    @Override
    public List<Table> getTables() throws DAOException {
        List<Table> tables = new ArrayList<>();
        try {
            List<Map<String, String>> result = query(getQuerySql(QUERY_TABLE), null);
            for (Map<String, String> map : result)
                tables.add(converter.convertMap2Table(map));
        } catch (DAOException e) {
            throw new DAOException(DAOException.QUERY_TABLE_EXCEPTION, e.getMessage(), e);
        }
        return tables;
    }

    abstract protected String getQuerySql(String sqlKey) throws DAOException;

    @Override
    public List<Column> getColumns(String tableName) throws DAOException {
        List<Column> columns = new ArrayList<>();
        try {
            List<Map<String, String>> result = query(getQuerySql(QUERY_COLUMN), null);
            for (Map<String, String> map : result)
                columns.add(converter.convertMap2Column(map));
        } catch (DAOException e) {
            throw new QueryDAOException(tableName, DAOException.QUERY_COLUMN_EXCEPTION, e.getMessage(), e);
        }
        return columns;
    }

    @Override
    public List<PrimaryKey> getPrimaryKeys(String tableName) throws DAOException {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        try {
            List<Map<String, String>> result = query(getQuerySql(QUERY_PRIMARY_KEY),
                    new String[] { tableName });

            for (Map<String, String> map : result) {
                primaryKeys.add(converter.convertMap2PrimaryKey(map));
            }

        } catch (DAOException e) {
            throw new QueryDAOException(tableName,
                    DAOException.QUERY_PRIMARY_KEY_EXCEPTION, e.getMessage(), e);
        }
        return primaryKeys;
    }

    @Override
    public List<ForeignKey> getForeignKeys(String tableName) throws DAOException {
        List<ForeignKey> foreignKeys = new ArrayList<>(0);
        try {
            List<Map<String, String>> result = query(getQuerySql(QUERY_FOREIGN_KEY),
                    new String[] { tableName });
            for (Map<String, String> map : result)
                foreignKeys.add(converter.convertMap2ForeignKey(map));
        } catch (DAOException e) {
            throw new QueryDAOException(tableName,
                    DAOException.QUERY_FOREIGN_KEY_EXCEPTION, e.getMessage(), e);
        }
        return foreignKeys;
    }

    @Override
    public List<Index> getIndexes(String tableName) throws DAOException {
        Map<String, Index> indexMap = new HashMap<>();
        try {
            List<Map<String, String>> result = query(getQuerySql(QUERY_INDEX),
                    new String[] { tableName });
            for (Map<String, String> map : result) {
                Index index = converter.convertMap2Index(map);
                String indexName = index.getName();
                if (!indexMap.containsKey(indexName)) {
                    indexMap.put(indexName, index);
                } else {
                    if (!index.getColumns().isEmpty()) {
                        indexMap.get(indexName).addColumn(index.getColumns().get(0));
                    }
                }
            }
        } catch (DAOException e) {
            throw new QueryDAOException(tableName,
                    DAOException.QUERY_INDEX_EXCEPTION, e.getMessage(), e);
        }
        return new LinkedList<>(indexMap.values());
    }

    @Override
    public List<Trigger> getTriggers(String tableName) throws DAOException {
        List<Trigger> triggers = new LinkedList<>();
        try {
            List<Map<String, String>> result = query(getQuerySql(QUERY_TRIGGER),
                    new String[] { tableName });
            for (Map<String, String> map : result)
                triggers.add(converter.convertMap2Trigger(map));
        } catch (DAOException e) {
            throw new QueryDAOException(tableName,
                    DAOException.QUERY_TRIGGER_EXCEPTION, e.getMessage(), e);
        }
        return triggers;
    }

    @Override
    public Connection openConnection() throws DAOException {
        try {
            closeConnection();
            Class.forName(getDriver());
            Properties properties = new Properties();
            properties.put("remarksReporting", "true");
            properties.put("user", connParam.getUserName());
            properties.put("password", connParam.getPassword());
            String url = getUrl(connParam.getHost(), connParam.getPort(), connParam.getDbName());
            connection = DriverManager.getConnection(getUrl(connParam.getHost(), connParam.getPort(), connParam.getDbName()), properties);
        } catch (ClassNotFoundException e) {
            String errorMsg = "连接创建失败，找不到相关驱动类（" + e.getMessage() + ")";
            throw new DAOException(DAOException.OPEN_CONNECTION_EXCEPTION, errorMsg, e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.OPEN_CONNECTION_EXCEPTION, e.getMessage(), e);
        }
        return connection;
    }

    /**
     * @Description: 获取要连接的数据库的url，抽象方法，由子类实现
     * @Author:      alex
     * @CreateDate:  2019/11/14 20:10
     * @param host
     * @param port
     * @param dbName
     * @return
    */
    protected abstract String getUrl(String host, int port, String dbName);

    /**
     * @Description: 获取驱动类字符串描述值，抽象方法，由子类实现
     * @Author:      alex
     * @CreateDate:  2019/11/14 20:09
     * @param
     * @return
    */
    protected abstract String getDriver() throws DAOException;

    @Override
    public void closeConnection() throws DAOException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new DAOException(DAOException.CLOSE_CONNECTION_EXCEPTION, e.getMessage(), e);
            }
        }
    }

    private void close(ResultSet rs, Statement st) throws DAOException {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                throw new DAOException(DAOException.CLOSE_JDBC_EXCEPTION, e.getMessage(), e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                        st = null;
                    } catch (SQLException e) {
                        throw new DAOException(DAOException.CLOSE_JDBC_EXCEPTION, e.getMessage(), e);
                    }
                }
            }

        }
    }

    public void setConverter(IMetaDataConverter converter) {
        this.converter = converter;
    }
}
