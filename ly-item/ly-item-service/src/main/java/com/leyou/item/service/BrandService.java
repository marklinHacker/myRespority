package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.entity.Brand;

import java.util.List;

/**
 * description: BrandService <br>
 * date: 2019/8/25 9:40 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
public interface BrandService {
    PageResult<BrandDTO> queryBrandByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc);

    void saveBrand( BrandDTO brand,List<Long> ids);

    void updateBrand(BrandDTO brandDTO, List<Long> ids);

    BrandDTO queryById(Long brandId);

    Brand queryBrandName(Long brandId);

    List<BrandDTO> queryBrandByCategoryId(Long id);

    /*  PageResult<BrandDTO> queryBrandByPages(Integer page, Integer rows);*/
}
