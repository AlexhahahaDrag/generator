package com.alex.core.page;

import com.alex.common.util.ReflectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: Mybatis分页查询助手
 * @Author:     alex
 * @CreateDate: 2019/12/10 20:00
 * @Version:    1.0
 *
*/
public class MybatisPageHelper {

    public static final String findPage = "findPage";

    /**
     * @Description: 分页查询，约定查询方法名为"findpage";
     * @Author:      alex
     * @CreateDate:  2019/12/10 20:02
     * @param pageRequest 分页请求
     * @param mapper dao对象 mybatis的mapper
     * @return
    */
    public static PageResult findPage(PageRequest pageRequest, Object mapper) {
        return findPage(pageRequest, mapper, findPage);
    }

    /**
     * @Description: 调用分页插件进行分页查询
     * @Author:      alex
     * @CreateDate:  2019/12/10 20:38
     * @param pageRequest 分页请求
     * @param pageRequest queryMethodName
     * @param mapper dao对象 mybatis的mapper
     * @param args 方法参数
     * @return
    */
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        //利用反射调用查询方法
        Object result = ReflectionUtils.invoke(mapper, queryMethodName, args);
        return getPageResult(pageRequest, new PageInfo((List) result));
    }

    private static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageRequest.getPageNum());
        pageResult.setPageSize(pageRequest.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
