package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.kunlun.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/14.
 */
@RestController
@RequestMapping("/seller/order")
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

}
