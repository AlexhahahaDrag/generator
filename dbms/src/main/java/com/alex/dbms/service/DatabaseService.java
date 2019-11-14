package com.alex.dbms.service;

import com.alex.dbms.model.*;
import com.alex.dbms.vo.ConnParam;

import java.util.List;
import java.util.Map;

/**
 * @Description: 数据库元信息查询服务
 * @Author:     alex
 * @CreateDate: 2019/11/14 9:13
 * @Version:    1.0
 *
*/
public interface DatabaseService {

    /**
     * @Description: 通用查询方法
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:14
     * @param connParam 连接参数
     * @param sql 要查询的sql语句
     * @param params 查询条件数组
     * @return
    */
    List<Map<String, String>> query(ConnParam connParam, String sql, String[] params);

    /**
     * @Description: 查询表集合
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:17
     * @param connParam 连接参数
     * @return
    */
    List<Table> getTables(ConnParam connParam);

    /**
     * @Description: 查询表字段
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:17
     * @param connParam 连接参数
     * @return
    */
    List<Column> getColumns(ConnParam connParam, String tableName);

    /**
     * @Description: 查询主键集
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:18
     * @param connParam
     * @param tableName
     * @return
    */
    List<PrimaryKey> getPrimaryKeys(ConnParam connParam, String tableName);

    /**
     * @Description: 查询外键集
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:24
     * @param connParam
     * @param tableName
     * @return
    */
    List<ForeignKey> getForeignKeys(ConnParam connParam, String tableName);

    /**
     * @Description: 查询索引集
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:31
     * @param connParam
     * @param tableName
     * @return
    */
    List<Index> getIndexes(ConnParam connParam, String tableName);

    /**
     * @Description: 查询触发器集
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:32
     * @param connParam
     * @param tableName
     * @return
    */
    List<Trigger> getTriggers(ConnParam connParam, String tableName);

    /**
     * @Description: 测试数据库是否可以连接
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:33
     * @param connParam
     * @return
    */
    boolean canConnection(ConnParam connParam);
}
