package com.leyou.item.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/*
1.根据当前分类的cid查询规格组的参数
 */
/**
 * description: SpecGroup <br>
 * date: 2019/8/28 11:32 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@Data
@Table(name="tb_spec_group")
public class SpecGroup {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Long cid;
    private Date createTime;
    private Date updateTime;
}
