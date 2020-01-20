import com.dao.CityDao;
import com.dao.PeopleDao;
import com.pojo.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/20 9:32
 * @description TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class T {
    @Autowired
    CityDao cityDao;
    @Autowired
    PeopleDao peopleDao;

    @Test
    public void getAll() {
        List<Map<String, Object>> all = cityDao.getAll();
        all.stream().forEach(t -> {
            System.out.println(t);
        });
    }

    @Test
    public void get() {
        Map<String, Object> map = new HashMap<>();
        map.put("sidx", "id");
        map.put("sord", "desc");
        List<Map<String, Object>> dynamicCitys = cityDao.getDynamicCitys(map);

        dynamicCitys.forEach(t -> System.out.println(t));
    }


    @Test
    public void afdf() {
        List<People> all = peopleDao.getAll();
        all.forEach(t -> System.out.println(t));
    }

    @Test
    public void insertOne() {
        People p = new People();
        p.setpName("小二");
//        p.setAge(2);
        p.setStatus(10);
        int i = peopleDao.insertOne(p);
        if (i > 0) {
            System.out.println("插入成功！");
        }
    }

    /**
     * 批量插入
     */
    @Test
    public void insertList() {
        List<People> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            People p = new People();
            p.setpName(Integer.toString(i));
            p.setAge(2);
            p.setStatus(10);
            list.add(p);
        }
        int i = peopleDao.insertList(list);
        if (i > 0) {
            System.out.println("插入成功！");
        }
    }


    /**
     * 修改一个
     */
    @Test
    public void updateOne() {
        People p = new People();
        p.setpName("小八");
//        p.setAge(2);
//        p.setStatus(10);
        p.setId(8);
        int i = peopleDao.updateOne(p);
        if (i > 0) {
            System.out.println("修改成功！");
        }
    }


    /**
     * 动态的修改数据
     */
    @Test
    public void updateDynamicOne(){
        People p = new People();
        p.setpName("小八32sdc88");
//        p.setAge(3232);
//        p.setStatus(10232);
        p.setId(8);
        int i = peopleDao.dynamicUpdateOne(p);
        if (i > 0) {
            System.out.println("修改成功！");
        }
    }


    /**
     * 批量修改
     */
    @Test
    public void updateList() {
        List<People> all = peopleDao.getAll();
        for (int i = 0; i < all.size(); i++) {
            People p = all.get(i);
            p.setpName(Integer.toString(i + 20));
//            p.setAge(i + 20);
//            p.setStatus(10);
        }
        int i = peopleDao.updateList(all);
        if (i > 0) {
            System.out.println("修改成功！");
        }
    }




    @Test
    public void fasd() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        System.out.println(inet.getHostAddress());
    }

    @Test
    public void cssd() throws UnknownHostException {

        LinkedList<People>peopleLinkedList=new LinkedList<>();
        for (int i=1;i<10;i++){
            People p2=new People();
            p2.setId(i);
            p2.setStatus(10);
            p2.setAge(10);
            p2.setpName("1");
            peopleLinkedList.add(p2);
        }
        AtomicInteger j= new AtomicInteger();
        j.set(0);
        peopleLinkedList.forEach(t-> {
            System.out.println(j.incrementAndGet()+"--"+t);
        });
        System.out.println("-----------------");
        peopleLinkedList.remove(4);
        AtomicInteger k= new AtomicInteger();
        k.set(0);
        peopleLinkedList.forEach(t-> {
            System.out.println(k.incrementAndGet()+"--"+t);
        });
        System.out.println("-----------------");
    }

}
