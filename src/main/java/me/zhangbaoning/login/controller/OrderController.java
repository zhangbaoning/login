package me.zhangbaoning.login.controller;

import me.zhangbaoning.login.entity.Orders;
import me.zhangbaoning.login.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: zhangbaoning
 * @date: 2018/11/13
 * @since: JDK 1.8
 * @description: TODO
 */
public class OrderController {
    @Autowired
    private OrderService service;

    public ModelAndView getOrderList() {
        ModelAndView view = new ModelAndView();
        List<Orders> orderList = service.getAll();
        view.addObject("orderList", orderList);
        return view;

    }
}
