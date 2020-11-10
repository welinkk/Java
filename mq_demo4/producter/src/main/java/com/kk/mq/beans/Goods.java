package com.kk.mq.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//注解在类上，为类提供一个无参的构造方法。
@NoArgsConstructor
//注解在类上，为类提供一个全参的构造方法。
@AllArgsConstructor
public class Goods implements Serializable {
//Serializable 序列持久化，只有序列持久化之后才能进行持久化、网络传输
    private String goods_id;
    private String goods_name;
    private Double goods_price;
    private String goods_desc;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price=" + goods_price +
                ", goods_desc='" + goods_desc + '\'' +
                '}';
    }
}
