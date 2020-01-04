package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/19 9:31
 * @description TODO
 */
@Controller
public class TestController {
    @RequestMapping(value = "/helloJqueryGrid")
    @ResponseBody
    public String helloJqueryGrid(){
        return "hello jqueryGrid";
    }
}
