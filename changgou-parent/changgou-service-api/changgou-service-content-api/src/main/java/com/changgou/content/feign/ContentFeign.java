package com.changgou.content.feign;

import com.changgou.content.pojo.Content;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="content")  //指定调用的服务的名称。名称是对应微服务的应用名，定义在application.yml中
@RequestMapping("/content")  //请求需要映射的路径，写在controller类上的公共路径
public interface ContentFeign {
    /*
    根据分类的ID 获取到广告列表
     */
    @GetMapping(value = "/list/category/{id}")   //请求需要映射的路径，实际路径是/content/list/category/{id}
    Result<List<Content>> findByCategory(@PathVariable(name="id") Long id);
}