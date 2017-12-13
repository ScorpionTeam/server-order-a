package com.kunlun;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public String hello() {
        LOGGER.info("接收到请求");
        return "Order Server1  return ......";
    }

    public String fallback() {
        return "Order Server1 is Down......";
    }
}
