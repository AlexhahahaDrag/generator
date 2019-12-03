package com.alex.dbms.dao;

import com.alex.dbms.constants.DBMSConstants;
import com.alex.dbms.dao.impl.CommonDatabaseDAOImpl;
import com.alex.dbms.dao.impl.MySql5DatabaseDao;
import com.alex.dbms.dao.sql.DatabaseType;
import com.alex.dbms.vo.ConnParam;

/**
 * @Description: 查询器生成工厂
 * @Author:     alex
 * @CreateDate: 2019/11/14 18:02
 * @Version:    1.0
 *
*/
public class DatabaseDAOFactory {

    public static IDatabaseDao getDao(ConnParam connParam) {
        String upperCaseDbName = connParam.getDbType().toUpperCase();
        if (upperCaseDbName.contains(DBMSConstants.ORACLE))
            return new CommonDatabaseDAOImpl(connParam, DatabaseType.Oracle);
        else if (upperCaseDbName.contains(DBMSConstants.SQL_SERVICE))
            return new CommonDatabaseDAOImpl(connParam, DatabaseType.MSSQLServer);
        else if (upperCaseDbName.contains(DBMSConstants.MYSQL))
            return new MySql5DatabaseDao(connParam, DatabaseType.MySql5);
        return new MySql5DatabaseDao(connParam, DatabaseType.MySql5);
    }
}
