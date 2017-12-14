package com.kunlun.order;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.kunlun.entity.Order;
import com.kunlun.entity.OrderExt;
import com.kunlun.enums.CommonEnum;
import com.kunlun.result.BaseResult;
import com.kunlun.result.PageResult;
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
     * @param pageNo
     * @param pageSize
     * @param wxCode
     * @param orderStatus
     * @return
     */
    @Override
    public PageResult findByUserId(int pageNo, int pageSize, String wxCode, String orderStatus) {
        //String userId = WxUtil.getOpenId(wxCode);
        PageHelper.startPage(pageNo, pageSize);
        if (CommonEnum.ALL.getCode().equals(orderStatus)) {
            orderStatus = null;
        }
        Page<OrderExt> page = wxOrderMapper.findByUserId(wxCode, orderStatus);
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
        int result = wxOrderMapper.refund(order);
        if (result < 0) {
            return BaseResult.error("ERROR", "退款申请失败");
        }
        return BaseResult.success("退款申请成功");
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public BaseResult findByOrderId(Long orderId) {
        if (orderId == null) {
            return BaseResult.parameterError();
        }
        Order order = wxOrderMapper.findByOrderId(orderId);
        if (order == null) {
            return BaseResult.error("ERROR", "没找到此订单");
        }
        return BaseResult.success("成功找到订单");
    }
}
