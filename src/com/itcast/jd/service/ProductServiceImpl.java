package com.itcast.jd.service;

import com.itcast.jd.po.Product;
import com.itcast.jd.po.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author congzi
 * @Description:
 * @create 2018-05-02
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private HttpSolrServer httpSolrServer;

    @Override
    public Result search(String queryString, String catalog_name, String price, String sort, Integer page) throws Exception{

        SolrQuery solrQuery = new SolrQuery();

        if(StringUtils.isNotBlank(queryString)){
            solrQuery.setQuery(queryString);
        }else{
            solrQuery.setQuery("*:*");
        }

        //设置默认的域
        solrQuery.set("df","product_keywords");

        //设置分类名称过滤条件
        if(StringUtils.isNotBlank(catalog_name)){
            solrQuery.addFilterQuery("product_catalog_name:" + catalog_name);
        }

        //设置价格区间过滤
        if(StringUtils.isNotBlank(price)){

            String[] arr = price.split("-");
            if(arr != null && arr.length == 2){
                solrQuery.addFilterQuery("product_price:[" + arr[0] +" TO "+arr[1] + "]");
            }
        }

        //设置排序
        if("1".equals(sort)){
            solrQuery.setSort("product_price", SolrQuery.ORDER.asc);
        }else{
            solrQuery.setSort("product_price", SolrQuery.ORDER.desc);
        }

        if(page == null){
            page = 1;
        }

        //分页
        solrQuery.setRows(20);
        solrQuery.setStart((page - 1)*20);

        //高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("product_name");
        solrQuery.setHighlightSimplePre("<font style='color:red' >");
        solrQuery.setHighlightSimplePost("</font>");

        //执行搜索
        QueryResponse response = httpSolrServer.query(solrQuery);

        //获取记录
        SolrDocumentList results = response.getResults();

        //获取总记录数
        long count = results.getNumFound();

        //获取高亮
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        //遍历结果
        List<Product> products = new ArrayList<>();

        Product product = null;
        for (SolrDocument sd:results
             ) {
            product = new Product();

            product.setPid(sd.get("id").toString());

            List<String> list = highlighting.get(sd.get("id")).get("product_name");

            if(list != null && list.size() > 0){
                product.setName(list.get(0));
            }else{
                product.setName(sd.get("product_name").toString());
            }

            product.setPrice(sd.get("product_price").toString());
            product.setPicture(sd.get("product_picture").toString());

            products.add(product);
        }

        //计算总页数
        int pageCount = (int)(count / 20);

        if(count % 20 > 0){
            pageCount++;
        }

        //封装返回结果
        Result result = new Result();
        result.setCurPage(page);
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setProductList(products);

        return result;
    }
}
