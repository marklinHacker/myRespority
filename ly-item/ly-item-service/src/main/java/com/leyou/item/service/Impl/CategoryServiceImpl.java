package com.leyou.item.service.Impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.dto.SpecGroupDTO;
import com.leyou.item.entity.Category;
import com.leyou.item.entity.SpecGroup;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * description: CategoryServiceImpl <br>
 * date: 2019/8/25 9:42 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**查询所有分类管理
     * @param pid
     * @return
     */
    public List<CategoryDTO> queryListByParent(Long pid) {
        // 查询条件，mapper会把对象中的非空属性作为查询条件
        Category t = new Category();
        t.setParentId(pid);
        List<Category> list = categoryMapper.select(t);
        System.out.println("list = " + list);
        // 判断结果
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        // 使用自定义工具类，把Category集合转为DTO的集合
        return BeanHelper.copyWithCollection(list, CategoryDTO.class);
    }

    /**根据品牌id查询分类列表（一个品牌可能对应多个分类）
     * @param brandId
     * @return
     */
    @Override
    public List<CategoryDTO> queryListByBrandId(Long brandId) {
      List<Category> list=categoryMapper.queryByBrandId(brandId);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        //将category转化为categoryDTO
        List<CategoryDTO> categoryDTOS = BeanHelper.copyWithCollection(list, CategoryDTO.class);
        return categoryDTOS;
    }

    @Override
    public List<CategoryDTO> queryCategoryNameById(List<Long> categoryIds) {
        List<Category> categoryList = this.categoryMapper.selectByIdList(categoryIds);
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
      /*  categoryIds.forEach(categoryId->{
            this.categoryMapper.selectByPrimaryKey(categoryId);
        });*/
        return BeanHelper.copyWithCollection(categoryList,CategoryDTO.class);
    }

}
