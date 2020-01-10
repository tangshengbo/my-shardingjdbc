package com.tangshengbo.shardingjdbc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2020/1/9
 */
@Configuration
@MapperScan(basePackages = {"com.tangshengbo.shardingjdbc.dao"},
        sqlSessionFactoryRef = ShardingDataSourceConfig.SESSION_FACTORY_NAME)
public class ShardingDataSourceConfig extends BaseSourceConfig {

    public static final String SESSION_FACTORY_NAME = "sqlSessionFactory";

    public static final String TRANSACTION_NAME = "transactionManager";

    private static final String DATA_SOURCE_NAME = "dataSource";

    /**
     * 创建数据源ds0
     *
     * @return 返回数据源
     */
    @Bean(name = "dataSource0")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.ds0")
    public DataSource dataSource0() {
        return new BasicDataSource();
    }

    /**
     * 创建数据源ds1
     *
     * @return 返回数据源
     */
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.ds1")
    public DataSource dataSource1() {
        return new BasicDataSource();
    }

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource dataSource() {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id",
                        "ds_${user_id % 2}"));

        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds_0", dataSource0());
        dataSourceMap.put("ds_1", dataSource1());

        // 配置分库 + 分表策略 t_order
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds_${0..1}.t_order_${0..1}");
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));
        orderTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_id"));

        // 配置分库 + 分表策略 t_order_item
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration("t_order_item", "ds_${0..1}.t_order_item_${0..1}");

//        orderItemTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}"));
        orderItemTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_item_${order_id % 2}"));
        orderItemTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_item_id"));
        shardingRuleConfig.getBindingTableGroups().add("t_order");
        shardingRuleConfig.getBindingTableGroups().add("t_order_item");

        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
        shardingRuleConfig.getTableRuleConfigs().add(orderItemTableRuleConfig);

        Properties props = new Properties();
        props.setProperty("sql.show", "true");

        //创建分片数据源
        try {
            return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, props);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 配置事务
     *
     * @return 事务
     */
    @Bean(name = TRANSACTION_NAME)
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    /**
     * 创建SqlSessionFactory对象
     *
     * @param dataSource 数据源
     * @return SqlSessionFactory对象
     * @throws Exception 异常
     */
    @Bean(name = SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        return super.getSessionFactory(dataSource);
    }

}
