package com.leyou.common.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    CATEGORY_NOT_FOND(204,"未找到网页！"),
    DATA_TRANSFER_ERROR(202,"【数据转换】数据转换出错，目标对象{}构造函数异常"),
    BRAND_NOT_FOUND(204,"找不到品牌信息"),
    INSERT_OPEATION_FAIL(500,"品牌新增失败"),
    BRAND_INSERT_ERROR(500,"品牌修改失败"),
    INVALID_FILE_TYPE(400,"图片不可用"),
    FILE_UPLOAD_ERROR(500,"图片上传失败"),
    SPECGROUP_NOT_FOUND(204,"商品规格参数组不存在"),
    SPECPARAM_NOT_FOUND(204,"规格参数不存在"),
    INSERT_SPECGROUP_FAIL(500,"规格组新增失败"),
    GOODS_NOT_FOUND(204,"商品不存在"),
    GOODS_OPERATOR_ERROR(500,"商品上下架操作失败");

    private int status;
    private String message;


    ExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}