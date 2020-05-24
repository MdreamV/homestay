/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtw.homestay.dao;

import com.jtw.homestay.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author  jtw
 * @date    2020-4-2 15:21:03
 * @version V1.0
 * @desc    
 */
public interface UserDao {

    /**
     * 添加用户
     * @param user
     * @return 
     */
    @Insert("insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code,regdate,isadmin,balance)values(#{userName},#{password},#{name},#{birthday},#{sex},#{telephone},#{email},#{status},#{code},#{regdate},#{isadmin},#{balance})")
    @Options(useGeneratedKeys = true,keyProperty = "uid",keyColumn = "uid")
    Boolean addUser(User user);
    
    /**
     * 根据用户ID删除用户
     * @param uid
     * @return 
     */
    @Delete("delete from tab_user where uid=#{uid}")
    Boolean deleteUser(Integer uid);
    
    /**
     * 更新用户信息
     * @param user
     * @return 
     */
    @Update("<script>"
            + "update tab_user set"
            + "     <if test='userName != null'>username=#{userName},</if>"
            + "     <if test='password != null'>password=#{password},</if>"
            + "     <if test='name != null'>name=#{name},</if>"
            + "     <if test='birthday != null'>birthday=#{birthday},</if>"
            + "     <if test='sex != null'>sex=#{sex},</if>"
            + "     <if test='telephone != null'>telephone=#{telephone},</if>"
            + "     <if test='email != null'>email=#{email}, </if>"
            + "     <if test='status != null'>status=#{status}, </if>"
            + "     <if test='balance != null'>balance=#{balance} </if>"
            + "where uid=#{uid}"
            + "</script>")
    Boolean updateUser(User user);
    
    /**
     * 查询所有用户
     * @return 
     */
    @Select("select * from tab_user")
    @Results(id="userMap",value = {
        @Result(id=true,property = "uid",column = "uid"),
        @Result(property = "userName",column = "username"),
        @Result(property = "name",column = "name"),
        @Result(property = "birthday",column = "birthday"),
        @Result(property = "sex",column = "sex"),
        @Result(property = "telephone",column = "telephone"),
        @Result(property = "email",column = "email"),
        @Result(property = "status",column = "status"),
        @Result(property = "regdate",column = "regdate"),
        @Result(property = "isadmin",column = "isadmin"),
        @Result(property = "balance",column = "balance")
    })
    List<User> findAll();
    
    /**
     * 根据用户名查询用户
     * @param userName
     * @return 
     */
    @Select("select * from tab_user where username=#{userName}")
    @ResultMap("userMap")
    User findByUserName(@Param("userName") String userName);

    /**
     * 通过用户id查询用户
     * @param uid
     * @return
     */
    @Select("select * from tab_user where uid=#{uid}")
    @ResultMap("userMap")
    User findByUid(Integer uid);

    /**
     * 根据用户名和密码查询用户
     * @param userName
     * @param password
     * @return
     */
    @Select("select * from tab_user where username=#{userName} and password=#{password}")
    @ResultMap("userMap")
    User findByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据激活码查询用户
     * @param code
     * @return
     */
    @Select("select * from tab_user where code = #{code}")
    @ResultMap("userMap")
    User findByCode(@Param("code") String code);

    /**
     * 更新用户激活状态
     * @param user 
     * @return  
     */
    @Update("update tab_user set status = #{status} where uid = #{uid}")
    Boolean updateStatus(User user);
}
