package com.controller;

import com.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/17 9:26
 * @description TODO
 */
@Controller
public class JRebelController {
    @Autowired
    CityDao cityDao;

    @RequestMapping(value = "/helloJrebel")
    @ResponseBody
    public String helloJrebel() {

     return "helloJrebel";
    }
    @RequestMapping(value = "/helloJrebel2")
    @ResponseBody
    public List<Map<String, Object>> helloJrebel2() {
        List<Map<String, Object>> jrebel = cityDao.getJrebel();
        return jrebel;
    }
}
