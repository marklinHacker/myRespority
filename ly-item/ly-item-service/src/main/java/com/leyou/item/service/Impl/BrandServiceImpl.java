package com.leyou.item.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.entity.Brand;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description: BrandServiceImpl <br>
 * date: 2019/8/25 9:47 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;

    /**查询品牌结果分页
     * @param page 当前页 int类型
     * @param rows 每页大小 int类型
     * @param key 搜索关键字 String类型
     * @param sortBy 排序字段 String类型
     * @param desc 是否为降序 Boolean类型
     * @return 1.total 总条数
     * 2.items 当前页数据
     * 3.totalPage 有些还需要总页数
     */
    @Override
    public PageResult<BrandDTO> queryBrandByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        //分页
        /**
         * Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=0, pages=0, reasonable=null, pageSizeZero=null}[]
         */
        System.out.println(PageHelper.startPage(page, rows));

        //拼接查询条件
        Example example = new Example(Brand.class);
        System.out.println("example = " + example);
        if (StringUtils.isNoneBlank(key)) {
            //创建模糊查询根据名字，
            example.createCriteria().orLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        //排序
        if (StringUtils.isNoneBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);//id  desc
        }
        System.out.println("example = " + example.toString());
        //根据查询条件拼接查询到品牌的所有属性数据
        List<Brand> brands = brandMapper.selectByExample(example);
        /**
         * brands = Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=165, pages=33, reasonable=false, pageSizeZero=false}[Brand(id=1115, name=HTC, image=, letter=H, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=1528, name=LG, image=, letter=L, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=1912, name=NEC, image=, letter=N, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=2032, name=OPPO, image=http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg, letter=O, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=2505, name=TCL, image=, letter=T, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019)]
         */
        System.out.println("brands = " + brands);
        //判断是否为空
        if (CollectionUtils.isEmpty(brands)) {
            //如果为空就抛出异常，携带异常信息
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //解析分页结果
        /**
         * info = PageInfo{pageNum=1, pageSize=5, size=5, startRow=1, endRow=5, total=165, pages=33, list=Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=165, pages=33, reasonable=false, pageSizeZero=false}[Brand(id=1115, name=HTC, image=, letter=H, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=1528, name=LG, image=, letter=L, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=1912, name=NEC, image=, letter=N, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=2032, name=OPPO, image=http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg, letter=O, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019), Brand(id=2505, name=TCL, image=, letter=T, createTime=Wed Feb 27 22:54:12 CST 2019, updateTime=Wed Feb 27 22:54:29 CST 2019)], prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=8, navigatepageNums=[1, 2, 3, 4, 5, 6, 7, 8]}
         */
        PageInfo<Brand> info = new PageInfo<>(brands);
        System.out.println("info = " + info);
        //转为BrandDTO
        List<BrandDTO> list = BeanHelper.copyWithCollection(brands, BrandDTO.class);
        System.out.println("list = " + list);
        /**
         * list = [BrandDTO(id=1115, name=HTC, letter=null, image=),
         * BrandDTO(id=1528, name=LG, letter=null, image=),
         * BrandDTO(id=1912, name=NEC, letter=null, image=),
         * BrandDTO(id=2032, name=OPPO, letter=null, image=http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg),
         * BrandDTO(id=2505, name=TCL, letter=null, image=),
         * BrandDTO(id=3177, name=爱贝多, letter=null, image=),
         * BrandDTO(id=3539, name=安桥（ONKYO）, letter=null, image=),
         * BrandDTO(id=3941, name=白金（PLATINUM）, letter=null, image=), BrandDTO(id=4986, name=波导（BiRD）, letter=null, image=),
         * BrandDTO(id=6522, name=朵唯（DOOV）, letter=null, image=),
         * BrandDTO(id=6742, name=飞利浦（PHILIPS）, letter=null, image=http://img12.360buyimg.com/popshop/jfs/t18361/122/1318410299/1870/36fe70c9/5ac43a4dNa44a0ce0.jpg),
         * BrandDTO(id=7174, name=富可视（InFocus）, letter=null, image=),
         * BrandDTO(id=7203, name=富士通（Fujitsu）, letter=null, image=),
         * BrandDTO(id=7420, name=格力（GREE）, letter=null, image=),
         * BrandDTO(id=7817, name=海尔（Haier）, letter=null, image=http://image.leyou.com/group1/M00/00/00/wKg4ZVrYZcyAVOzoAAB846UcmLg013.png), BrandDTO(id=7888, name=海信（Hisense）, letter=null, image=), BrandDTO(id=8214, name=黑莓（BlackBerry）, letter=null, image=), BrandDTO(id=8551, name=华硕（ASUS）, letter=null, image=), BrandDTO(id=8557, name=华为（HUAWEI）, letter=null, image=http://img10.360buyimg.com/popshop/jfs/t5662/36/8888655583/7806/1c629c01/598033b4Nd6055897.jpg), BrandDTO(id=8740, name=惠普（HP）, letter=null, image=), BrandDTO(id=9420, name=金立（Gionee）, letter=null, image=), BrandDTO(id=9637, name=京瓷（KYOCERA）, letter=null, image=), BrandDTO(id=10317, name=康佳（KONKA）, letter=null, image=), BrandDTO(id=10640, name=酷派（Coolpad）, letter=null, image=http://img10.360buyimg.com/popshop/jfs/t2521/347/883897149/3732/91c917ec/5670cf96Ncffa2ae6.jpg), BrandDTO(id=11516, name=联想（Lenovo）, letter=null, image=http://img11.360buyimg.com/popshop/jfs/t6799/74/1348425607/15774/bc286188/59804c98N1944175e.jpg)]
         */
        return new PageResult<BrandDTO>(info.getTotal(), list);
    }

    @Override
    public void saveBrand(BrandDTO brandDTO, List<Long> ids) {
        //新增品牌
        //将brandDTO转化为Brand
       Brand brand= BeanHelper.copyProperties(brandDTO, Brand.class);
        brand.setId(null);
        //新增品牌
        //这里调用了brandMapper中的一个自定义方法，来实现中间表的数据新增
        int count = brandMapper.insertSelective(brand);
        //如果count不为1，说明新增没有成功
        if (count != 1) {
            //抛出异常
            throw new LyException(ExceptionEnum.INSERT_OPEATION_FAIL);
        }
      count = brandMapper.insertCategoryBrand(brand.getId(),ids);
        if (count != ids.size()) {
            throw new LyException(ExceptionEnum.INSERT_OPEATION_FAIL);
        }
    }
}
