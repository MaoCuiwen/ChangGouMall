package com.changgou.search.service;

public interface SkuService {
    /**
     * 从MySQL数据库中导入数据到elasticsearch
     */
    void importSku();
}
