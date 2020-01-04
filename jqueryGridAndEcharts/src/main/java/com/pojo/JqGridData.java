package com.pojo;

import java.util.List;

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
