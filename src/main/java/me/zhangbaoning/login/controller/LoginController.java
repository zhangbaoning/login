package me.zhangbaoning.login.controller;

import me.zhangbaoning.login.entity.User;
import me.zhangbaoning.login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: zhangbaoning
 * @date: 2018/11/8
 * @since: JDK 1.8
 * @description: TODO
 */
@Controller
public class LoginController {
    @Autowired
    private UserService service;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getIdCard());
        subject.login(token);
        System.out.println(user.toString());
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
