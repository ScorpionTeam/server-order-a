package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
import org.springframework.ui.ModelMap;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/14.
 */
public interface SellerOrderService {

    /**
     * 订单列表
     *
     * @param object
     * @return
     */
    PageResult findByCondition(JSONObject object);

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    BaseResult findById(Long id);

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    BaseResult modify(Order order);

    /**
     * 退款
     *
     * @param orderId
     * @param flag
     * @param remark
     * @param refundFee
     * @return
     */
    BaseResult refund(Long orderId, String flag, String remark, Integer refundFee);
}
