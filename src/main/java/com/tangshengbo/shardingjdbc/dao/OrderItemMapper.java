package com.tangshengbo.shardingjdbc.dao;

import com.tangshengbo.shardingjdbc.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    /**
     * @mbggenerated 2020-01-07
     */
    int deleteByPrimaryKey(Long orderItemId);

    /**
     * @mbggenerated 2020-01-07
     */
    int insert(OrderItem record);

    /**
     * @mbggenerated 2020-01-07
     */
    int insertSelective(OrderItem record);

    /**
     * @mbggenerated 2020-01-07
     */
    OrderItem selectByPrimaryKey(Long orderItemId);

    /**
     * @mbggenerated 2020-01-07
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     * @mbggenerated 2020-01-07
     */
    int updateByPrimaryKey(OrderItem record);
}