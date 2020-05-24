package com.jtw.homestay.service.Impl;

import com.jtw.homestay.dao.CommentDao;
import com.jtw.homestay.dao.FacilitiesDao;
import com.jtw.homestay.dao.HousresDao;
import com.jtw.homestay.dao.UserDao;
import com.jtw.homestay.domain.Comment;
import com.jtw.homestay.domain.Facilities;
import com.jtw.homestay.domain.Housres;
import com.jtw.homestay.domain.PageBean;
import com.jtw.homestay.service.HousresService;
import com.jtw.homestay.utils.DaoUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-03
 */

public class HousresServiceImpl implements HousresService {


    @Override
    public List<Housres> findAll() {
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        List<Housres> housresList = housresDao.findAll();
        session.close();
        return housresList;
    }

    /**
     * 根据房源id查询房源信息
     * @param hid
     * @return
     */
    @Override
    public Housres findByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        CommentDao commentDao = session.getMapper(CommentDao.class);
        List<Comment> commentList = commentDao.findByHid(hid);
        Housres housres = housresDao.findByHid(hid);
        housres.setComment(commentList);
        session.close();
        return housres;
    }

    @Override
    public List<Housres> findByRoomName(String roomName) {
        return null;
    }

    @Override
    public Boolean setSell(Housres housres) {
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        Boolean updateHousres = housresDao.updateHousres(housres);
        session.close();
        return updateHousres;
    }

    @Override
    public Boolean addRoom(Housres housres) {
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        Facilities facilities = new Facilities();
        FacilitiesDao facilitiesDao = session.getMapper(FacilitiesDao.class);
        Boolean aBoolean1 = facilitiesDao.addFacilities(facilities);
        housres.setFid(facilities.getFid());
        Boolean aBoolean = housresDao.addHousres(housres);
        session.close();
        return aBoolean;
    }

    @Override
    public List<Housres> findAllRoom() {
        return null;
    }

    @Override
    public Boolean deleteHousreByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        Boolean deleteHousres = housresDao.deleteHousres(hid);
        session.close();
        return deleteHousres;
    }

    @Override
    public Boolean updateRoom(Housres room) {
        return null;
    }

    /**
     * 分页查询
     * @param rid
     * @param currentPage
     * @param count
     * @return
     */
    @Override
    public PageBean<Housres> findLimit(Integer rid, Integer currentPage, Integer count) {
        PageBean<Housres> pageBean = new PageBean<>();
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        //设置页面大小
        pageBean.setPageSize(count);
        //设置总记录数
        Integer total = housresDao.findCountByRegion(rid);
        pageBean.setTotalCount(total);
        //设置当前页数
        pageBean.setCurrentPage(currentPage);
        //设置页面数据集合
        int start = (currentPage - 1) * count;
        List<Housres> housresList = housresDao.findLimit(rid, start, count);
        pageBean.setPageList(housresList);
        //设置总页数
        int pageCount=(total % count) == 0 ? (total / count) : (total / count)+1;
        pageBean.setTotalPage(pageCount);
        session.close();
        return pageBean;
    }

    @Override
    public List<String> findAllRegion() {
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        List<String> regions = housresDao.findAllRegion();
        session.close();
        return regions;
    }

    @Override
    public List<Housres> findAllHousresByUid(Integer uid) {
        SqlSession session = DaoUtils.getSession();
        HousresDao housresDao = session.getMapper(HousresDao.class);
        List<Housres> housresList = housresDao.findAllHousresByUid(uid);
        session.close();
        return housresList;
    }

}
