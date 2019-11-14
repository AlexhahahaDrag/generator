package com.alex.dbms.exception;

/**
 * @Description: dao查询操作异常
 * @Author:     alex
 * @CreateDate: 2019/11/14 19:44
 * @Version:    1.0
 *
 */
public class QueryDAOException extends DAOException {

    private final String tableName;

    /**
     * @Description:
     * @Author:      alex
     * @CreateDate:  2019/11/14 19:45
     * @param
     * @return
     */
    public QueryDAOException(String tableName, int type, String message, Throwable cause) {
        super(type, message, cause);
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public String toString() {
        String typeStr = getTypeString();
        if (typeStr != null)
            return String.format("表【%s】查询%s信息出错，出错原因是%s。", tableName, typeStr, getMessage());
        return super.toString();
    }

    private String getTypeString() {
        switch (getType()) {
            case QUERY_COLUMN_EXCEPTION :
                return "字段";
            case QUERY_FOREIGN_KEY_EXCEPTION :
                return "外键";
            case QUERY_INDEX_EXCEPTION :
                return "索引";
            case QUERY_PRIMARY_KEY_EXCEPTION :
                return "主键";
            case QUERY_TRIGGER_EXCEPTION :
                return "触发器";
            default:
                return null;
        }
    }
}
