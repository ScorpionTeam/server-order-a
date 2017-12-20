package com.kunlun.order;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
}
