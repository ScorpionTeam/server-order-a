package com.kunlun.order;

import com.github.pagehelper.Page;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/14.
 */
@Mapper
public interface SellerOrderMapper {

    /**
     * 订单列表
     *
     * @param orderExt
     * @return
     */
    Page<OrderExt> findByCondition(OrderExt orderExt);

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    Order findById(@Param("id") Long id);

    /**
     * 修改订单
     *
     * @param order
     */
    int modify(Order order);

    void updateOrderRefundById(Long orderId, String code, String remark, int i);
}
