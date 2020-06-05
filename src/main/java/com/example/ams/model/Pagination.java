package com.example.ams.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {
    private int page;
    private int limit;
    @JsonProperty("total_page")
    private int totalPage;
    @JsonProperty("total_record")
    private int totalRecord;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
