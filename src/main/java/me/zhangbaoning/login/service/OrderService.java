package me.zhangbaoning.login.service;

import me.zhangbaoning.login.dao.OrderDao;
import me.zhangbaoning.login.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhangbaoning
 * @date: 2018/11/13
 * @since: JDK 1.8
 * @description: 预约服务层
 */
@Service
public class OrderService {
    @Resource
    private OrderDao dao;

    /**
     * 获取所有的预约
     * @return
     */
    public List<Orders> getAll(){
        List<Orders> orderList = dao.selectAll();
        return orderList;
    }
}
