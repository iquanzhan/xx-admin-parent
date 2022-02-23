package com.chengxiaoxiao.core.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 请求工具类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/21 23:35
 */
public class RequestUtil {
    /**
     * 获取请求属性集
     *
     * @return 请求属性集
     */
    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取request
     *
     * @return request
     */
    public static HttpServletRequest getRequest() {
        try {
            return Objects.requireNonNull(getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取response
     *
     * @return response
     */
    public static HttpServletResponse getResponse() {
        try {
            return Objects.requireNonNull(getRequestAttributes()).getResponse();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取response
     *
     * @return response
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        assert request != null;

        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null)
        {
            while (enumeration.hasMoreElements())
            {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

}
