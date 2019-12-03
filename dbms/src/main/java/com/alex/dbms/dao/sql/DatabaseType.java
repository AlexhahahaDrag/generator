package com.alex.dbms.dao.sql;

import com.alex.dbms.converter.CommonMetaDataConverter;
import com.alex.dbms.converter.MySQL5MetaDataConverter;
import com.alex.dbms.converter.OracleMetaDataConverter;
import com.alex.dbms.dao.IMetaDataConverter;

/**
 * @Description: 数据库查询语句文件
 * @Author:     alex
 * @CreateDate: 2019/11/18 13:59
 * @Version:    1.0
 *
 */
public enum DatabaseType {

    Oracle {

        @Override
        public String getFileName() {
            return FOLDER + "/Oracle.xml";
        }

        @Override
        public IMetaDataConverter getConverter() {
            return OracleMetaDataConverter.getInstance();
        }
    },

    MySql5 {
        @Override
        public String getFileName() {
            return FOLDER + "/MySQL5.xml";
        }

        @Override
        public IMetaDataConverter getConverter() {
            return MySQL5MetaDataConverter.getInstance();
        }
    },

    MSSQLServer {
        @Override
        public String getFileName() {
            return FOLDER + "/MSSQL.xml";
        }
    };

    private static final String FOLDER =
            DatabaseType.class.getPackage().getName().replace(".", "/");

    public abstract String getFileName();

    public IMetaDataConverter getConverter() {
        return CommonMetaDataConverter.getInstance();
    }
}
