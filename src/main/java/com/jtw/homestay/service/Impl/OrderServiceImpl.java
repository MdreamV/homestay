/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.service.Impl;


import com.jtw.homestay.dao.HousresDao;
import com.jtw.homestay.dao.OrderDao;
import com.jtw.homestay.dao.UserDao;
import com.jtw.homestay.domain.Housres;
import com.jtw.homestay.domain.Order;
import com.jtw.homestay.domain.User;
import com.jtw.homestay.service.OrderService;
import com.jtw.homestay.utils.DaoUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Iterator;
import java.util.List;

/**
 * @author  jtw
 * @date    2020-4-1 11:51:07
 * @version V1.0
 * @desc    
 */
public class OrderServiceImpl implements OrderService {

    /**
     * 添加订单
     * @param order
     * @return 
     */
    @Override
    public Boolean addOrder(Order order) {
        //创建orderDao代理对象
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        //插入订单，持久化order
        Boolean flag = orderDao.addOrder(order);
        //if(){}
        session.close();
        return flag;
    }

    /**
     * 查询单个订单
     * @param oid
     * @return 
     */
    @Override
    public Order findOne(Integer oid) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        Order order = orderDao.findByOid(oid);
        session.close();
        return order;
    }

    /**
     * 查询单个会员的全部订单
     * @param uid
     * @return 
     */
    @Override
    public List<Order> findAllByUid(Integer uid) {

        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        List<Order> orders = orderDao.findAllByUid(uid);
        session.close();
        return orders;
    }

    /**
     * 支付订单
     * @param order
     * @return 
     */
    @Override
    public Boolean payOrder(Order order) {
        //创建orderDao代理对象
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        UserDao userDao = session.getMapper(UserDao.class);
        userDao.updateUser(order.getUser());
        //更新订单支付状态
        Boolean flag = orderDao.updateOrder(order);
        //if(){}
        session.close();
        return flag;
    }

    /**
     * 查询所有订单
     * @return 
     */
    @Override
    public List<Order> findAll() {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        List<Order> orders = orderDao.findAll();
        session.close();
        return orders;
    }

    /**
     * 删除订单
     * @param oid
     * @return 
     */
    @Override
    public Boolean deleteOrder(Integer oid) {
        //创建orderDao代理对象
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        //插入订单，持久化order
        Boolean flag = orderDao.deleteOrder(oid);
        //if(){}
        session.close();
        return flag;
    }

    /**
     * 根据订单号查询订单
     * @return 
     */
    @Override
    public Order findOrderByOnum(String onum) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);

        Order order = orderDao.findByONum(onum);
        UserDao userDao = session.getMapper(UserDao.class);
        session.close();
        return order;
    }

    /**
     * 根据房间号查询订单
     * @param hid
     * @return 
     */
    @Override
    public List<Order> findOrderByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        List<Order> orderList = orderDao.findOrderByHid(hid);
        session.close();
        return orderList;
    }

    /**
     * 查询指定下单时间段内的订单
     * @param lowdate
     * @param update
     * @return 
     */
    @Override
    public List<Order> findOrderByOdate(String lowdate, String update) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        List<Order> orders = orderDao.findOrderByOdate(lowdate,update);
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> users = userDao.findAll();
        Iterator<Order> ito = orders.iterator();

        session.close();
        return orders;
    }

    /**
     * 办理入住
     * @param order
     * @return 
     */
    @Override
    public Boolean checkin(Order order) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        Boolean aBoolean = orderDao.updateOrder(order);
        session.close();
        return aBoolean;
    }

    /**
     * 查询所有未处理订单
     * @return 
     */
    @Override
    public List<Order> findOrderByNotHandle() {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        List<Order> orders = orderDao.findOrderByNotHandle();
        UserDao userDao = session.getMapper(UserDao.class);

        session.close();
        return orders;
    }

    /**
     * 新增入住
     * @param user
     * @param housres
     * @param order
     * @return 
     */
    @Override
    public Boolean addCheckin(User user, Housres housres, Order order) {

        return false;
    }

    /**
     * 查询已处理订单
     * @return
     */
    @Override
    public List<Order> findAllCheckinAndHandleOver() {

        return null;
    }

    /**
     * 办理退房
     * @param order
     * @return 
     */
    @Override
    public Boolean checkOut(Order order) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        Boolean aBoolean = orderDao.updateOrder(order);
        Order order1 = orderDao.findByOid(order.getOid());
        HousresDao housresDao = session.getMapper(HousresDao.class);
        Housres housres = new Housres();
        housres.setHid(order1.getHid());
        housres.setIssell(false);
        housresDao.updateHousres(housres);
        session.close();
        return aBoolean;
    }

    @Override
    public Boolean addComment(Order order) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        Boolean aBoolean = orderDao.updateOrder(order);
        session.close();
        return aBoolean;
    }

    @Override
    public Boolean deleteOrderByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        OrderDao orderDao = session.getMapper(OrderDao.class);
        Boolean aBoolean = orderDao.deleteOrderByHid(hid);
        session.close();
        return aBoolean;
    }


}
