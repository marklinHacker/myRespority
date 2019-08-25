package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description: BrandService <br>
 * date: 2019/8/25 9:40 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
public interface BrandService {
    PageResult<BrandDTO> queryBrandByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc);

    void saveBrand(@Param("brandDTO") BrandDTO brand, @Param("ids") List<Long> ids);
}
