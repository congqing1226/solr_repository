package com.itcast.jd.controller;

import com.itcast.jd.po.Result;
import com.itcast.jd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author congzi
 * @Description: 全文检索接口
 * @create 2018-05-02
 * @Version 1.0
 */

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;

    @RequestMapping("list")
    public String list
            (String queryString, String catalog_name, String price, String sort, Integer page, Model model){

        try {
            /**
             * 查询条件回显
             */
            model.addAttribute("queryString",queryString);
            model.addAttribute("catalog_name",catalog_name);
            model.addAttribute("price",price);
            model.addAttribute("sort",sort);

            /**
             * 获取结果 集
             */
            Result result = productService.search(queryString, catalog_name, price, sort, page);

            model.addAttribute("result",result);

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "product_list";
    }

}
