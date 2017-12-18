package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.kunlun.entity.Order;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
import com.kunlun.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * @param object
     * @return
     */
    @PostMapping("/findByWxCode")
    public PageResult findByWxCode(@RequestBody JSONObject object) {
        return wxOrderService.findByWxCode(object);
    }

    /**
     * 退款
     *
     * @param object 订单
     * @return
     */
    @PostMapping("/refund")
    public BaseResult refund(@RequestBody JSONObject object) {
        return wxOrderService.refund(object);
    }

    /**
     * 查询我的订单详情
     *
     * @param object 订单id
     * @return
     */
    @PostMapping("/findByOrderId")
    public BaseResult findByOrderId(@RequestBody JSONObject object) {
        return wxOrderService.findByOrderId(object);
    }

    /**
     * 签收后评价
     *
     * @param object 订单
     * @return
     */
    @PostMapping("/estimate")
    public BaseResult estimate(@RequestBody JSONObject object) {
        return wxOrderService.estimate(object);
    }

    /**
     * 取消订单
     *
     * @param id      订单id
     * @param request 请求
     * @return
     */
    @GetMapping("/cancelOrder/{id}")
    public BaseResult cancelOrder(@PathVariable Long id, HttpServletRequest request) {
        String ipAddress = IpUtil.getIPAddress(request);
        return wxOrderService.cancelOrder(id, ipAddress);
    }

    /**
     * 确认收货
     *
     * @param id      订单id
     * @param request 请求
     * @return
     */
    @GetMapping("/confirmReceipt/{id}")
    public BaseResult confirmReceipt(@PathVariable Long id, HttpServletRequest request) {
        String ipAddress = IpUtil.getIPAddress(request);
        return wxOrderService.confirmReceipt(id, ipAddress);
    }
}
