package com.tangshengbo.shardingjdbc.config;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import javax.sql.DataSource;


class BaseSourceConfig {

    SqlSessionFactory getSessionFactory(DataSource dataSource, String[] aliasPackages) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(String.join(",", aliasPackages));
        SqlSessionFactory factory = sessionFactory.getObject();
        Configuration configuration = factory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        return factory;
    }
}
