package com.alex.generator.service;

import com.alex.dbms.model.Table;
import com.alex.dbms.vo.ConnParam;
import com.alex.generator.vo.GenerateModel;

import java.util.List;

/**
 * @Description: 代码生成器服务
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:43
 * @Version:    1.0
 *
*/
public interface GenerateService {

    /**
     * @Description: 是否可以连接
     * @Author:      alex
     * @CreateDate:  2019/11/13 16:52
     * @param connParam
     * @return true or false
    */
    boolean testConnection(ConnParam connParam);

    /**
     * @Description: 反向查找数据表
     * @Author:      alex
     * @CreateDate:  2019/11/13 16:53
     * @param connParam
     * @return
    */
    List<Table> getTables(ConnParam connParam);

    /**
     * @Description: 获取代码生成数据模型
     * @Author:      alex
     * @CreateDate:  2019/11/13 17:01
     * @param generateModel
     * @return
    */
    GenerateModel getGenerateModel(GenerateModel generateModel);

    /**
     * @Description: 生成代码文件
     * @Author:      alex
     * @CreateDate:  2019/11/13 17:27
     * @param generateModel
     * @return
     * @throws Exception
    */
    boolean generateModels(GenerateModel generateModel) throws Exception;
}
