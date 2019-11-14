package com.alex.dbms.dao;

import com.alex.dbms.exception.DAOException;
import com.alex.dbms.model.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**数据库元信息查询接口
 * @Description:
 * @Author:     alex
 * @CreateDate: 2019/11/14 17:47
 * @Version:    1.0
 *
*/
public interface IDatabaseDao {

    /**
     * @Description: 通用查询方法
     * @Author:      alex
     * @CreateDate:  2019/11/14 17:47
     * @param sql 要查询的sql语句
     * @param params 要查询的条件数组
     * @return
    */
    List<Map<String, String>> query(String sql, String[] params) throws DAOException;

    /**
     * @Description: 查询表集合
     * @Author:      alex
     * @CreateDate:  2019/11/14 17:55
     * @param
     * @return
    */
    List<Table> getTables() throws DAOException;

    /**
     * @Description: 查询表的字段集
     * @Author:      alex
     * @CreateDate:  2019/11/14 17:55
     * @param tableName
     * @return
    */
    List<Column> getColumns(String tableName) throws DAOException;

    /**
     * @Description: 查询的主键集
     * @Author:      alex
     * @CreateDate:  2019/11/14 17:57
     * @param tableName
     * @return
    */
    List<PrimaryKey> getPrimaryKeys(String tableName) throws DAOException;

    /**
     * @Description: 查询外键集
     * @Author:      alex
     * @CreateDate:  2019/11/14 17:57
     * @param tableName
     * @return
    */
    List<ForeignKey> getForeignKeys(String tableName) throws DAOException;

    /**
     * @Description: 查询索引集
     * @Author:      alex
     * @CreateDate:  2019/11/14 17:59
     * @param tableName
     * @return
    */
    List<Index> getIndexes(String tableName) throws DAOException;

    /**
     * @Description: 查询触发器集
     * @Author:      alex
     * @CreateDate:  2019/11/14 17:59
     * @param tableName
     * @return
    */
    List<Trigger> getTriggers(String tableName) throws DAOException;

    /**
     * @Description: 打开数据库连接
     * @Author:      alex
     * @CreateDate:  2019/11/14 18:00
     * @param
     * @return
    */
    Connection openConnection() throws DAOException;

    /**
     * @Description: 关闭数据库连接
     * @Author:      alex
     * @CreateDate:  2019/11/14 18:00
     * @param
     * @return
    */
    void closeConnection() throws DAOException;
}
