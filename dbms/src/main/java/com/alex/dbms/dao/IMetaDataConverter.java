package com.alex.dbms.dao;

import com.alex.dbms.model.*;

import java.util.Map;

/**
 * @Description: 元数据转换器接口
 * @Author:     alex
 * @CreateDate: 2019/11/14 19:07
 * @Version:    1.0
 *
*/
public interface IMetaDataConverter {

    /**
     * @Description: 将map转换成table
     * @Author:      alex
     * @CreateDate:  2019/11/14 19:08
     * @param
     * @return
    */
    Table convertMap2Table(Map<String, String> map);

    /**
     * @Description: 将map转换成column
     * @Author:      alex
     * @CreateDate:  2019/11/14 19:08
     * @param
     * @return
    */
    Column convertMap2Column(Map<String, String> map);

    /**
     * @Description: 将map转换成primary key
     * @Author:      alex
     * @CreateDate:  2019/11/14 19:09
     * @param
     * @return
    */
    PrimaryKey convertMap2PrimaryKey(Map<String, String> map);

    /**
     * @Description: 将map转换成foreign key
     * @Author:      alex
     * @CreateDate:  2019/11/14 19:10
     * @param
     * @return
    */
    ForeignKey convertMap2ForeignKey(Map<String, String> map);

    /**
     * @Description: 将map转化成index
     * @Author:      alex
     * @CreateDate:  2019/11/14 19:11
     * @param
     * @return
    */
    Index convertMap2Index(Map<String, String> map);

    /**
     * @Description: 将map转化成trigger
     * @Author:      alex
     * @CreateDate:  2019/11/14 19:12
     * @param
     * @return
    */
    Trigger convertMap2Trigger(Map<String, String> map);
}
