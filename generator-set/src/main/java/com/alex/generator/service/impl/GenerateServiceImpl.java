package com.alex.generator.service.impl;

import com.alex.dbms.model.Column;
import com.alex.dbms.model.PrimaryKey;
import com.alex.dbms.model.Table;
import com.alex.dbms.service.DatabaseService;
import com.alex.dbms.utils.StringUtils;
import com.alex.dbms.vo.ConnParam;
import com.alex.generator.service.GenerateService;
import com.alex.generator.utils.DataTypeUtils;
import com.alex.generator.vo.ColumnModel;
import com.alex.generator.vo.GenerateModel;
import com.alex.generator.vo.TableModel;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代码生成服务实现
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:44
 * @Version:    1.0
 *
*/
@Service
public class GenerateServiceImpl implements GenerateService {

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
     * @Description: file suffix name
     * @Author:      alex
     * @CreateDate:  2019/11/13 18:08
     * @param
     * @return
    */
    public static final String SQL_MAP_SUFFIX = "Mapper.xml";

    public static final String MODEL_SUFFIX = ".java";

    public static final String MAPPER_SUFFIX = "Mapper.java";

    public static final String SERVICE_SUFFIX = "Service.java";

    public static final String SERVICE_IMPL_SUFFIX = "ServiceImpl.java";

    public static final String CONTROLLER_SUFFIX = "Controller.java";

    public static final String VIEW_SUFFIX = ".vue";

    @Autowired
    private DatabaseService databaseService;

    @Override
    public boolean testConnection(ConnParam connParam) {
        return databaseService.canConnection(connParam);
    }

    @Override
    public List<Table> getTables(ConnParam connParam) {
        return databaseService.getTables(connParam);
    }

    @Override
    public GenerateModel getGenerateModel(GenerateModel generateModel) {
        List<TableModel> list = generateModel.getTableModels();
        for (TableModel tableModel : list) {
            ConnParam connParam = generateModel.getConnParam();
            String tableName = tableModel.getName();
            //设置表对应的实体名
            tableModel.setClassName(StringUtils.capitalize(lineToHump(tableName)));
            //设置表对应的实例名
            tableModel.setObjectName(StringUtils.uncapitalize(tableModel.getClassName()));
            //加载表字段
            tableModel.setColumnModels(getColumns(tableModel, connParam, tableName));
        }
        return generateModel;
    }

    private List<ColumnModel> getColumns(TableModel tableModel, ConnParam connParam, String tableName) {
        List<ColumnModel> columnModels = new ArrayList<>();
        List<Column> columns = databaseService.getColumns(connParam, tableName);
        List<PrimaryKey> primaryKeys = databaseService.getPrimaryKeys(connParam, tableName);
        for (Column column : columns) {
            ColumnModel columnModel = new ColumnModel();
            BeanUtils.copyProperties(column, columnModel);
            //设置对应的对象属性名
            String fieldName = lineToHump(column.getName());
            columnModel.setFieldName(fieldName);
            //设置属性设置和获取方法
            String setter = "set" + StringUtils.capitalize(fieldName);
            columnModel.setSetter(setter);
            String getter = "get" + StringUtils.capitalize(fieldName);
            columnModel.setGetter(getter);
            //设置java数据类型
            String javaType = DataTypeUtils.getJavaType(columnModel.getDataType());
            columnModel.setJavaType(javaType);
            String jdbcType = DataTypeUtils.getJdbcType(columnModel.getDataType());
            columnModel.setJdbcType(jdbcType);
            //设置是否为主键
            for (PrimaryKey primaryKey : primaryKeys) {
                if (column.getName().equalsIgnoreCase(primaryKey.getColumn())) {
                    columnModel.setPrimaryKey(true);
                    tableModel.setPrimaryKey(columnModel);
                    break;
                }
            }
            columnModels.add(columnModel);
        }
        return columnModels;
    }

