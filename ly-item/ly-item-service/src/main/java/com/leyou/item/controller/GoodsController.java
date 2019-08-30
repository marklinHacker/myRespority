package com.leyou.item.controller;

import com.leyou.common.vo.PageResult;
import com.leyou.item.dto.SpuDTO;
import com.leyou.item.entity.SpuDetail;
import com.leyou.item.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * description: GoodsController <br>
 * date: 2019/8/29 21:34 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/spu")
public class GoodsController {
    @Autowired
    private SpuService spuService;

    /**
     * 查询商品分页
     *
     * @param page
     * @param key
     * @param saleable
     * @param rows
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<SpuDTO>> queryGoods(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(value = "key", required = false) String key,
                                                         @RequestParam(value = "saleable", required = false) Boolean saleable,
                                                         @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        return ResponseEntity.ok(this.spuService.queryGoods(page, key, saleable, rows));
    }

    @PutMapping("/saleable")
    public ResponseEntity<Void> updateSaleable(@RequestParam("id") Long id, @RequestParam("saleable") Boolean saleable) {
        this.spuService.updateSaleable(id,saleable);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
/*

    */
/**修改商品
     * @param id
     * @return
     *//*

    @GetMapping("detail")
    public ResponseEntity<SpuDetailDTO> editGoods(@RequestParam("id")Long id) {
      return ResponseEntity.ok(this.spuService.editSpu(id));
    }
*/

}
