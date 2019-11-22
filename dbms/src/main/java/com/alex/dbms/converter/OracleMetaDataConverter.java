package com.alex.dbms.converter;

import com.alex.dbms.dao.IMetaDataConverter;
import com.alex.dbms.model.*;

import java.util.Map;

/**
 * @Description: oracle元数据转化器
 * @Author:     alex
 * @CreateDate: 2019/11/18 14:03
 * @Version:    1.0
 *
*/
public class OracleMetaDataConverter extends CommonMetaDataConverter{

    private static final IMetaDataConverter instance = new OracleMetaDataConverter();

    public static IMetaDataConverter getInstance() {
        return instance;
    }

    @Override
    public Column convertMap2Column(Map<String, String> map) {
        Column column = super.convertMap2Column(map);
        String charLength = getValue(map, "CHAR_LENGTH");
        if (!"0".equals(charLength) && column.getDataType().contains("CHAR"))
            column.setLength(charLength);
        return column;
    }

    @Override
    public Index convertMap2Index(Map<String, String> map) {
        Index index = super.convertMap2Index(map);
        index.setUnique("NONUNIQUE".equals(getValue(map, "IS_")));
    }
}
