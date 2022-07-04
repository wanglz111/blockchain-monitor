package com.xjtlu.monitor.util;

public class Result<T> {

    private int code;
    private String message;
    private T data;

    private Result(T data) {
        this.code = 1;
        this.message = "success";
        this.data = data;
    }

    private Result(MessageCode messageCode) {
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
    }


     public static <T> Result<T> success(T data) {
         return new Result<T>(data);
     }

    public static <T> Result<T> error(MessageCode message) {
        return new Result<T>(message);
    }


// getter
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
