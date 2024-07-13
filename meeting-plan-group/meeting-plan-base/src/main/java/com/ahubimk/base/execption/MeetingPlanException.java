package com.ahubimk.base.execption;

/**
 * @Description   排会系统异常类
 * @Author lixiao
 * @Date 2024/7/13
 */
public class MeetingPlanException extends RuntimeException{
    private String errMessage;

    public MeetingPlanException() {
        super();
    }

    public MeetingPlanException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError){
        throw new MeetingPlanException(commonError.getErrMessage());
    }
    public static void cast(String errMessage){
        throw new MeetingPlanException(errMessage);
    }



}
