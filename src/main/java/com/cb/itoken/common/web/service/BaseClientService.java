package com.cb.itoken.common.web.service;

import com.cb.itoken.common.hystrix.Fallback;

/**
 * @Author:congbing
 * @Description 通用服务消费者接口
 * @Date: 0:19 2019/1/28
 **/
public interface BaseClientService {
    default String page(int pageNum, int PageSize, String domainJson){
        return Fallback.badGateway();
    }
}
