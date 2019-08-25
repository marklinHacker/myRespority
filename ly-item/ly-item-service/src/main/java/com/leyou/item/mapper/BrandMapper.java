package com.leyou.item.mapper;

import com.leyou.item.entity.Brand;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * description: BrandMapper <br>
 * date: 2019/8/25 0:40 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
public interface BrandMapper extends Mapper<Brand> {
    int insertCategoryBrand(@Param("bid")Long bid, @Param("ids")List<Long> ids);
}
