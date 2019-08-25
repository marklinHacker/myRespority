package com.leyou.item.controller;

import com.leyou.item.dto.BrandDTO;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leyou.common.vo.PageResult;
import java.util.List;

/**
 * description: BrandController <br>
 * date: 2019/8/24 21:01 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;


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
    @GetMapping("/page")
    public ResponseEntity<PageResult<BrandDTO>> queryBrandByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                 @RequestParam(value = "rows", defaultValue = "5") Integer rows,
                                                                 @RequestParam(value = "key", required = false) String key,
                                                                 @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                 @RequestParam(value = "desc", required = false) Boolean desc) {
/**
 * {"total":165,"totalPage":null,"items":[{"id":1115,"name":"HTC","letter":null,"image":""},{"id":1528,"name":"LG","letter":null,"image":""},{"id":1912,"name":"NEC","letter":null,"image":""},{"id":2032,"name":"OPPO","letter":null,"image":"http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg"},{"id":2505,"name":"TCL","letter":null,"image":""}]}
 */
        return ResponseEntity.ok(brandService.queryBrandByPage(page, rows, key, sortBy, desc));
    }

    /**新增品牌
     * @param brand 返回前端的品牌数据
     * @param ids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(BrandDTO brand, @RequestParam("cids")List<Long> ids) {
        brandService.saveBrand(brand, ids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
