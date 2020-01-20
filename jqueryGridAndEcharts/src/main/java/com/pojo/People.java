package com.pojo;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/16 10:47
 * @description TODO
 */
public class People {

    private long id;
    private String pName;
    private Integer age;
    private Integer status;


    public People() {
    }

    public People(long id, String pName, Integer age, Integer status) {
        this.id = id;
        this.pName = pName;
        this.age = age;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", pName='" + pName + '\'' +
                ", age=" + age +
                ", status=" + status +
                '}';
    }
}
