package com.changgou.goods.pojo;

import lombok.Data;

import java.util.List;
@Data
public class Goods {
    private Spu spu;
    private List<Sku> skuList;
}
