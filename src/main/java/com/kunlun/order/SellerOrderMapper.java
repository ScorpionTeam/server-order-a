package com.kunlun.order;

import com.github.pagehelper.Page;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import org.apache.ibatis.annotations.Mapper;

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
}
