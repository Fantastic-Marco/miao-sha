package com.marco.util;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by landun on 2018/7/6.
 */
public class TestUtil {

    public static void main(String[] args) {
        B b1 = new B(50);
        A a1 = new A("张三", b1);
        A a2 = a1.clone();
        A a3 = a1.deepClone();
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);

    }


}

class A implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    B b;

    public A(String name, B b) {
        this.name = name;
        this.b = b;
    }


    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", b=" + b +
                '}';
    }

    @Override
    public A clone() {
        try {
            return (A) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public A deepClone() {
        // 将对象写到流里
        try (
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                ObjectOutputStream oo = new ObjectOutputStream(bo);
        ) {
            oo.writeObject(this);
            // 从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (A) oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class B implements Serializable{
    private static final long serialVersionUID = 1L;
    int money;

    public B(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "B{" +
                "money=" + money +
                '}';
    }

}
