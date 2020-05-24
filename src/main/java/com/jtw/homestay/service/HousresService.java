/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.service;

import com.jtw.homestay.domain.Housres;
import com.jtw.homestay.domain.PageBean;

import java.util.List;

/**
 * @author  jml
 * @date    2020-3-9 14:08:54
 * @version V1.0
 * @desc    
 */
public interface HousresService {
    
    /**
     * 查询全部房间信息
     * @return 
     */
    List<Housres> findAll();
    
    /**
     * 查询单个房间信息
     * @param hid
     * @return 
     */
    Housres findByHid(Integer hid);
    
    /**
     * 根据房间名称查询房间
     * @param roomName
     * @return 
     */
    List<Housres> findByRoomName(String roomName);

    /**
     * 设置房间的出售状态
     * @param room 
     * @return  
     */
    Boolean setSell(Housres room);

    /**
     * 添加房间
     * @param room
     * @return 
     */
    Boolean addRoom(Housres room);

    /**
     * 查询所有房间
     * @return 
     */
    List<Housres> findAllRoom();

    /**
     * 根据房间编号删除房间
     * @param hid
     * @return 
     */
    Boolean deleteHousreByHid(Integer hid);

    /**
     * 更新房源
     * @param room
     * @return
     */
    Boolean updateRoom(Housres room);

    /**
     * 分页查询
     * @param rid
     * @param currentPage
     * @param count
     * @return
     */
    PageBean<Housres> findLimit(Integer rid, Integer currentPage, Integer count);

    /**
     * 查询所有地区
     * @return
     */
    List<String> findAllRegion();

    /**
     * 查询单个用户的所有房源
     * @param uid
     * @return
     */
    List<Housres> findAllHousresByUid(Integer uid);
}
