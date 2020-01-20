package com.dao;


import com.pojo.People;

import java.util.List;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/16 10:45
 * @description TODO
 */
public interface PeopleDao {
    /**
     * 所有数据
     * @return
     */
    List<People>getAll();

    /**
     * 添加一个
     * @return
     */
    int insertOne(People p);

    /**
     * 动态的添加一个数据
     * @param p
     * @return
     */
    int dynamicInsertOne(People p);

    /**
     * 批量插入
     */
    int insertList(List<People>list);


    /**
     * 修改一个
     * @param people
     * @return
     */
    int updateOne(People people);

    /**
     * 动态的修改一个
     * @param people
     * @return
     */
    int dynamicUpdateOne(People people);




    /**
     * 批量更新List
     * @param people
     * @return
     */
    int updateList(List<People>people);



    List<People>GEGT();

}


