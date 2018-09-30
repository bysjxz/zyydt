package com.cdutcm.core.page2;

/**
 * @Auther: Chandler
 * @Date: 2018/7/22 12:27
 * @Description:
 */
public class PageUtil {
    public static Page<Object> queryPage(Integer currentPage,int queryCount,int pageSize){
        Page<Object> page= new Page<>();
        int firstIndex =pageSize*(currentPage-1);//计算起始计数位置
        page.setPageSize(pageSize);//设置每页的数量
        int totalPageCount = (queryCount+ page.getPageSize()-1)/page.getPageSize();//计算page的页数
        page.setFirstIndex(firstIndex);
        page.setTotalPageCount(totalPageCount);
        page.setPageCount(currentPage);
        page.setTotalDataCount(queryCount);
        return page;
    }
}