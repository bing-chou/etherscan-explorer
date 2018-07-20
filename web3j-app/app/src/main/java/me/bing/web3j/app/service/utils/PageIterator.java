package me.bing.web3j.app.service.utils;

import java.util.ArrayList;
import java.util.List;

public class PageIterator<T> {

    private int page;
    private int pageSize;
    private int totalPage;
    private long totalCount;
    private List<T> data;

    public static <T> PageIterator<T> create(int page, int pageSize, long totalCount) {
        PageIterator<T> instance = new PageIterator<>();
        instance.setPage(page);
        instance.setPageSize(pageSize);
        instance.setTotalCount(totalCount);
        instance.setTotalPage((int) Math.ceil((double) totalCount / (double) pageSize));
        instance.setData(new ArrayList<>());
        return instance;
    }

    public static <T> PageIterator<T> create(PageIterator iterator0) {
        PageIterator<T> instance = create(iterator0.getPage(), iterator0.pageSize, iterator0.totalCount);
        instance.setData(new ArrayList<>());
        return instance;
    }

    public int getPage() {
        return page;
    }

    private void setPage(int page) {
        this.page = page < 0 ? 0 : page;
    }

    public int getPageSize() {
        return pageSize;
    }

    private void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    public int getCurrentPage() {
        return getPage();
    }
}

