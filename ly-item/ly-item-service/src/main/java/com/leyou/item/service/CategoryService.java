package com.leyou.item.service;


import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.dto.SpecGroupDTO;
import com.leyou.item.entity.Category;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
public interface CategoryService {
    List<CategoryDTO> queryListByParent(Long pid);

    List<CategoryDTO> queryListByBrandId(Long brandId);

    List<CategoryDTO> queryCategoryNameById(List<Long> categoryIds);
}