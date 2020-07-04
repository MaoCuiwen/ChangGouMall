package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Override
    public void importSku() {
        //远程调用服务获取审核通过的所有sku数据(注：实际开发中应该分页获取)
        Result<List<Sku>> result = skuFeign.findByStatus("1");
        List<Sku> skus = result.getData();

        //将sku数据转换为skuinfo数据
        List<SkuInfo> skuInfos=  JSON.parseArray(JSON.toJSONString(skus),SkuInfo.class);

        for(SkuInfo skuInfo:skuInfos){
            Map<String, Object> specMap= JSON.parseObject(skuInfo.getSpec()) ;
            skuInfo.setSpecMap(specMap);
        }

        //保存到elasticsearch中
        skuEsMapper.saveAll(skuInfos);

    }
}
