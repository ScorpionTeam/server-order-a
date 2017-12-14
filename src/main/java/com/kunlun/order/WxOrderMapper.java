package com.kunlun.order;

import com.github.pagehelper.Page;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author by fk
 * @created on 2017/12/14.
 */
@Mapper
public interface WxOrderMapper {

    /**
     * 查询我的订单列表
     *
     * @param userId
     * @param orderStatus
     * @return
     */
    Page<OrderExt> findByUserId(@Param("userId") String userId, @Param("orderStatus") String orderStatus);

    /**
     * 退款
     *
     * @param order
     * @return
     */
    int refund(Order order);
}
