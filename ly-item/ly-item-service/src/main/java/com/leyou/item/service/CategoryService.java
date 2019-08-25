package com.leyou.item.service;


import com.leyou.item.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategoryService {
    List<CategoryDTO> queryListByParent(Long pid);
}