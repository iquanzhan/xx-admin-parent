package com.chengxiaoxiao.database;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.chengxiaoxiao.database.config.DatabaseProperties;
import com.chengxiaoxiao.database.config.DruidProperties;
import com.chengxiaoxiao.database.handler.XxMetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * 数据库自动配置类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/21 0021 18:21
 */
@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({DatabaseProperties.class, DruidProperties.class})
@Import({XxMetaObjectHandler.class})
@Slf4j
public class DatabaseAutoConfiguration {

    @Resource
    DruidProperties druidProperties;

    /**
     * 乐观锁插件
     *
     * @return {OptimisticLockerInnerInterceptor}
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }


    /**
     * 逻辑删除插件
     */
    @Bean
    @ConditionalOnProperty(name = "xx.database.config.enable-logic-delete", matchIfMissing = true)
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


    /**
     * 注册druid的servlet
     *
     * @return servletBean
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        log.info("init Druid Servlet Configuration ...");
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");

        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", druidProperties.getUserName());
        servletRegistrationBean.addInitParameter("loginPassword", druidProperties.getPassword());
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册防火墙过滤器
     *
     * @return 过滤器注册实体
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}

