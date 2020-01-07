package com.tangshengbo.shardingjdbc.dao;

import com.tangshengbo.shardingjdbc.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    /**
     * @mbggenerated 2020-01-07
     */
    int deleteByPrimaryKey(Long orderId);

    /**
     * @mbggenerated 2020-01-07
     */
    int insert(Order record);

    /**
     * @mbggenerated 2020-01-07
     */
    int insertSelective(Order record);

    /**
     * @mbggenerated 2020-01-07
     */
    Order selectByPrimaryKey(Long orderId);

    /**
     * @mbggenerated 2020-01-07
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * @mbggenerated 2020-01-07
     */
    int updateByPrimaryKey(Order record);
}