package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.kunlun.entity.Order;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;

/**
 * @author by fk
 * @created on 2017/12/14.
 */
public interface WxOrderService {

    /**
     * 查询我的订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param wxCode
     * @param orderStatus
     * @return
     */
    PageResult findByUserId(int pageNo, int pageSize, String wxCode, String orderStatus);

    /**
     * 退款
     *
     * @param object
     * @return
     */
    BaseResult refund(JSONObject object);

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    BaseResult findByOrderId(Long orderId);

    /**
     * 签收后评价
     *
     * @param object
     * @return
     */
    BaseResult estimate(JSONObject object);

    /**
     * 取消订单
     *
     * @param id
     * @param ipAddress
     * @return
     */
    BaseResult cancelOrder(Long id, String ipAddress);
}
