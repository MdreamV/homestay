package com.jtw.homestay.service;

import com.jtw.homestay.domain.Comment;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-05
 */

public interface CommentService {

    /**
     * 根据房源查询评论
     * @param hid
     * @return
     */
    List<Comment> findByHid(Integer hid);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    Boolean addComment(Comment comment);

    /**
     * 查询单个房东的所有评论
     * @param uid
     * @return
     */
    List<Comment> findAllCommentByUid(Integer uid);

    List<Comment> findAllCommentByHid(Integer hid);

    /**
     * 回复评论
     * @param comment
     * @return
     */
    Boolean reply(Comment comment);

    /**
     * 删除房间评论
     * @param hid
     * @return
     */
    Boolean deleteCommentByHid(Integer hid);
}
