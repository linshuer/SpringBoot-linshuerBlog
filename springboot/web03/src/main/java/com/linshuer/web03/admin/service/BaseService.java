package com.linshuer.web03.admin.service;

import java.util.List;

public interface BaseService<T> {
    //查询所有
    List<T> findAll();
    //id查询
    List<T> findById(Long id);
    //添加
    void create(T t);
    //删除（批量）
    void  delete(Long... ids);
    //修改
    void update(T t);

}
