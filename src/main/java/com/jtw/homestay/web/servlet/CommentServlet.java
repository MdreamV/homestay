package com.jtw.homestay.web.servlet;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jtw.homestay.domain.Comment;
import com.jtw.homestay.domain.Order;
import com.jtw.homestay.domain.ResultInfo;
import com.jtw.homestay.domain.User;
import com.jtw.homestay.service.CommentService;
import com.jtw.homestay.service.Impl.CommentServiceImpl;
import com.jtw.homestay.service.Impl.OrderServiceImpl;
import com.jtw.homestay.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-07
 */
@WebServlet(name = "commentServlet",urlPatterns = "/comment/*")
public class CommentServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    /**
     * 提交评论
     * @param request
     * @param response
     */
    public void submitComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oid = request.getParameter("oid");
        String hid = request.getParameter("hid");
        String uid = request.getParameter("uid");
        String des = request.getParameter("des");
        String hygiene = request.getParameter("hygiene");
        String location = request.getParameter("location");
        String content = request.getParameter("content");

        double score = (Double.parseDouble(des)+Double.parseDouble(hygiene)+Double.parseDouble(location))/3;

        Comment comment =new Comment();
        comment.setContent(content);
        comment.setContentdate(new Date().toLocaleString());
        comment.setUid(Integer.parseInt(uid));
        comment.setHid(Integer.parseInt(hid));
        comment.setDes(Double.parseDouble(des));
        comment.setHygiene(Double.parseDouble(hygiene));
        comment.setLocation(Double.parseDouble(location));
        comment.setScore(score);

        Boolean flag = commentService.addComment(comment);

        Order order = orderService.findOne(Integer.parseInt(oid));
        order.setCid(comment.getCid());
        orderService.addComment(order);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setData("评论失败！");
        }
        writeValue(info,response);

    }

    /**
     * 查询单个房东的所有评论
     * @param request
     * @param response
     */
    public void findAllCommentByHid(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String hid = request.getParameter("hid");
        List<Comment> commentList = commentService.findAllCommentByHid(Integer.parseInt(hid));
        writeValue(commentList,response);

    }

    public void reply(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String cid = request.getParameter("cid");
        String reply = request.getParameter("reply");

        Comment comment = new Comment();
        comment.setCid(Integer.parseInt(cid));
        comment.setReply(reply);
        comment.setReplydate(new Date().toLocaleString());

        Boolean flag =  commentService.reply(comment);
        ResultInfo info = new ResultInfo();
        info.setFlag(false);
        if(flag){
            info.setFlag(true);
        }
        writeValue(info,response);
    }
}
