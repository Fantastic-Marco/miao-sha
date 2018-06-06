package com.marco.result;

/**
 * Created by landun on 2018/5/23.
 */
public class Msg<T> {
    private int code;
    private String status;
    private T data;

    public static Msg ORDER_NO_LEVEL = new Msg(500301,"订单库存不足");

    public static Msg returnSuccess(Object data) {
        return new Msg(data);
    }

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
