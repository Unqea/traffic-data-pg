/**
  * Copyright 2024 bejson.com 
  */
package com.traffic.test.domain;
import java.util.List;

/**
 * Auto-generated: 2024-02-04 14:35:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private List<Data> data;
    private int pageNum;
    private int pageSize;
    private int totalNum;
    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setPageNum(int pageNum) {
         this.pageNum = pageNum;
     }
     public int getPageNum() {
         return pageNum;
     }

    public void setPageSize(int pageSize) {
         this.pageSize = pageSize;
     }
     public int getPageSize() {
         return pageSize;
     }

    public void setTotalNum(int totalNum) {
         this.totalNum = totalNum;
     }
     public int getTotalNum() {
         return totalNum;
     }

}