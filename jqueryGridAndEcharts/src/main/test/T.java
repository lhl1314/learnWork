import com.dao.CityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void getAll(){
        List<Map<String, Object>> all = cityDao.getAll();
        all.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @Test
    public void get(){
        Map<String,Object>map=new HashMap<>();
        map.put("sidx","id");
        map.put("sord","desc");
        List<Map<String, Object>> dynamicCitys = cityDao.getDynamicCitys(map);

        dynamicCitys.forEach(t-> System.out.println(t));
    }
}