    @Override
    public boolean generateModels(GenerateModel generateModel) throws Exception {
        String outPutFolderPath = generateModel.getOutPutFolderPath();
        if (outPutFolderPath == null) {
            outPutFolderPath = System.getProperty("user.dir") + "/src/main/java";
            generateModel.setOutPutFolderPath(outPutFolderPath);
        }
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("templates");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        for (TableModel tableModel : generateModel.getTableModels()) {
            //设置各类代码包名
            tableModel.setModelPackageName(getPackageName(generateModel.getBasePackage(), PACKAGE_MODEL));
            tableModel.setDaoPackageName(getPackageName(generateModel.getBasePackage(), PACKAGE_DAO));
            tableModel.setServiceImplPackageName(getPackageName(generateModel.getBasePackage(), PACKAGE_SERVICE_IMPL));
            tableModel.setServicePackageName(getPackageName(generateModel.getBasePackage(), PACKAGE_SERVICE));
            tableModel.setControllerPackageName(getPackageName(generateModel.getBasePackage(), PACKAGE_CONTROLLER));
            generateModel(groupTemplate, tableModel, TEMPLATE_MODEL, generateModel.getOutPutFolderPath());
            generateModel(groupTemplate, tableModel, TEMPLATE_VIEW, generateModel.getOutPutFolderPath());
            generateModel(groupTemplate, tableModel, TEMPLATE_SQLMAP, generateModel.getOutPutFolderPath());
            generateModel(groupTemplate, tableModel, TEMPLATE_MAPPER, generateModel.getOutPutFolderPath());
            generateModel(groupTemplate, tableModel, TEMPLATE_SERVICE_IMPL, generateModel.getOutPutFolderPath());
            generateModel(groupTemplate, tableModel, TEMPLATE_SERVICE, generateModel.getOutPutFolderPath());
            generateModel(groupTemplate, tableModel, TEMPLATE_CONTROLLER, generateModel.getOutPutFolderPath());
        }
        return false;
    }

    private String getPackageName(String basePackage, String subPackage) {
        return basePackage + "." + subPackage;
    }

    /**
     * @Description: 生成代码
     * @Author:      alex
     * @CreateDate:  2019/11/14 16:47
     * @param groupTemplate
     * @param tableModel
     * @param templatePath
     * @param outPutFolderPath
     * @return
    */
    private void generateModel(GroupTemplate groupTemplate, TableModel tableModel, String templatePath, String outPutFolderPath) throws Exception {
        Template template = groupTemplate.getTemplate(templatePath);
        template.binding(TABLE, tableModel);
        FileOutputStream os = new FileOutputStream(getOutputFile(tableModel, outPutFolderPath, templatePath));
        template.renderTo(os);
        os.close();
    }

    /**
     * @Description: 获取要生成的文件
     * @Author:      alex
     * @CreateDate:  2019/11/14 11:55
     * @param tableModel
     * @param outPutFolderPath
     * @param templatePath
     * @return
    */
    private String getOutputFile(TableModel tableModel, String outPutFolderPath, String templatePath) {
        String packageName = tableModel.getModelPackageName();
        String suffix = MODEL_SUFFIX;
        if (TEMPLATE_CONTROLLER.equals(templatePath)) {
            packageName = tableModel.getControllerPackageName();
            suffix =  CONTROLLER_SUFFIX;
        } else if (TEMPLATE_SERVICE.equals(templatePath)) {
            packageName = tableModel.getServicePackageName();
            suffix = SERVICE_SUFFIX;
        } else if (TEMPLATE_SERVICE_IMPL.equals(templatePath)) {
            packageName = tableModel.getServiceImplPackageName();
            suffix = SERVICE_IMPL_SUFFIX;
        } else if (TEMPLATE_MAPPER.equals(templatePath)) {
            packageName = tableModel.getDaoPackageName();
            suffix = MAPPER_SUFFIX;
        } else if (TEMPLATE_SQLMAP.equals(templatePath)) {
            packageName = tableModel.getSqlMapPackageName();
            suffix = MAPPER_SUFFIX;
        } else if (TEMPLATE_VIEW.equals(templatePath)) {
            packageName = tableModel.getViewPackageName();
            suffix = VIEW_SUFFIX;
        }
        outPutFolderPath = outPutFolderPath + "/" + packageName.replaceAll("\\.", "/");
        File outPutFolder = new File(outPutFolderPath);
        if (!outPutFolder.exists())
            outPutFolder.mkdirs();
        String filePath = outPutFolderPath + File.separator + tableModel.getClassName() + suffix;
        File file = new File(filePath);
        if (file.exists())
            file.delete();
        return filePath;
    }

    private String lineToHump(String str) {
        return StringUtils.lineToHump(str);
    }
}
