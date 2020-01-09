package com.tangshengbo.shardingjdbc;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {SpringBootConfiguration.class, DataSourceAutoConfiguration.class})
public class MyShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShardingJdbcApplication.class, args);
    }

}
