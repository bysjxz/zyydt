package com.cdutcm.core.page2;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Auther: Chandler
 * @Date: 2018/7/22 12:26
 * @Description:
 */
public class Page<T> {
    private int firstIndex=0;//起始页
    private int pageSize;//每页的数量
    private int pageCount=1;//当前页
    private int totalPageCount;//总页数
    private int totalDataCount;//数据总量
    private String keyWord_1=null;//关键字
    private String keyWord_2=null;//关键字
    private String keyWord_3=null;//关键字
    private List<T> list;//实体类数组

    public String getKeyWord_3() {
        return keyWord_3;
    }

    public void setKeyWord_3(String keyWord_3) {
        this.keyWord_3 = keyWord_3;
    }

    public String getKeyWord_1() {
        return keyWord_1;
    }

    public void setKeyWord_1(String keyWord_1) {
        this.keyWord_1 = keyWord_1;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalDataCount() {
        return totalDataCount;
    }

    public void setTotalDataCount(int totalDataCount) {
        this.totalDataCount = totalDataCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getKeyWord_2() {
        return keyWord_2;
    }

    public void setKeyWord_2(String keyWord_2) {
        this.keyWord_2 = keyWord_2;
    }
}