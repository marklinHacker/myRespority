package com.leyou.common.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    CATEGORY_NOT_FOND(204,"未找到网页！"),
    DATA_TRANSFER_ERROR(202,"【数据转换】数据转换出错，目标对象{}构造函数异常"),
    BRAND_NOT_FOUND(204,"找不到品牌信息"),
    INSERT_OPEATION_FAIL(500,"品牌新增失败");

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