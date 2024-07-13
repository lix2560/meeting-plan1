package com.ahubimk.base.execption;

import java.io.Serializable;

/**
 * @Description 错误响应参数包装
 * @Author lixiao
 * @Date 2024/7/13
 */
public class RestErrorResponse implements Serializable {
    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }




}
