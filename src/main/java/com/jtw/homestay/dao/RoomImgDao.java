/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtw.homestay.dao;

import com.jtw.homestay.domain.RoomImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author  jml
 * @date    2020-3-14 15:21:03
 * @version V1.0
 * @desc    
 */
public interface RoomImgDao {
    
    /**
     * 添加房间图片
     * @param roomImg
     * @return 
     */
    @Insert("insert into tab_room_img(hid,bigpic,smallpic1,smallpic2,smallpic3,smallpic4)values(#{hid},#{bigpic},#{smallpic1},#{smallpic2},#{smallpic3},#{smallpic4})")
    @Options(useGeneratedKeys = true,keyProperty = "rgid",keyColumn = "rgid")
    Boolean addRoomIng(RoomImg roomImg);
    
    /**
     * 根据房间编号删除房间图片
     * @param hid
     * @return 
     */
    @Delete("delete from tab_room_img where hid=#{hid}")
    Boolean deleteRoomImg(@Param("hid") Integer hid);
    
    /**
     * 更新房间图片
     * @param roomImg
     * @return 
     */
    @Update("<script>"
            + "update tab_room_img set"
            + "<if test = 'hid != null'>hid = #{hid},</if>"
            + "<if test = 'smallpic1 != null'>smallpic1 = #{smallpic1},</if>"
            + "<if test = 'bigpic != null'>bigpic = #{bigpic}</if>"
            + "where rgid= #{rgid}"
            + "</script>")
    Boolean updateRoomImg(RoomImg roomImg);


    
    /**
     * 查询全部房间图片
     * @return 
     */
    @Select("select * from tab_room_img")
    List<RoomImg> findAll();
    
    /**
     * 根据房间编号查询房间图片
     * @param hid
     * @return 
     */
    @Select("select * from tab_room_img where hid = #{hid}")
    RoomImg findByHid(@Param("hid") Integer hid);
    
}
