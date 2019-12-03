package com.alex.dbms.converter;

import com.alex.dbms.dao.IMetaDataConverter;
import com.alex.dbms.model.Index;
import com.alex.dbms.model.Table;

import java.util.Map;

/**
 * @Description: 元数据转换
 * @Author:     alex
 * @CreateDate: 2019/12/3 10:46
 * @Version:    1.0
 *
*/
public class MySQL5MetaDataConverter extends CommonMetaDataConverter{

    private static IMetaDataConverter instance = new MySQL5MetaDataConverter();

    public static IMetaDataConverter getInstance() {
        return instance;
    }

    @Override
    public Index convertMap2Index(Map<String, String> map) {
        Index index = super.convertMap2Index(map);
        index.setUnique(!"0".equals(getValue(map, "NON_UNIQUE")));
        return index;
    }
}
