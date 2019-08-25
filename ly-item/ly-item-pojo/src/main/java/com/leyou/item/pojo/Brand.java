package com.leyou.item.pojo;

import lombok.Data;

import java.util.Date;

/**
 * description: Brand <br>
 * date: 2019/8/24 21:07 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */

/**
 *
 */
@Data
public class Brand {
    private Long id;
    private String name;
    private String image;
    private String letter;
    private Date createTime;
    private Date updateTime;
}
