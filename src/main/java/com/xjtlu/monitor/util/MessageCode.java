package com.xjtlu.monitor.util;

public class MessageCode {

    public static final MessageCode NO_CHAIN_FOUND = new MessageCode(99999, "No chain found");

    public static MessageCode SUCCESS = new MessageCode(1, "success");

    public static MessageCode EXIST_ERROR = new MessageCode(20001, "Exist error");

    public static MessageCode SERVER_ERROR=new MessageCode(50000,"服务端异常");
    public static MessageCode PASSWORD_EMPTY=new MessageCode(50001,"密码不能为空");
    public static MessageCode MOBILE_EMPTY=new MessageCode(50002,"手机号不能为空");
    public static MessageCode MOBILE_ERROR=new MessageCode(50003,"手机号格式错误");
    public static MessageCode NO_USER = new MessageCode(50004,"用户不存在");
    public static MessageCode PASSWORD_ERROR = new MessageCode(50005,"密码错误");











    // property
    private int code;
    private String message;

    // constructor
    public MessageCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // getter
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public MessageCode fillArgs(Object... args) {
        return new MessageCode(code, String.format(message, args));
    }
}
