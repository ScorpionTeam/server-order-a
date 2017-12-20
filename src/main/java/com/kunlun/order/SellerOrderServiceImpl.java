package com.kunlun.order;

import com.alibaba.druid.Constants;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import com.kunlun.enums.CommonEnum;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/14.
 */
@Service
public class SellerOrderServiceImpl implements SellerOrderService {

    @Autowired
    private SellerOrderMapper sellerOrderMapper;

    /**
     * 订单列表
     *
     * @param object
     * @return
     */
    @Override
    public PageResult findByCondition(JSONObject object) {
        OrderExt orderExt = JSONObject.toJavaObject(object, OrderExt.class);
        PageHelper.startPage(orderExt.getPageNo(), orderExt.getPageSize());
        //模糊查询
        if (StringUtils.isEmpty(orderExt.getSearchKey())) {
            orderExt.setSearchKey(null);
        }
        if (!StringUtils.isEmpty(orderExt.getSearchKey())) {
            orderExt.setSearchKey("%" + orderExt.getSearchKey() + "%");
        }
        Page<OrderExt> page = sellerOrderMapper.findByCondition(orderExt);
        return new PageResult(page);
    }

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Order order = sellerOrderMapper.findById(id);
        if (order == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(order);
    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @Override
    public BaseResult modify(Order order) {
        if (order.getId() == null) {
            return BaseResult.parameterError();
        }
        Order newOrder = sellerOrderMapper.findById(order.getId());
        if (CommonEnum.UN_PAY.getCode().equals(newOrder.getOrderStatus())) {
            return BaseResult.error("ERROR", "订单未付款不能修改");
        }
        if (CommonEnum.DONE.getCode().equals(newOrder.getOrderStatus())) {
            return BaseResult.error("ERROR", "订单已完成不能修改");
        }
        int result = sellerOrderMapper.modify(order);
        //TODO 订单日志
        if (result < 0) {
            return BaseResult.error("ERROR", "修改订单失败");
        }
        return BaseResult.success("订单修改成功");
    }

    /**
     * 退款
     *
     * @param orderId
     * @param flag
     * @param remark
     * @param refundFee
     * @return
     */
    @Override
    public BaseResult refund(Long orderId, String flag, String remark, Integer refundFee) {
        if (orderId == null) {
            return BaseResult.notFound();
        }
        Order order = sellerOrderMapper.findById(orderId);
        if (CommonEnum.REFUSE.getCode().equals(flag)) {
            //拒绝退款
            sellerOrderMapper.updateOrderRefundById(orderId, CommonEnum.REFUND_FAIL.getCode(), remark, 0);
            //TODO 日志
            return BaseResult.success("拒绝退款");
        }
        if (CommonEnum.AGREE.getCode().equals(flag)) {
            //允许退款
            sellerOrderMapper.updateOrderRefundById(orderId, CommonEnum.REFUNDING.getCode(), remark, refundFee);
            //TODO 日志
            //更新商品库存

        }
        return null;
    }
}
