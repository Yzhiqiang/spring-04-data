package com.yu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 14:53 2021/9/8
 * @Modified By:
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource()
    {
        return new DruidDataSource();
    }

//    后台监控:web.xml   ServletRegisterationBean
//    因为SPringBoot内置了Servlet容器，所以没有web.xml,  替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean a()
    {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

//        后台需要有人登录，账号密码设置
        HashMap<String, String> initParameters = new HashMap<>();

        initParameters.put("loginUsername","admin");    //登录key是固定的loginUsername loginPassword
        initParameters.put("loginPassword","123456");

        //允许谁能访问
        initParameters.put("allow","");
        bean.setInitParameters(initParameters); //初始化参数
//        //禁止谁能访问       initParameters.put("kuangshen","192.168.11.123");
        return bean;
    }

//    filter
    @Bean
    public FilterRegistrationBean webStatFilter()
    {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions","*.js,*.css, /druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }
}
