package com.kunlun.order;

import com.kunlun.result.PageResult;
import org.springframework.ui.ModelMap;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/14.
 */
public interface SellerOrderService {

    /**
     * 条件查询商家订单信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    ModelMap findByCondition(Integer pageNo, Integer pageSize);


}
