package net.spfwork.forum.dto;

import java.util.List;

/**
 * 分页数据传输对象
 * 用于封装分页查询结果，包含分页信息和数据列表
 * @param <T> 数据列表中的元素类型
 */
public class PageDTO<T> {
    private int pageSize;      // 每页记录数
    private int pageNumber;    // 当前页码
    private int totalRecords;  // 总记录数
    private int totalPages;    // 总页数

    private List<T> list;      // 数据列表


    /**
     * 构造函数，初始化分页信息
     * @param pageNumber 当前页码
     * @param pageSize 每页记录数
     * @param totalRecords 总记录数
     */
    public PageDTO(int pageNumber,int pageSize,int totalRecords){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        // 计算总页数
        if (totalRecords%pageSize==0){
            pageNumber=totalRecords/pageSize;
        }else{
         pageNumber=totalRecords/pageSize+1;

        }
    }

    // 获取数据列表
    public List<T> getList() {
        return list;
    }

    // 设置数据列表
    public void setList(List<T> list) {
        this.list = list;
    }

    // 获取每页记录数
    public int getPageSize() {
        return pageSize;
    }

    // 设置每页记录数
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    // 获取当前页码
    public int getPageNumber() {
        return pageNumber;
    }

    // 设置当前页码
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    // 获取总记录数
    public int getTotalRecords() {
        return totalRecords;
    }

    // 设置总记录数
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    // 获取总页数
    public int getTotalPages() {
        return totalPages;
    }

    // 设置总页数
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", totalRecords=" + totalRecords +
                ", totalPages=" + totalPages +
                ", list=" + list +
                '}';
    }
}
