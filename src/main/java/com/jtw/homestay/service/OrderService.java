/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.service;

import com.jtw.homestay.domain.Housres;
import com.jtw.homestay.domain.Order;
import com.jtw.homestay.domain.User;

import java.util.List;

/**
 * @author  jml
 * @date    2020-3-10 11:50:15
 * @version V1.0
 * @desc    
 */
public interface OrderService {
    
    /**
     * 添加订单
     * @param order
     * @return 
     */
    Boolean addOrder(Order order);

    /**
     * 查询单个订单
     * @param oid
     * @return 
     */
    Order findOne(Integer oid);
    
    /**
     * 查询单个会员的全部订单
     * @param uid
     * @return 
     */
    List<Order> findAllByUid(Integer uid);

    /**
     * 订单支付
     * @param order
     * @return 
     */
    Boolean payOrder(Order order);
    
    /**
     * 查询所有订单
     * @return 
     */
    List<Order> findAll();
    
    /**
     * 删除订单
     * @param parseInt
     * @return 
     */
    Boolean deleteOrder(Integer parseInt);

    /**
     * 根据订单编号查询订单
     * @param onum
     * @return 
     */
    Order findOrderByOnum(String onum);

    /**
     * 根据房间号查询订单
     * @param hid
     * @return 
     */
    List<Order> findOrderByHid(Integer hid);

    /**
     * 查询指定下单时间段内的订单
     * @param lowdate
     * @param update
     * @return 
     */
    List<Order> findOrderByOdate(String lowdate, String update);

    /**
     * 办理入住
     * @param order
     * @return 
     */
    Boolean checkin(Order order);

    /**
     * 查询未处理订单
     * @return 
     */
    List<Order> findOrderByNotHandle();

    /**
     * 新增入住
     * @param user
     * @param housres
     * @param order
     * @return 
     */
    Boolean addCheckin(User user, Housres housres, Order order);

    /**
     * 查询已处理订单
     * @return
     */
    List<Order> findAllCheckinAndHandleOver();

    /**
     * 办理退房
     * @param order
     * @return 
     */
    Boolean checkOut(Order order);


    /**
     * 插入评论
     * @param order
     */
    Boolean addComment(Order order);

    Boolean deleteOrderByHid(Integer hid);
}
