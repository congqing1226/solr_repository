package com.itcast.jd.po;

import java.util.List;

/**
 * @author congzi
 * @Description:  分页结果 Entity
 * @create 2018-05-02
 * @Version 1.0
 */
public class Result {

    private Integer curPage;
    private Integer pageCount;
    private Long recordCount;
    private List<Product> productList;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Result{" +
                "curPage=" + curPage +
                ", pageCount=" + pageCount +
                ", recordCount=" + recordCount +
                ", productList=" + productList +
                '}';
    }
}
