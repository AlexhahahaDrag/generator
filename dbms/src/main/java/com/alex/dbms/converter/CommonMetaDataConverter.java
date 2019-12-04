package com.alex.dbms.converter;

import com.alex.dbms.dao.IMetaDataConverter;
import com.alex.dbms.model.*;

import java.util.Map;

/**
 * @Description: 通用元数据转化器
 * @Author:     alex
 * @CreateDate: 2019/11/18 14:04
 * @Version:    1.0
 *
*/
public class CommonMetaDataConverter implements IMetaDataConverter {

    //查询返回的字段名全是大写，所以用map获取值时，需要用大写字段名
    private static final IMetaDataConverter instance = new CommonMetaDataConverter();

    public static IMetaDataConverter getInstance() {
        return instance;
    }

    @Override
    public Table convertMap2Table(Map<String, String> map) {
        Table table = new Table();
        table.setName(getValue(map, "TABLE_NAME"));
        table.setDescription(getValue(map, "DESCRIPTION"));
        table.setTablespace(getValue(map, "TABLESPACE"));
        return table;
    }

    @Override
    public Column convertMap2Column(Map<String, String> map) {
        Column column = new Column();
        column.setName(getValue(map, "COLUMN_NAME"));
        column.setTableName(getValue(map, "TABLE_NAME"));
        column.setDataType(getValue(map, "DATA_TYPE"));
        String dataPrecision = getValue(map, "DATA_PRECISION");
        if (dataPrecision != null)
            column.setLength(dataPrecision);
        else
            column.setLength(getValue(map, "DATA_LENGTH"));
        column.setPrecision(getValue(map, "DATA_SCALE"));
        String nullable = getValue(map, "NULLABLE");
        if ("N".equalsIgnoreCase(nullable) || "NO".equalsIgnoreCase(nullable) || "NOT".equalsIgnoreCase(nullable))
            column.setNullable(false);
        else
            column.setNullable(true);
        column.setDescription(getValue(map, "DESCRIPTION"));
        column.setDefaultValue(getValue(map, "DEFAULT_VALUE"));
        return column;
    }

    @Override
    public PrimaryKey convertMap2PrimaryKey(Map<String, String> map) {
        PrimaryKey primaryKey = new PrimaryKey();
        primaryKey.setName(getValue(map, "CONSTRAINT_NAME"));
        primaryKey.setTableName(getValue(map, "TABLE_NAME"));
        primaryKey.setColumn(getValue(map, "COLUMN_NAME"));
        return primaryKey;
    }

    @Override
    public ForeignKey convertMap2ForeignKey(Map<String, String> map) {
        ForeignKey fk = new ForeignKey();
        fk.setFkName(getValue(map, "FK_NAME"));
        fk.setFkTableName(getValue(map, "FK_TABLE_NAME"));
        fk.setFkColumnName(getValue(map, "FK_COLUMN_NAME"));
        fk.setPkTableName(getValue(map, "PK_TABLE_NAME"));
        fk.setPkColumnName(getValue(map, "PK_COLUMN_NAME"));
        return fk;
    }

    @Override
    public Index convertMap2Index(Map<String, String> map) {
        Index index = new Index();
        index.setName(getValue(map, "INDEX_NAME"));
        index.setTableName(getValue(map, "TABLE_NAME"));
        index.setIndexType(getValue(map, "INDEX_TYPE"));
        index.setUnique(Boolean.valueOf(getValue(map, "IS_UNIQUE")));
        index.getColumns().add(getValue(map, "COLUMN_NAME"));
        return index;
    }

    @Override
    public Trigger convertMap2Trigger(Map<String, String> map) {
        Trigger trigger = new Trigger();
        trigger.setName(getValue(map, "TRIGGER_NAME"));
        trigger.setTriggerType(getValue(map, "TRIGGER_TYPE"));
        trigger.setEventType(getValue(map, "EVENT_TYPE"));
        trigger.setDefinition(getValue(map, "TRIGGER_BODY"));
        trigger.setDescription(getValue(map, "DESCRIPTION"));
        return trigger;
    }

    protected String getValue(Map<String, String>map, String key) {
        assert !map.containsKey(key) : map.toString() + "不包含key[" + key + "]";
        return map.get(key);
    }
}
