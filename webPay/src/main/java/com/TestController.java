package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/26 10:24
 * @description TODO
 */
@Controller
public class TestController {
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "hello webPay....";
    }
}
