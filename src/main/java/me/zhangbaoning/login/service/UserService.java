package me.zhangbaoning.login.service;

import me.zhangbaoning.login.dao.UserDao;
import me.zhangbaoning.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangbaoning
 * @date: 2018/11/8
 * @since: JDK 1.8
 * @description: TODO
 */
@Service
public class UserService {
    @Resource
    private UserDao dao;
    public void login(){

    }
    public void get(){
        User user = new User();
        user.setIdCard("zhang");
        user.setFullName("zhang");
        dao.insert(user);
    }

    /**
     * 身份证号和姓名都不是唯一的，
     * 可能一个身份证号后六位对应多个姓名，
     * 相反也同样
     * @param idCard
     */
    public User getByIdCard( String idCard) {
         User user = new User();
        user.setIdCard(idCard);
        List<User> userList = dao.select(user);
        if (userList !=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
}
