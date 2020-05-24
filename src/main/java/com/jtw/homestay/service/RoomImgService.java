/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.service;


import com.jtw.homestay.domain.RoomImg;

/**
 * @author  jml
 * @date    2020-3-17 14:23:53
 * @version V1.0
 * @desc    
 */
public interface RoomImgService {
    
    /**
     * 添加房间图片
     * @param roomImg
     * @return 
     */
    Boolean addRoomImg(RoomImg roomImg);

    /**
     * 更新房间图片信息
     * @param roomImg
     * @return 
     */
    Boolean updateRoomImg(RoomImg roomImg);

    Boolean deleteImgByHid(Integer hid);

}
