package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/14.
 */
@RestController
@RequestMapping("seller/order")
public class SellerOrderController {

    @Autowired
    private SellerOrderService sellerOrderService;

    /**
     * 订单列表
     *
     * @param object 订单
     * @return
     */
    @PostMapping("/findByCondition")
    public PageResult findByCondition(@RequestBody JSONObject object) {
        return sellerOrderService.findByCondition(object);
    }

    /**
     * 根据id查询订单详情
     *
     * @param id 订单id
     * @return
     */
    @GetMapping("/findById/{id}")
    public BaseResult findById(@PathVariable("id") Long id) {
        return sellerOrderService.findById(id);
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return
     */
    @PostMapping("/modify")
    public BaseResult modify(@RequestBody Order order) {
        return sellerOrderService.modify(order);
    }

    /**
     * 退款
     *
     * @param orderId   订单id
     * @param flag      AGREE 同意  REFUSE  拒绝
     * @param remark    退款备注
     * @param refundFee 退款金额
     * @return
     */
    public BaseResult refund(Long orderId, String flag, String remark, Integer refundFee) {
        return sellerOrderService.refund(orderId, flag, remark, refundFee);
    }



}
