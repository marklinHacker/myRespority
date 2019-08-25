package com.leyou.common.vo;

import lombok.Data;

import java.util.List;

/**
 * description: PageResult <br>
 * date: 2019/8/25 0:47 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@Data
public class PageResult<T> {
    private Long total;// 总条数
    private Integer totalPage;// 总页数
    private List<T> items;// 当前页数据

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult() {
    }
}
