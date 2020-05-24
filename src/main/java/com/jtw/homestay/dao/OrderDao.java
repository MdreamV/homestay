/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtw.homestay.dao;


import com.jtw.homestay.domain.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author  jtw
 * @date    2020-4-1 15:21:03
 * @version V1.0
 * @desc    
 */
public interface OrderDao {
    
    /**
     * 添加订单
     * @param order
     * @return 
     */
    @Insert("insert into tab_order(onum,odate,uid,totalprice,realpay,checkindate,checkoutdate,ischeck,hid,ispay,ishandle,cid)"
            + "values(#{onum},#{odate},#{uid},#{totalprice},#{realpay},#{checkindate},#{checkoutdate},#{ischeck},#{hid},#{ispay},#{ishandle},#{cid})")
    @Options(useGeneratedKeys = true,keyProperty = "oid",keyColumn = "oid")
    Boolean addOrder(Order order);
    
    /**
     * 删除订单
     * @param oid
     * @return 
     */
    @Delete("delete from tab_order where oid = #{oid}")
    Boolean deleteOrder(Integer oid);

    @Delete("delete from tab_order where hid = #{hid}")
    Boolean deleteOrderByHid(Integer hid);
    
    /**
     * 更新订单
     * @param order
     * @return 
     */
    @Update("<script>"
            + "update tab_order set"
            + "<if test= 'onum != null'>onum=#{onum},</if>"
            + "<if test= 'odate != null'>odate=#{odate},</if>"
            + "<if test= 'uid != null'>uid=#{uid},</if>"
            + "<if test= 'totalprice != null'>totalprice=#{totalprice},</if>"
            + "<if test= 'realpay != null'>realpay=#{realpay},</if>"
            + "<if test= 'checkindate != null'>checkindate=#{checkindate},</if>"
            + "<if test= 'checkoutdate != null'>checkoutdate=#{checkoutdate},</if>"
            + "<if test= 'ischeck != null'>ischeck=#{ischeck},</if>"
            + "<if test= 'ispay != null'>ispay=#{ispay},</if>"
            + "<if test= 'cid != null'>cid=#{cid},</if>"
            + "<if test= 'ishandle != null'>ishandle=#{ishandle}</if>"
            +"where oid = #{oid}"
            + "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "oid",keyColumn = "oid")
    Boolean updateOrder(Order order);
    
    /**
     * 查询所有订单
     * @return 
     */
    @Select("select * from tab_order")
    @Results(id="orderMap",value = {
        @Result(id= true,property = "oid" ,column = "oid"),
        @Result(property = "onum" ,column = "onum"),
        @Result(property = "uid" ,column = "uid"),
        @Result(property = "odate" ,column = "odate"),
        @Result(property = "totalprice" ,column = "totalprice"),
        @Result(property = "realpay" ,column = "realpay"),
        @Result(property = "checkindate" ,column = "checkindate"),
        @Result(property = "checkoutdate" ,column = "checkoutdate"),
        @Result(property = "ischeck" ,column = "ischeck"),
        @Result(property = "hid" ,column = "hid"),
        @Result(property = "ispay" ,column = "ispay"),
        @Result(property = "ishandle" ,column = "ishandle"),
        @Result(property = "cid" ,column = "cid"),
        @Result(property = "user" ,column = "uid",one=@One(select="com.jtw.homestay.dao.UserDao.findByUid",fetchType = FetchType.EAGER)),
        @Result(property = "housres" ,column = "hid",one=@One(select="com.jtw.homestay.dao.HousresDao.findByHid",fetchType = FetchType.EAGER))
    })
    List<Order> findAll();
    
    /**
     * 根据订单编号查询订单
     * @param oid
     * @return 
     */
    @Select("select * from tab_order where oid = #{oid}")
    @ResultMap("orderMap")
    Order findByOid(Integer oid);
    
    /**
     * 根据订单号查询订单
     * @param oNum
     * @return 
     */
    @Select("select * from tab_order where onum=#{oNum}")
    @ResultMap("orderMap")
    Order findByONum(String oNum);

    /**
     * 查询单个用户的所有订单
     * @param uid
     * @return 
     */
    @Select("select * from tab_order where uid = #{uid}")
    @ResultMap("orderMap")
    List<Order> findAllByUid(Integer uid);

    /**
     * 根据房间编号查询订单
     * @param hid
     * @return 
     */
    @Select("select * from tab_order where hid = #{hid}")
    @ResultMap("orderMap")
    List<Order> findOrderByHid(Integer hid);

    /**
     * 根据下单日期查询
     * @param lowdate
     * @param update
     * @return 
     */
    @Select("select * from tab_order where odate > #{lowdate} and odate < #{update}")
    @ResultMap("orderMap")
    List<Order> findOrderByOdate(@Param("lowdate") String lowdate, @Param("update") String update);

    /**
     * 查询未处理订单
     * @return 
     */
    @Select("select * from tab_order where ischeck = 0 and ishandle = 0")
    @ResultMap("orderMap")
    List<Order> findOrderByNotHandle();

    /**
     * 查询已入住未退房的订单
     * @return 
     */
    @Select("select * from tab_order where ischeck = 1 and ishandle = 1")
    @ResultMap("orderMap")
    List<Order> findAllCheckinAndHandleOver();
    
    }
