package com.alex.core.page;

import java.util.List;

/**
 * @Description: 分页返回结果
 * @Author:     alex
 * @CreateDate: 2019/12/10 19:49
 * @Version:    1.0
 *
*/
public class PageResult {

    //当前页码
    private int pageNum;

    //每页数量
    private int pageSize;

    //记录总数
    private long totalSize;

    //页码总数
    private long totalPage;

    //每页数据
    private List<?> content;

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

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
