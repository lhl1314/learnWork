package com;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/17 9:54
 * @description TODO
 */
@RequestMapping(value = "/index")
@Controller
public class IndexController {
    /**
     * 到达
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isAuthenticated();
        if (b){
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            System.out.println("------------------------当前用户名是："+username);
            User user=new User();
            user.setUsername(username);
            user.setPassword("a");
            session.setAttribute("user",user);
            return "/home";
        }
        else {
            return "redirect:/index/index";//因为shiro所有的路径都拦截了，所以，
            //认证失败后悔重定向到登录页面
        }
//        else {
//           return "redirect:http://127.0.0.1:8080/cas/login?service=http://localhost:8080/shiro_cas/casLogin";
//        }
    }
}
