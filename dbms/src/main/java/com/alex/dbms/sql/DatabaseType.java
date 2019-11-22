package com.alex.dbms.sql;

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
    }
}
