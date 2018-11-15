package me.zhangbaoning.login.service;

import me.zhangbaoning.login.dao.UserDao;
import me.zhangbaoning.login.entity.User;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Timestamp;
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

    public void login() {

    }

    public void updateByIdCard(User user) {

        user.setGmtModified(new Timestamp(System.currentTimeMillis()));
        Example example = new Example(User.class);
        example.and(example.createCriteria().andEqualTo("idCard", user.getIdCard()));
        dao.updateByExampleSelective(user, example);
    }

    /**
     * 身份证号和姓名都不是唯一的，
     * 可能一个身份证号后六位对应多个姓名，
     * 相反也同样
     *
     * @param idCard
     */
    public User getByIdCard(String idCard) {
        User user = new User();
        user.setIdCard(idCard);
        List<User> userList = dao.select(user);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    public User getByOpenid(String openid) {
        User user = new User();
        user.setOpenid(openid);
        return dao.selectOne(user);
    }
}
