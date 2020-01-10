//package com.tangshengbo.shardingjdbc.config;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.api.config.masterslave.LoadBalanceStrategyConfiguration;
//import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import tk.mybatis.spring.annotation.MapperScan;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.*;
//
///**
// * Created by Tangshengbo
// *
// * @author Tangshengbo
// * @date 2020/1/9
// */
//@Configuration
//@MapperScan(basePackages = {"com.tangshengbo.shardingjdbc.dao"},
//        sqlSessionFactoryRef = MasterSlaveDataSourceConfig.SESSION_FACTORY_NAME)
//public class MasterSlaveDataSourceConfig extends BaseSourceConfig {
//
//    public static final String SESSION_FACTORY_NAME = "sqlSessionFactory";
//
//    public static final String TRANSACTION_NAME = "transactionManager";
//
//    private static final String DATA_SOURCE_NAME = "dataSource";
//
//    /**
//     * 创建数据源dataSourceMaster
//     *
//     * @return 返回数据源
//     */
//    @Bean(name = "dataSourceMaster")
//    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master")
//    public DataSource dataSourceMaster() {
//        return new BasicDataSource();
//    }
//
//    /**
//     * 创建数据源dataSourceSlave0
//     *
//     * @return 返回数据源
//     */
//    @Bean(name = "dataSourceSlave0")
//    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.slave0")
//    public DataSource dataSourceSlave0() {
//        return new BasicDataSource();
//    }
//
//
//    /**
//     * 创建数据源dataSourceSlave1
//     *
//     * @return 返回数据源
//     */
//    @Bean(name = "dataSourceSlave1")
//    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.slave1")
//    public DataSource dataSourceSlave1() {
//        return new BasicDataSource();
//    }
//
//    @Bean(name = DATA_SOURCE_NAME)
//    public DataSource dataSource() {
//        // 配置分片规则
//        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration =
//                new MasterSlaveRuleConfiguration("ds_ms", "dataSourceMaster", Arrays.asList("dataSourceSlave0", "dataSourceSlave1"),
//                        new LoadBalanceStrategyConfiguration("round_robin"));
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("dataSourceMaster", dataSourceMaster());
//        dataSourceMap.put("dataSourceSlave0", dataSourceSlave0());
//        dataSourceMap.put("dataSourceSlave1", dataSourceSlave1());
//        Properties props = new Properties();
//        props.setProperty("sql.show", "true");
//        //创建分片数据源
//        try {
//            return MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfiguration, props);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 配置事务
//     *
//     * @return 事务
//     */
//    @Bean(name = TRANSACTION_NAME)
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//    /**
//     * 创建SqlSessionFactory对象
//     *
//     * @param dataSource 数据源
//     * @return SqlSessionFactory对象
//     * @throws Exception 异常
//     */
//    @Bean(name = SESSION_FACTORY_NAME)
//    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
//        return super.getSessionFactory(dataSource);
//    }
//
//}
