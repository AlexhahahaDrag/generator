package com.alex.dbms.dao.impl;

import com.alex.dbms.exception.DAOException;
import com.alex.dbms.dao.sql.DatabaseType;
import com.alex.dbms.utils.Dom4jUtils;
import com.alex.dbms.vo.ConnParam;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.HashMap;
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

    private static final String ELEMENT_DRIVER = DRIVER;

    private static final String ELEMENT_URL = URL;

    private static final String ELEMENT_SELECT = "select";

    private static final String ATTRIBUTE_NAME = "name";

    private String driver;

    private String url;

    private Map<String, String> selectMap = new HashMap<>();

    private DatabaseType dbType;

    public CommonDatabaseDAOImpl(ConnParam connParam, DatabaseType dbType) {
        super(connParam);
        setDbType(dbType);
        loadSqlXml(dbType);
    }

    public void setDbType(DatabaseType dbType) {
        this.dbType = dbType;
        setConverter(dbType.getConverter());
    }

    @Override
    protected String getDriver() throws DAOException {
        return driver;
    }

    @Override
    protected String getUrl(String host, int port, String dbName) {
        return String.format(url, host, port, dbName + "?serverTimezone=UTC&useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8");
    }

    protected String getQuerySql(String sqlKey) throws DAOException {
        if (selectMap.containsKey(sqlKey))
            return selectMap.get(sqlKey);
        throw new DAOException(DAOException.QUERY_EXCEPTION, "获取sql查询出错，数据库枚举类型为：" + dbType + "，查询语句为：" + sqlKey, null);
    }

    private void loadSqlXml(DatabaseType dbType) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(dbType.getFileName());
        Document doc = Dom4jUtils.getDocument(is);
        if (doc != null) {
            Element root = doc.getRootElement();
            driver = root.elementText(ELEMENT_DRIVER);
            url = root.elementText(ELEMENT_URL);
            for (Element selectElment : (List<Element>)root.elements(ELEMENT_SELECT))
                selectMap.put(selectElment.attributeValue(ATTRIBUTE_NAME), selectElment.getTextTrim());
        }
        try {
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
