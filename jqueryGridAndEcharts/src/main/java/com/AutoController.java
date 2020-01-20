package com;

import com.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/17 11:22
 * @description TODO  服务启动的时候即刻加载内容
 */
@Component
public class AutoController {
    public AutoController(@Autowired CityDao cityDao) {
        System.out.println("-------------------------------------------------");
        List<Map<String, Object>> limit = cityDao.getLimit();
        limit.forEach(t-> System.out.println(t));
        System.out.println("-------------------------------------------------");
    }
}
