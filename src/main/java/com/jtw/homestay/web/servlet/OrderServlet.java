/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtw.homestay.domain.Housres;
import com.jtw.homestay.domain.Order;
import com.jtw.homestay.domain.ResultInfo;
import com.jtw.homestay.domain.User;
import com.jtw.homestay.service.HousresService;
import com.jtw.homestay.service.Impl.HousresServiceImpl;
import com.jtw.homestay.service.Impl.OrderServiceImpl;
import com.jtw.homestay.service.Impl.UserServiceImpl;
import com.jtw.homestay.service.OrderService;
import com.jtw.homestay.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  jtw
 * @date    2020-4-10 17:03:30
 * @version V1.0
 * @desc    
 */
@WebServlet(name="orderServlet", urlPatterns={"/order/*"})
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    private UserService userService = new UserServiceImpl();
    private HousresService housresService = new HousresServiceImpl();
    /**
     * 会员下单
     * @param request
     * @param response 
     */ 
    public void preOrder(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        String hid = request.getParameter("hid");
        String checkindate = request.getParameter("checkindate");
        String checkoutdate = request.getParameter("checkoutdate");
        String uid = request.getParameter("uid");
        String realpay = request.getParameter("realpay");
        Order order = new Order();
        order.setCheckindate(checkindate);
        order.setCheckoutdate(checkoutdate);
        order.setHid(Integer.parseInt(hid));
        order.setUid(Integer.parseInt(uid));
        order.setOnum(new Date().getTime() + "");
        order.setOdate(new Date().toLocaleString());
        order.setRealpay(Double.parseDouble(realpay));
        order.setTotalprice(Double.parseDouble(realpay));

        Boolean flag = orderService.addOrder(order);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
            info.setData(order);
        }else{
            info.setFlag(false);
        }
        writeValue(info,response);
    }
    
    /**
     * 查询单个订单
     * @param request
     * @param response 
     */
    public void getOrder(HttpServletRequest request,HttpServletResponse response) throws IOException{
        Integer oid = Integer.parseInt(request.getParameter("oid"));
        Order order = orderService.findOne(oid); 
        User user = (User) request.getSession().getAttribute("user");
        ResultInfo info = new ResultInfo();
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("请登录！");
            writeValue(info, response);
            return;
        }
//        order.getMember().setUser(user);
        //暂时保存订单
        request.getSession().setAttribute("order", order);
        info.setData(order);
        info.setFlag(true);
        writeValue(info, response);
    }
    
    /**
     * 支付订单
     * @param request
     * @param response 
     */
    public void pay(HttpServletRequest request,HttpServletResponse response) throws IOException{
        Order order = (Order) request.getSession().getAttribute("order");
        User user = order.getUser();
        Double realpay = order.getRealpay();
        Double balance = user.getBalance();
        ResultInfo info = new ResultInfo();
        if(balance <= realpay){
            info.setFlag(false);
            info.setErrorMsg("余额不足！请在30分钟内前往线下支付！");
            writeValue(info, response);
            return;
        }
        user.setBalance(balance-realpay);
        order.setUser(user);
        order.setIspay(true);
        Boolean payOk = orderService.payOrder(order);
        Integer hid = order.getHid();
        //设置出售状态
        Housres housres = new Housres();
        housres.setHid(hid);
        housres.setIssell(true);
        housresService.setSell(housres);
        info.setFlag(true);
        writeValue(info, response);
        request.getSession().removeAttribute("order");
    }

    /**
     * 查询单个用户的所有订单
     * @param request
     * @param response
     * @throws IOException
     */
    public void findAllByUid(HttpServletRequest request,HttpServletResponse response) throws IOException{
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.findAllByUid(user.getUid());


        writeValue(orderList, response);
    }
    
    /**
     * 查询所有订单
     * @param request
     * @param response
     * @throws IOException 
     */
    public void findAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
        
        List<Order> orders = orderService.findAll();
        request.getSession().setAttribute("orders",orders);
        writeValue(orders, response);
    }
    
    /**
     * 删除订单
     * @param request
     * @param response
     * @throws IOException 
     */
    public void deleteOrder(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String oid = request.getParameter("oid");
        Boolean flag = orderService.deleteOrder(Integer.parseInt(oid));
        ResultInfo info =new ResultInfo();
        if(flag){
            info.setData("删除成功！");
            //request.getSession().removeAttribute("orders");
        }else{
            info.setData("删除失败！");
        }
        writeValue(info, response);
    }
    
    /**
     * 根据订单号查询订单
     * @param request
     * @param response
     * @throws IOException 
     */
    public void findOrderByOnum(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String onum = request.getParameter("onum");
        Order order = orderService.findOrderByOnum(onum);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        writeValue(orders, response);
    }
    
    /**
     * 根据房间号查询订单
     * @param request
     * @param response
     * @throws IOException 
     */
    public void findOrderByHid(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String hid = request.getParameter("hid");
        List<Order> orders = orderService.findOrderByHid(Integer.parseInt(hid));
        writeValue(orders, response);
    }
            
    /**
     * 根据下单日期查询
     * @param request
     * @param response
     * @throws IOException 
     */
    public void findOrderByOdate(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String update = request.getParameter("update");
        String lowdate = request.getParameter("lowdate");
        if(update == null || "".equals(update)){
            update = new Date().toLocaleString();
        }
        if(lowdate == null || "".equals(lowdate)){
            lowdate = "2020-02-01";
        }
        List<Order> orders = orderService.findOrderByOdate(lowdate,update);
        writeValue(orders, response);
    }
    
    
    /**
     * 查询全部未处理订单
     * @param request
     * @param response
     * @throws IOException 
     */
    public void findOrderByNotHandle(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String hid = request.getParameter("hid");
        List<Order> orders = orderService.findOrderByNotHandle();

        writeValue(orders, response);
    }
    
    
    /**
     * 办理入住
     * @param request
     * @param response
     * @throws IOException 
     */
    public void checkin(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String oid = request.getParameter("oid");
        Order order =new Order();
        order.setOid(Integer.parseInt(oid));
        order.setIscheck(true);
        order.setIspay(true);
        order.setIshandle(true);
        Boolean flag= orderService.checkin(order);
        ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        if(flag){
            //info.setData("办理入住成功！");
            info.setData(order);
        }else{
            info.setData("办理失败！");
        }
        writeValue(info, response);
    }
    
    /**
     * 新增入住
     * @param request
     * @param response
     * @throws IOException 
     */
    public void addCheckin(HttpServletRequest request,HttpServletResponse response) throws IOException{

    }
    
    /**
     * 查询已入住未退房的房间
     * @param request
     * @param response
     * @throws IOException 
     */
    public void findAllCheckinAndHandleOver(HttpServletRequest request,HttpServletResponse response) throws IOException{
        
        List<Order> orders = orderService.findAllCheckinAndHandleOver();
        
        writeValue(orders, response);
    }
    
    /**
     * 退房
     * @param request
     * @param response
     * @throws IOException 
     */
    public void checkOut(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String oid = request.getParameter("oid");
        Order order =new Order();
        order.setOid(Integer.parseInt(oid));
        order.setIscheck(false);
        order.setIspay(true);
        order.setIshandle(true);
        Boolean flag  = orderService.checkOut(order);
        
        ResultInfo info =new ResultInfo();
        info.setFlag(flag);
        if(flag){
            //info.setData("退房成功！");
            info.setData(order);
        }else{
            info.setData("退房失败!");
        }
        writeValue(info, response);
    }
}
