package controller;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author： lhl
 * @date： 2019/10/11 17:08
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/","/toIndex","/a"})
    public String toIndex(HttpServletRequest request){
        System.out.println("-----------------------------");
        AttributePrincipal userPrincipal = (AttributePrincipal)request.getUserPrincipal();
        System.out.println(userPrincipal);
        String name = userPrincipal.getName();
        return "home";
    }


    /**
     * 退出登录
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpSession httpSession){
        httpSession.invalidate();//清空本地session信息
        return "redirect:http://localhost:8080/cas/logout?service=http://localhost:8080/client01";
    }
}
