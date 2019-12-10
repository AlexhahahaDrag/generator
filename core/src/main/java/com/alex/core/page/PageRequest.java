package com.alex.core.page;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 分页请求
 * @Author:     alex
 * @CreateDate: 2019/12/10 19:44
 * @Version:    1.0
 *
*/
public class PageRequest {

    //当前页码
    private int pageNum;

    //每页数量
    private int pageSize = 10;

    //过滤条件
    private Map<String, ColumnFilter> columnFilterMap = new HashMap<>();

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, ColumnFilter> getColumnFilterMap() {
        return columnFilterMap;
    }

    public void setColumnFilterMap(Map<String, ColumnFilter> columnFilterMap) {
        this.columnFilterMap = columnFilterMap;
    }
}
