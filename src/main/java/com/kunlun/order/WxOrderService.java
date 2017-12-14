package com.kunlun.order;

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
}
