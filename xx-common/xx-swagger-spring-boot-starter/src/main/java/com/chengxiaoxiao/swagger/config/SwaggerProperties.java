package com.chengxiaoxiao.swagger.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/21 0021 15:01
 */
@Data
@ConfigurationProperties("xx.swagger")
public class SwaggerProperties {
    /**
     * 是否开启swagger
     */
    private Boolean enabled;

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";

    /**
     * swagger会解析的url规则
     **/
    private List<String> basePath = new ArrayList<>();


    /**
     * 是否开启header内，权限字段
     */
    private Boolean enabledAuth = true;

    /**
     * TOKEN在Header中的KEY
     */
    private String tokenHeader = "TOKEN";

    /**
     * 在basePath基础上需要排除的url规则
     **/
    private List<String> excludePath = new ArrayList<>();

    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";


    //private List<Class> ignoreClass = new ArrayList<>();

    /**
     * 版本
     **/
    private String version = "";

    /**
     * host信息
     **/
    private String host = "";

    /**
     * 联系人信息
     */
    private Contact contact = new Contact();


    @Data
    @NoArgsConstructor
    public static class Contact {

        /**
         * 联系人
         **/
        private String name = "";

        /**
         * 联系人url
         **/
        private String url = "";

        /**
         * 联系人email
         **/
        private String email = "";

    }
}
