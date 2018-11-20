package me.zhangbaoning.login.dao;

import me.zhangbaoning.login.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: zhangbaoning
 * @date: 2018/11/8
 * @since: JDK 1.8
 * @description: TODO
 */
public interface UserDao extends Mapper<User> {
    /**
     * 通过身份证号后六位进行模糊查询
     * @param idCard
     * @return
     */
    User selectUserByIdcardLike(String idCard);
}
