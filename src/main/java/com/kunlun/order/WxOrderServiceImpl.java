package com.kunlun.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kunlun.entity.OrderExt;
import com.kunlun.enums.CommonEnum;
import com.kunlun.result.PageResult;
import com.kunlun.wxentity.wxUtils.WxUtil;
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
        if(CommonEnum.ALL.getCode().equals(orderStatus)) {
            orderStatus = null;
        }
        Page<OrderExt> page = wxOrderMapper.findByUserId(wxCode, orderStatus);
        return new PageResult(page);
    }
}
