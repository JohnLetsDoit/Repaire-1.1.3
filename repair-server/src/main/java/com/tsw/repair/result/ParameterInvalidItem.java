package com.tsw.repair.result;

/**
 * Controller参数校验 错误返回封装
 *
 * @author
 * <p>
 * 2019年6月9日
 */
public class ParameterInvalidItem {

    private String fieldName;

    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
