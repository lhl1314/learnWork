package com.pojo;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/27 14:31
 * @description TODO
 */
public class JqGridData {
    private int page;        // 当前页数
    private int total;       // 总页数
    private long records;     // 总记录数
    private List<?> rows;     // 每页显示的内容


    public JqGridData() {
    }

    public JqGridData(int page, int total, long records, List<?> rows) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * 多级表格数据返回结构
     * 比如表格返回的详情表格
     * @param list
     * @return
     */
    public static Map<String,Object> getJsonReaderData(List<Map<String, Object>>list){
        Map<String,Object>map=new HashMap<>();
        map.put("page","1");
        map.put("total","1");
        map.put("records",list.size());
        List<Map<String,Object>>mapList=new ArrayList<>();
        list.forEach(t->{
            Map<String,Object>contentMap=new HashMap<>();
            contentMap.put("id",t.get("id"));//每行的id
//            'id', 'name', 'countryCode', 'district', 'population'
            List<Object>a=new ArrayList<>();
            a.add(t.get("id"));
            a.add(t.get("name"));
            a.add(t.get("countryCode"));
            a.add(t.get("district"));
            a.add(t.get("population"));
            contentMap.put("cell",a);//内容放数组汇总
            mapList.add(contentMap);
        });
        map.put("rows",mapList);
        return map;
    }




    @Override
    public String toString() {
        return "JqGridData{" +
                "page=" + page +
                ", total=" + total +
                ", records=" + records +
                ", rows=" + rows +
                '}';
    }
}
