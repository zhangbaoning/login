package me.zhangbaoning.login.service;

import me.zhangbaoning.login.dao.UserDao;
import me.zhangbaoning.login.entity.User;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @author: zhangbaoning
 * @date: 2018/11/8
 * @since: JDK 1.8
 * @description: 用户服务层
 */
@Service
public class UserService {
    @Resource
    private UserDao dao;

    /**
     * 添加openId之后更新修改时间
     * @param user
     */
    public void updateByIdCard(User user) {

        user.setGmtModified(new Timestamp(System.currentTimeMillis()));
        Example example = new Example(User.class);
        example.and(example.createCriteria().andEqualTo("idCard", user.getIdCard()));
        dao.updateByExampleSelective(user, example);
    }

    /**
     * 使用身份证号查询
     * 身份证号后六位唯一
     * @param idCard
     */
    public User getByIdCard(String idCard) {


        User user = null;
        try {
            user = dao.selectUserByIdcardLike(idCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 通过openid进行查询
     * @param openid
     * @return
     */
    public User getByOpenid(String openid) {
        User getUser = null;
        User user = new User();
        user.setOpenid(openid);
        try {
            getUser = dao.selectOne(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUser;
    }
}
