package com;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/18 14:47
 * @description TODO
 */
@Controller
public class IndexController {
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("a", "a");
        try {
            subject.login(token);
            subject.getSession().setAttribute("username","a");
            return "登录成功";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "登录失败，用户名或者密码错误";
        }
    }

    /**
     * 登录成功
     * @return
     */
    @RequestMapping(value = "/home")
    public String index(){
        return "home";
    }
    /**
     * 注销
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        return "index";
    }
}
