package com.alex.core.page;

/**
 * @Description: 分页查询过滤器
 * @Author:     alex
 * @CreateDate: 2019/12/10 19:47
 * @Version:    1.0
 *
*/
public class ColumnFilter {

    //过滤列名
    private String name;

    //查询值
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
