package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.SpuDTO;

/**
 * description: SpuService <br>
 * date: 2019/8/29 21:41 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
public interface SpuService {
    PageResult<SpuDTO> queryGoods(Integer page, String key, Boolean saleable, Integer rows);

    void updateSaleable(Long id, Boolean saleable);

}
