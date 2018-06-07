package com.marco.config.redis.key;

/**
 * Created by landun on 2018/6/6.
 */
public class AbstractPrefix implements Prefix {
    //超时时间
    private int expire;
    //前缀
    private String prefix;

    protected AbstractPrefix(){
        expire = 0;
        this.prefix = getClass().getSimpleName();
    }

    protected AbstractPrefix(int expire) {
        this.expire = expire;
        this.prefix = getClass().getSimpleName();
    }

    @Override
    public int getExpire() {
        return expire;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }
}
