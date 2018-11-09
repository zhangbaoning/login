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
//    @Resource
    private UserDao dao;
    public void login(){

    }
    public void get(){
        User user = new User();
        user.setIdCard("zhang");
        user.setUsername("zhang");
        dao.insert(user);
    }

    /**
     * 身份证号和姓名都不是唯一的，
     * 可能一个身份证号后六位对应多个姓名，
     * 相反也同样
     * @param username
     */
    public List<String> getByUsername(final String username){
        final User user = new User();
        user.setUsername(username);
        List<String> idCardList = new ArrayList();
        dao.select(user).forEach(
                getUser ->{
                    String idCard = user.getIdCard();
                    int length  = idCard.length();
                    if (length >=6){
                        idCardList.add(idCard.substring(length-6,length));
                    }
                }
        );
        return idCardList;

    }
}
