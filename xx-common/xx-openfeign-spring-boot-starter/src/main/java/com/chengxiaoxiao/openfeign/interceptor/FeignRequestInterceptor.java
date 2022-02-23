package com.chengxiaoxiao.openfeign.interceptor;

import com.chengxiaoxiao.core.constant.SecurityConstant;
import com.chengxiaoxiao.core.util.IpUtil;
import com.chengxiaoxiao.core.util.RequestUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 请求拦截器，用以服务调用时进行请求的拦截，添加HEADER
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-23 11:20
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = RequestUtil.getRequest();
        if (httpServletRequest != null) {
            Map<String, String> headers = RequestUtil.getHeaders(httpServletRequest);
            
            // 传递用户信息请求头，防止丢失
            String userId = headers.get(SecurityConstant.USER_ID);
            if (StringUtils.isNotEmpty(userId)) {
                requestTemplate.header(SecurityConstant.USER_ID, userId);
            }

            String userName = headers.get(SecurityConstant.USER_NAME);
            if (StringUtils.isNotEmpty(userId)) {
                requestTemplate.header(SecurityConstant.USER_NAME, userName);
            }

            String authentication = headers.get(SecurityConstant.AUTHORIZATION);
            if (StringUtils.isNotEmpty(authentication)) {
                requestTemplate.header(SecurityConstant.AUTHORIZATION, authentication);
            }

            // 配置客户端IP
            requestTemplate.header("X-Forwarded-For", IpUtil.getIpAddr(httpServletRequest));
        }
    }
}
