package com.leyou.item.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.dto.SpuDTO;
import com.leyou.item.entity.Brand;
import com.leyou.item.entity.Category;
import com.leyou.item.entity.Spu;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.SpuService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;


/**
 * description: SpuServiceImpl <br>
 * date: 2019/8/29 21:42 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SpuDetailMapper detailMapper;
    /**
     * 查询商品分页信息
     *
     * @param page
     * @param key
     * @param saleable
     * @param rows
     * @return
     */
    @Override
    public PageResult<SpuDTO> queryGoods(Integer page, String key, Boolean saleable, Integer rows) {
        Page page1 = PageHelper.startPage(page, rows);
        //查询语句查询商品id，name
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(key)) {
            criteria.andLike("name", "%" + key + "%");
        }
        if (saleable != null) {
            criteria.andEqualTo("saleable",saleable);
        }
//select
        List<Spu> spuList = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spuList)) {
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);
        long total = pageInfo.getTotal();

        List<SpuDTO> spuDTOList = BeanHelper.copyWithCollection(spuList, SpuDTO.class);
        for (SpuDTO spuDTO : spuDTOList) {
            //查询品牌id
            Brand brand= this.brandService.queryBrandName(spuDTO.getBrandId());
            spuDTO.setBrandName(brand.getName());

         List<CategoryDTO> categoryDTOList =  this.categoryService.queryCategoryNameById(spuDTO.getCategoryIds());
            String names = categoryDTOList.stream()
                    .map(CategoryDTO::getName)
                    .collect(Collectors.joining("/"));

            spuDTO.setCategoryName(names);

        }
        return new PageResult(total,pageInfo.getPages(),spuDTOList);
    }

    /**修改商品上下架
     * @param id
     * @param saleable
     */
    @Override
    public void updateSaleable(Long id, Boolean saleable) {
//
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (saleable != null) {
            criteria.andEqualTo("id",id);
        }
        Spu spu = new Spu();
        spu.setSaleable(saleable);
                //传入一个对象
        //update tb_spu set saleable=?
        int count = this.spuMapper.updateByExampleSelective(spu, example);
        if (1 != count) {
            throw new LyException(ExceptionEnum.GOODS_OPERATOR_ERROR);
        }
    }

}
