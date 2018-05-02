package com.itcast.jd.service;

import com.itcast.jd.po.Result;

/**
 * @author congzi
 * @Description:
 * @create 2018-05-02
 * @Version 1.0
 */
public interface ProductService {

    /**
     * 根据条件从索引库查询数据
     * @param queryString 查询的关键词
     * @param catalog_name 商品分类
     * @param price     商品价格
     * @param sort      排序
     * @param page      当前页
     * @return
     */
    Result search
            (String queryString,String catalog_name,String price, String sort,Integer page)throws  Exception;

}
