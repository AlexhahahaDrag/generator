package com.alex.dbms.service.impl;

import com.alex.dbms.dao.DatabaseDAOFactory;
import com.alex.dbms.dao.IDatabaseDao;
import com.alex.dbms.model.*;
import com.alex.dbms.service.DatabaseService;
import com.alex.dbms.vo.ConnParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    Logger logger = LoggerFactory.getLogger(DatabaseServiceImpl.class);

    @Override
    public List<Map<String, String>> query(ConnParam connParam, String sql, String[] params) {
        List<Map<String, String>> maps = new ArrayList<>();
        if (connParam == null)
            return maps;
        try {
            IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
            long start = System.currentTimeMillis();
            dao.openConnection();
            maps = dao.query(sql, params);
            dao.closeConnection();
            long end = System.currentTimeMillis();
            logger.info("反向获取数据库信息耗时：" + (end - start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    @Override
    public List<Table> getTables(ConnParam connParam) {
        List<Table> tables = new ArrayList<>();
        if (connParam == null)
            return tables;
        try {
            IDatabaseDao databaseDao = DatabaseDAOFactory.getDao(connParam);
            long start = System.currentTimeMillis();
            databaseDao.openConnection();
            tables = databaseDao.getTables();
            databaseDao.closeConnection();
            long end = System.currentTimeMillis();
            logger.info("反向获取数据库表信息耗时：" + (end - start) + "耗时");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public List<Column> getColumns(ConnParam connParam, String tableName) {
        List<Column> columns = new ArrayList<>();
        if (connParam != null) {
            try {
                IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
                long start = System.currentTimeMillis();
                dao.openConnection();
                columns = dao.getColumns(tableName);
                dao.closeConnection();
                long end = System.currentTimeMillis();
                logger.info("反向获取数据库列信息耗时：" + (end - start) + "毫秒");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return columns;
    }

    @Override
    public List<PrimaryKey> getPrimaryKeys(ConnParam connParam, String tableName) {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        if (primaryKeys != null) {
            try {
                IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
                long start = System.currentTimeMillis();
                dao.openConnection();
                primaryKeys = dao.getPrimaryKeys(tableName);
                dao.closeConnection();
                long end = System.currentTimeMillis();
                logger.info("反向获取数据库逐渐信息耗时：" + (end - start));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return primaryKeys;
    }

    @Override
    public List<ForeignKey> getForeignKeys(ConnParam connParam, String tableName) {
        List<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();
        if(connParam == null) {
            return foreignKeys;
        }
        try {
            IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
            dao.openConnection();
            foreignKeys = dao.getForeignKeys(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foreignKeys;
    }

    @Override
    public List<Index> getIndexes(ConnParam connParam, String tableName) {
        List<Index> indexes = new ArrayList<Index>();
        if(connParam == null) {
            return indexes;
        }
        try {
            IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
            dao.openConnection();
            indexes = dao.getIndexes(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexes;
    }

    @Override
    public List<Trigger> getTriggers(ConnParam connParam, String tableName) {
        List<Trigger> trigger = new ArrayList<Trigger>();
        if(connParam == null) {
            return trigger;
        }
        try {
            IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
            dao.openConnection();
            trigger = dao.getTriggers(tableName);
            dao.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trigger;
    }

    @Override
    public boolean canConnection(ConnParam connParam) {
        IDatabaseDao dao = DatabaseDAOFactory.getDao(connParam);
        if (dao == null)
            return false;
        try {
            dao.openConnection();
            logger.info("数据库连接成功！");
            return true;
        } catch (Exception e) {
            logger.info("数据库连接失败，请检查端口号、用户名或密码是否正确");
            e.printStackTrace();
        } finally {
            try {
                dao.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
