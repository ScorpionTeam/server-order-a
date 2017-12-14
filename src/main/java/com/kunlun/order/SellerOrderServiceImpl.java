package com.kunlun.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kunlun.entity.Order;
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

    @HystrixCommand(fallbackMethod = "findByConditionFallBack")
    @Override
    public ModelMap findByCondition(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Order> page = sellerOrderMapper.findByCondition();
        ModelMap map = new ModelMap();
        map.addAttribute(new PageResult<>(page));
        return map;
    }


    private ModelMap findByConditionFallBack(Integer pageNo,Integer pageSize) {
        ModelMap map = new ModelMap();
        map.addAttribute("ERROR","服务宕机，请稍后重试");
        return map;
    }
}
