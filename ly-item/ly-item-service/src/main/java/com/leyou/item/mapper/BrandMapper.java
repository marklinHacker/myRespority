package com.leyou.item.mapper;

import com.leyou.item.entity.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
@Delete("delete from tb_category_brand  where brand_id=#{bid}")
    void deleteCategoryBrand(@Param("bid") Long bid);
@Select("select tb.id,tb.`name` FROM tb_category_brand tcb, tb_brand tb WHERE tcb.brand_id=tb.id AND tcb.category_id=#{id} ")
    List<Brand> selectBrandByCategoryId(@Param("id") Long id);
}
