package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.kunlun.entity.Estimate;
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
    Page<OrderExt> findByWxCode(@Param("userId") String userId, @Param("orderStatus") String orderStatus);

    /**
     * 退款
     *
     * @param id
     * @return
     */
    int refund(Long id);

    /**
     * 查询我的订单详情
     *
     * @param orderId
     * @return
     */
    Order findByOrderId(@Param("orderId") Long orderId);

    /**
     * 签收后评价
     *
     * @param estimate
     * @return
     */
    int estimate(Estimate estimate);

    /**
     * 更新订单状态
     *
     * @param id
     * @return
     */
    int updateOrderStatus(@Param("id") Long id,
                          @Param("orderStatus") String orderStatus);
}
