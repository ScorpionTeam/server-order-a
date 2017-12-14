package com.kunlun;

import com.kunlun.entity.Seller;
import com.kunlun.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/13.
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;


    @GetMapping("/hello")
    public BaseResult hello() {
        return indexService.hello();
    }

    @GetMapping("/hello1/param/{test}")
    public BaseResult hello1(@PathVariable String test) {
        System.out.println("传递的参数为：" + test);
        return BaseResult.success("参数传递成功");
    }

    @PostMapping("/hello/post")
    public BaseResult post(@RequestBody Seller seller) {
        System.out.println("POST请求进来");
        System.out.println("打印参数：" + seller.toString());
        return null;
    }

}
