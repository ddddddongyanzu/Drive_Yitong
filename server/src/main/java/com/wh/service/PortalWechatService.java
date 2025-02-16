package com.wh.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author panda
 * @date 2021/4/27 14:10
 * <p>
 * description service
 */
public interface PortalWechatService {

    /**
     * 微信支付回调
     * @param request 请求
     * @return 结果
     */

    String callBack(HttpServletRequest request);
}

