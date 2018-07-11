package com.marco.result;

/**
 * Created by landun on 2018/5/23.
 * 该类用于规范所有返回的json response报文
 */
public class Msg<T> {
    private int code;
    private String status;
    private T data;

    public static Msg LOGIN_USER_NOT_EXIST = new Msg(500101, "账号或密码错误");
    public static Msg LOGIN_PWD_ERROR = new Msg(500102, "密码错误");
    public static Msg LOGIN_IMGCODE_ERROR = new Msg(500103, "验证码错误");


    public static Msg ORDER_NO_LEVEL = new Msg(500301, "订单库存不足");

    /**
     * 当操作成功时状态码都是固定的
     * 状态信息也是固定的
     * 成功时只需传一个结果集
     *
     * @param data
     * @return
     */
    public static Msg returnSuccess(Object data) {
        return new Msg(data);
    }

    /**
     * 私有化构造方法
     * 用于构建成功结果集调用
     *
     * @param data
     */
    private Msg(T data) {
        this.code = 500200;
        this.status = "success";
        this.data = data;
    }

    private Msg(int code, String status) {
        this.code = code;
        this.status = status;
    }

    private Msg() {
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
