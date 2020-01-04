package com;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/17 9:44
 * @description TODO
 */
@RequestMapping(value = "/test")
@Controller
public class TestController {

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(){
        User user=new User();
        user.setUsername("小一");
        user.setPassword("1234");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
            subject.login(token);//shiro进行验证用户登陆合法性, 如果合法，则更新session表userid
            Session session = subject.getSession();//
            User o = (User) subject.getPrincipal();
            System.out.println("--------------------------------------");
            System.out.println("登录成功"+user);
            return "login success";
    }

    /**
     * 登录失败
     * @return
     */
    @RequestMapping(value = "/toLogin")
    @ResponseBody
    public String toLogin(){
        return  "please login first";
    }


    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:http://localhost:8080/cas/logout?service=http://localhost:8080/shiro/casLogin";
    }


    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "hello............shiro_cas";
    }
}
