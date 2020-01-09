package com.tangshengbo.shardingjdbc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


class BaseSourceConfig {

    SqlSessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setFailFast(true);
//        sessionFactory.setTypeAliasesPackage("com.tangshengbo.shardingjdbc.model");
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*.xml"));
        return sessionFactory.getObject();
    }
}
