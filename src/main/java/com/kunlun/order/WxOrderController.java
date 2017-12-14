package com.kunlun.order;

import com.kunlun.entity.Order;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by fk
 * @created on 2017/12/14.
 */
@RestController
@RequestMapping("/wx/order")
public class WxOrderController {

    @Autowired
    private WxOrderService wxOrderService;

    /**
     * 查询我的订单列表
     *
     * @param pageNo      当前页
     * @param pageSize    当前页数
     * @param wxCode      微信Code
     * @param orderStatus 订单状态
     * @return
     */
    @GetMapping("/findByUserId/{pageNo}/{pageSize}/{wxCode}/{orderStatus}")
    public PageResult findByUserId(@PathVariable int pageNo,
                                   @PathVariable int pageSize,
                                   @PathVariable String wxCode,
                                   @PathVariable String orderStatus) {
        return wxOrderService.findByUserId(pageNo, pageSize, wxCode, orderStatus);
    }

    /**
     * 退款
     *
     * @param order
     * @return
     */
    @PostMapping("/refund")
    public BaseResult refund(@RequestBody Order order) {
        return wxOrderService.refund(order);
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/findByOrderId/{orderId}")
    public BaseResult findByOrderId(@PathVariable Long orderId) {
        return wxOrderService.findByOrderId(orderId);
    }
}
