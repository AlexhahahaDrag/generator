package com.alex.dbms.dao.impl;

import com.alex.dbms.exception.DAOException;
import com.alex.dbms.dao.sql.DatabaseType;
import com.alex.dbms.vo.ConnParam;

import java.util.List;
import java.util.Map;

/**
 * @Description: MySql数据元信息查询
 * @Author:     alex
 * @CreateDate: 2019/12/3 14:40
 * @Version:    1.0
 *
*/
public class MySql5DatabaseDao extends CommonDatabaseDAOImpl {

    public MySql5DatabaseDao(ConnParam connParam, DatabaseType dbType) {
        super(connParam, dbType);
    }

    @Override
    protected String getQuerySql(String sqlKey) throws DAOException {
        return super.getQuerySql(sqlKey);
    }

    @Override
    public List<Map<String, String>> query(String sql, String[] params) throws DAOException {
        String[] realParams;
        if (params == null)
            realParams = new String[] {connParam.getDbName()};
        else {
            realParams = new String[params.length + 1];
            realParams[0] = connParam.getDbName();
            int index = 1;
            for (String param : params)
                realParams[index++] = param;
        }
        return super.query(sql, realParams);
    }
}
