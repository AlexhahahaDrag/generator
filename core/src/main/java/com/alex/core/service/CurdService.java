package com.alex.core.service;

import com.alex.core.page.PageRequest;
import com.alex.core.page.PageResult;

import java.util.List;

/**
 * @Description: 通用curd
 * @Author:     alex
 * @CreateDate: 2019/12/10 19:40
 * @Version:    1.0
 *
*/
public interface CurdService<T> {

    /**
     * @Description: 保存操作
     * @Author:      alex
     * @CreateDate:  2019/12/10 19:41
     * @param
     * @return
    */
    int saveOrUpdate(T record);

    /**
     * @Description: 删除操作
     * @Author:      alex
     * @CreateDate:  2019/12/10 19:41
     * @param
     * @return
    */
    int delete(T record);

    /**
     * @Description: piliangshanchu
     * @Author:      alex
     * @CreateDate:  2019/12/10 19:42
     * @param
     * @return
    */
    int delete(List<T> records);

    /**
     * @Description: 根据id查询
     * @Author:      alex
     * @CreateDate:  2019/12/10 19:43
     * @param
     * @return
    */
    T findById(Long id);

    /**
     * @Description: 分页查询
     * @Author:      alex
     * @CreateDate:  2019/12/10 19:43
     * @param
     * @return
    */
    PageResult findPage(PageRequest pageRequest);
}
