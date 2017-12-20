package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
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
}
