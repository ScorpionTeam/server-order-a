package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.kunlun.entity.Estimate;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import com.kunlun.enums.CommonEnum;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
import com.kunlun.wxentity.wxUtils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by fk
 * @created on 2017/12/14.
 */
@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    private WxOrderMapper wxOrderMapper;

    /**
     * 查询我的订单列表
     *
     * @param object
     * @return
     */
    @Override
    public PageResult findByWxCode(JSONObject object) {
        OrderExt orderExt = object.toJavaObject(OrderExt.class);
        String userId = WxUtil.getOpenId(orderExt.getWxCode());
        PageHelper.startPage(orderExt.getPageNo(), orderExt.getPageSize());
        String orderStatus = orderExt.getOrderStatus();
        //所有订单
        if (CommonEnum.ALL.getCode().equals(orderStatus)) {
            orderStatus = null;
        }
        Page<OrderExt> page = wxOrderMapper.findByWxCode(userId, orderStatus);
        return new PageResult(page);
    }

    /**
     * 退款
     *
     * @param object
     * @return
     */
    @Override
    public BaseResult refund(JSONObject object) {
        Order order = JSONObject.toJavaObject(object, Order.class);
        if (StringUtil.isEmpty(order.toString())) {
            return BaseResult.notFound();
        }
        int result = wxOrderMapper.refund(order.getId());
        if (result < 0) {
            return BaseResult.error("ERROR", "退款申请失败");
        }
        return BaseResult.success("退款申请成功");
    }

    /**
     * 查询我的订单详情
     *
     * @param object
     * @return
     */
    @Override
    public BaseResult findByOrderId(JSONObject object) {
        OrderExt orderExt = JSONObject.toJavaObject(object, OrderExt.class);
        if (orderExt.getId() == null) {
            return BaseResult.parameterError();
        }
        Order order = wxOrderMapper.findByOrderId(orderExt.getId());
        if (order == null) {
            return BaseResult.error("ERROR", "没找到此订单");
        }
        return BaseResult.success("成功找到订单");
    }

    /**
     * 签收后评价
     *
     * @param object
     * @return
     */
    @Override
    public BaseResult estimate(JSONObject object) {
        Estimate estimate = JSONObject.toJavaObject(object, Estimate.class);
        String userId = WxUtil.getOpenId(estimate.getWxCode());
        if (StringUtil.isEmpty(userId)) {
            return BaseResult.notFound();
        }
        if (StringUtil.isEmpty(estimate.getGoodId().toString())) {
            return BaseResult.notFound();
        }
        estimate.setUserId(userId);
        int result = wxOrderMapper.estimate(estimate);
        if (result < 0) {
            return BaseResult.error("ERROR", "评价不成功");
        }
        return BaseResult.success("评价成功");
    }

    /**
     * 取消订单
     *
     * @param object
     * @param ipAddress
     * @return
     */
    @Override
    public BaseResult cancelOrder(JSONObject object, String ipAddress) {
        OrderExt orderExt = JSONObject.toJavaObject(object, OrderExt.class);
        if (StringUtil.isEmpty(orderExt.getId().toString())) {
            return BaseResult.notFound();
        }
        Order order = wxOrderMapper.findByOrderId(orderExt.getId());
        //订单已完成状态
        if (order.getOrderStatus().equals(CommonEnum.ALL_DONE.getCode())) {
            return BaseResult.error("ERROR", "订单已完成,不能取消订单");
        }
        //修改订单的状态
        int result = wxOrderMapper.updateOrderStatus(orderExt.getId(), CommonEnum.CLOSING.getCode());
        if (result < 0) {
            return BaseResult.error("ERROR", "取消订单失败,请重试");
        }
        return BaseResult.success("取消订单成功");
    }

    /**
     * 确认收货
     *
     * @param object
     * @param ipAddress
     * @return
     */
    @Override
    public BaseResult confirmByGood(JSONObject object, String ipAddress) {
        OrderExt orderExt = JSONObject.toJavaObject(object, OrderExt.class);
        if (StringUtil.isEmpty(orderExt.getId().toString())) {
            return BaseResult.parameterError();
        }
        Order order = wxOrderMapper.findByOrderId(orderExt.getId());
        if (StringUtil.isEmpty(order.getGoodId().toString())) {
            return BaseResult.parameterError();
        }
        //判断该订单状态
        if (!order.getOrderStatus().equals(CommonEnum.UN_RECEIVE.getCode())) {
            return BaseResult.error("ERROR", "订单状态异常");
        }
        int result = wxOrderMapper.updateOrderStatus(orderExt.getId(), CommonEnum.DONE.getCode());
        if (result > 0) {
            //TODO 保存订单日志
            /*int save = wxOrderLogMapper.saveConfirmReceiptLog(order.getOrderNo(), ipAddress, order.getId());
            if (save < 0) {
                return BaseResult.error("ERROR", "保存订单日志失败");
            }*/
            return BaseResult.success("确认收货成功");
        }

        return BaseResult.error("ERROR", "确认收货失败");
    }

}
