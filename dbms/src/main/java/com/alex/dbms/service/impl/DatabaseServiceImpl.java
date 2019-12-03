package com.alex.dbms.service.impl;

import com.alex.dbms.dao.DatabaseDAOFactory;
import com.alex.dbms.dao.IDatabaseDao;
import com.alex.dbms.model.*;
import com.alex.dbms.service.DatabaseService;
import com.alex.dbms.vo.ConnParam;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 数据库元信息查询服务实现类
 * @Author:     alex
 * @CreateDate: 2019/11/14 9:36
 * @Version:    1.0
 *
*/
@Component
public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public List<Map<String, String>> query(ConnParam connParam, String sql, String[] params) {
        List<Map<String, String>> maps = new ArrayList<>();
        if (connParam == null)
            return maps;
        IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
        return null;
    }

    @Override
    public List<Table> getTables(ConnParam connParam) {
        return null;
    }

    @Override
    public List<Column> getColumns(ConnParam connParam, String tableName) {
        return null;
    }

    @Override
    public List<PrimaryKey> getPrimaryKeys(ConnParam connParam, String tableName) {
        return null;
    }

    @Override
    public List<ForeignKey> getForeignKeys(ConnParam connParam, String tableName) {
        return null;
    }

    @Override
    public List<Index> getIndexes(ConnParam connParam, String tableName) {
        return null;
    }

    @Override
    public List<Trigger> getTriggers(ConnParam connParam, String tableName) {
        return null;
    }

    @Override
    public boolean canConnection(ConnParam connParam) {
        return true;
    }
}
