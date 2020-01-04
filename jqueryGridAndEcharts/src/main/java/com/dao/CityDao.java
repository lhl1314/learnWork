package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/19 9:32
 * @description TODO
 */
public interface CityDao {
    /**
     * 获取所有的city
     * @return
     */
    List<Map<String,Object>> getAll();

    /**
     * 获取导出的数据，这里可以根据具体需求查询所要导出的数据
     *
     * @return
     */
    List<Map<String,Object>> getExportSqlList();

    /**
     * 动态的获取数据
     * @param map
     * @return
     */
    List<Map<String,Object>>getDynamicCitys(Map<String,Object> map);


    List<Map<String,Object>>getErJi();
}
