package com.alex.generator.service.impl;

import com.alex.dbms.model.Table;
import com.alex.dbms.vo.ConnParam;
import com.alex.generator.service.GenerateService;
import com.alex.generator.vo.GenerateModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 代码生成服务实现
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:44
 * @Version:    1.0
 *
*/
@Service
public class GenerateServiceImpl extends GenerateService {

    public static final String TABLE = "table";

    /**
     * @Description: model
     * @Author:      alex
     * @CreateDate:  2019/11/13 18:04
     * @param
     * @return
    */
    public static final String TEMPLATE_MODEL = "/model.btl";

    public static final String TEMPLATE_MAPPER = "/mapper.btl";

    public static final String TEMPLATE_SQLMAP = "/sqlMap.btl";

    public static final String TEMPLATE_SERVICE = "/service.btl";

    public static final String TEMPLATE_SERVICE_IMPL = "/serviceImpl.btl";

    public static final String TEMPLATE_CONTROLLER = "/controller.btl";

    public static final String TEMPLATE_VIEW = "/view.btl";

    /**
     * @Description: directory
     * @Author:      alex
     * @CreateDate:  2019/11/13 18:05
     * @param
     * @return
    */
    public static final String PACKAGE_MODEL = "model";

    public static final String PACKAGE_DAO = "dao";

    public static final String PACKAGE_SQLMAP = "sqlmap";

    public static final String PACKAGE_SERVICE = "service";

    public static final String PACKAGE_SERVICE_IMPL = "service.impl";

    public static final String PACKAGE_CONTROLLER = "controller";

    public static final String PACKAGE_VIEW = "view";

    /**
     * @Description: file
     * @Author:      alex
     * @CreateDate:  2019/11/13 18:08
     * @param
     * @return
    */
    public static final String SQL_MAP_SUFFIX =
    @Override
    public boolean testConnection(ConnParam connParam) {
        return false;
    }

    @Override
    public List<Table> getTables(ConnParam connParam) {
        return null;
    }

    @Override
    public GenerateModel getGenerateModel(GenerateModel generateModel) {
        return null;
    }

    @Override
    public boolean generateModels(GenerateModel generateModel) throws Exception {
        return false;
    }
}
