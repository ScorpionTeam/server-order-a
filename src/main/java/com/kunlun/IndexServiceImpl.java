package com.kunlun;

import com.kunlun.entity.Seller;
import com.kunlun.result.BaseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/13.
 */
@Service
public class IndexServiceImpl implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServiceImpl.class);

    @HystrixCommand(fallbackMethod = "fallback")
    @Override
    public BaseResult hello() {
        LOGGER.info("接收到请求");
        Seller seller = new Seller();
        seller.setId(1L);
        seller.setReason("服务A");
        Seller seller1 = new Seller();
        seller1.setId(2L);
        seller1.setReason("服务B");
        List<Seller> list = new ArrayList<>();
        list.add(seller);
        list.add(seller1);
        return BaseResult.success(list);
    }


    public BaseResult fallback() {
        return BaseResult.error("error","Server1 Down");
    }
}
