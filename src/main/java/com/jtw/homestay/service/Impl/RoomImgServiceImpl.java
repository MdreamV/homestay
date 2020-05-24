/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtw.homestay.service.Impl;

import com.jtw.homestay.dao.RoomImgDao;
import com.jtw.homestay.domain.RoomImg;
import com.jtw.homestay.service.RoomImgService;
import com.jtw.homestay.utils.DaoUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author  jml
 * @date    2020-3-17 14:24:03
 * @version V1.0
 * @desc    
 */
public class RoomImgServiceImpl implements RoomImgService {

    /**
     * 添加房间图片
     * @param roomImg
     * @return 
     */
    @Override
    public Boolean addRoomImg(RoomImg roomImg) {
        SqlSession session = DaoUtils.getSession();
        RoomImgDao roomImgDao = session.getMapper(RoomImgDao.class);
        Boolean flag = roomImgDao.addRoomIng(roomImg);
        session.close();
        return flag;
    }

    /**
     * 更新房间图片信息
     * @param roomImg
     * @return 
     */
    @Override
    public Boolean updateRoomImg(RoomImg roomImg) {
        SqlSession session = DaoUtils.getSession();
        RoomImgDao roomImgDao = session.getMapper(RoomImgDao.class);
        Boolean flag = roomImgDao.updateRoomImg(roomImg);
        session.close();
        return flag;
    }

    @Override
    public Boolean deleteImgByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        RoomImgDao roomImgDao = session.getMapper(RoomImgDao.class);
        Boolean flag = roomImgDao.deleteRoomImg(hid);
        session.close();
        return flag;
    }

}
