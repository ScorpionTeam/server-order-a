package com.kunlun.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/14.
 */
@Configuration
public class DruidConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver relaxedPropertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");

    }

//    @Bean
//    public ServletRegistrationBean druidServlet() {
//        return new ServletRegistrationBean(new StatViewServlet(), "druid/*");
//    }

    /**
     * 初始化数据库连接池
     *
     * @return
     */
    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(relaxedPropertyResolver.getProperty("url"));
        dataSource.setUsername(relaxedPropertyResolver.getProperty("username"));
        dataSource.setPassword(relaxedPropertyResolver.getProperty("password"));
        dataSource.setDriverClassName(relaxedPropertyResolver.getProperty("driver-class-name"));
        dataSource.setInitialSize(Integer.parseInt(relaxedPropertyResolver.getProperty("initialSize")));
        dataSource.setMinIdle(Integer.valueOf(relaxedPropertyResolver.getProperty("minIdle")));
        dataSource.setMaxWait(Long.valueOf(relaxedPropertyResolver.getProperty("maxWait")));
        dataSource.setMaxActive(Integer.valueOf(relaxedPropertyResolver.getProperty("maxActive")));
        dataSource.setPoolPreparedStatements(
                Boolean.valueOf(relaxedPropertyResolver.getProperty("poolPreparedStatements")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(
                Integer.valueOf(relaxedPropertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));
        return dataSource;
    }


}
