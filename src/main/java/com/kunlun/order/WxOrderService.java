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
     * @param object
     * @return
     */
    PageResult findByWxCode(JSONObject object);

    /**
     * 退款
     *
     * @param object
     * @return
     */
    BaseResult refund(JSONObject object);

    /**
     * 查询我的订单详情
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

    /**
     * 确认收货
     *
     * @param id
     * @param ipAddress
     * @return
     */
    BaseResult confirmReceipt(Long id, String ipAddress);
}
