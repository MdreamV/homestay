package com.jtw.homestay.service.Impl;

import com.jtw.homestay.dao.CommentDao;
import com.jtw.homestay.dao.HousresDao;
import com.jtw.homestay.domain.Comment;
import com.jtw.homestay.domain.Housres;
import com.jtw.homestay.service.CommentService;
import com.jtw.homestay.utils.DaoUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-05
 */

public class CommentServiceImpl implements CommentService {

    /**
     * 根据房源编号查询评论
     * @param hid
     * @return
     */
    @Override
    public List<Comment> findByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        CommentDao commentDao = session.getMapper(CommentDao.class);
        List<Comment> commentList = commentDao.findByHid(hid);
        session.close();
        return commentList;
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Override
    public Boolean addComment(Comment comment) {
        SqlSession session = DaoUtils.getSession();
        CommentDao commentDao = session.getMapper(CommentDao.class);
        Boolean flag = commentDao.addComment(comment);
        HousresDao housresDao = session.getMapper(HousresDao.class);
        Housres housres = housresDao.findByHid(comment.getHid());

        housres.setDes(housres.getDes() == 0.d ? comment.getDes() :(housres.getDes()+comment.getDes())/2);
        housres.setHygiene(housres.getHygiene() == 0.d ? comment.getHygiene():(housres.getHygiene()+comment.getHygiene())/2);
        housres.setLocation(housres.getLocation() ==0.d? comment.getLocation():(housres.getLocation()+comment.getLocation())/2);
        housres.setHscore(housres.getHscore() == 0.d ? comment.getScore():(comment.getScore()+housres.getHscore())/2);
        housresDao.updateHousres(housres);
        session.close();
        return flag;
    }

    @Override
    public List<Comment> findAllCommentByUid(Integer uid) {
        List<Comment> comments = new ArrayList<>();
        SqlSession session = DaoUtils.getSession();
        CommentDao commentDao = session.getMapper(CommentDao.class);
        HousresDao housresDao = session.getMapper(HousresDao.class);
        List<Housres> housresList = housresDao.findAllHousresByUid(uid);
        Iterator<Housres> ite = housresList.iterator();
        while(ite.hasNext()){
            Housres housres = ite.next();
            List<Comment> commentList = commentDao.findByHid(housres.getHid());
            for (Comment comment:commentList) {
                comments.add(comment);
            }
        }
        session.close();
        return comments;
    }

    @Override
    public List<Comment> findAllCommentByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        CommentDao commentDao = session.getMapper(CommentDao.class);
        List<Comment> commentList = commentDao.findByHid(hid);
        session.close();
        return commentList;
    }

    /**
     * 回复评论
     * @param comment
     * @return
     */
    @Override
    public Boolean reply(Comment comment) {
        SqlSession session = DaoUtils.getSession();
        CommentDao commentDao = session.getMapper(CommentDao.class);
        Boolean aBoolean = commentDao.updateCommet(comment);
        session.close();
        return aBoolean;
    }

    /**
     * 根据房间id删除评论
     * @param hid
     * @return
     */
    @Override
    public Boolean deleteCommentByHid(Integer hid) {
        SqlSession session = DaoUtils.getSession();
        CommentDao commentDao = session.getMapper(CommentDao.class);
        Boolean deleteCommentByHid = commentDao.deleteCommentByHid(hid);
        session.close();
        return deleteCommentByHid;
    }
}
