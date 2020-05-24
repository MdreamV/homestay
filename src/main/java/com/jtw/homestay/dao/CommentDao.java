package com.jtw.homestay.dao;

import com.jtw.homestay.domain.Comment;
import com.jtw.homestay.domain.Housres;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-02
 */

public interface CommentDao {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Insert("insert into tab_comment(content,contentdate,score,hid,uid,des,hygiene,location)" +
            "values(#{content},#{contentdate},#{score},#{hid},#{uid},#{des},#{hygiene},#{location})")
    @Options(useGeneratedKeys = true,keyColumn = "cid",keyProperty = "cid")
    Boolean addComment(Comment comment);

    /**
     * 删除评论
     * @param cid
     * @return
     */
    @Delete("delete from tab_comment where cid = #{cid}")
    Boolean deleteComment(Integer cid);

    /**
     * 更新评论
     * @param comment
     * @return
     */
    @Update("<script>update tab_comment set" +
            "<if test = 'browsevolume != null'>browsevolume = #{browsevolume},</if>" +
            "<if test = 'replydate != null'>replydate = #{replydate},</if>" +
            "<if test = 'reply != null'>reply = #{reply}</if>" +
            "where cid = #{cid}" +
            "</script>")
    Boolean updateCommet(Comment comment);

    /**
     * 根据id查询评论
     * @param cid
     * @return
     */
    @Select("select * from tab_comment where cid = #{cid}")
    @Results(id="commentMap",value = {
            @Result(id = true,property = "cid",column = "cid"),
            @Result(property = "content",column = "content"),
            @Result(property = "reply",column = "reply"),
            @Result(property = "replydate",column = "replydate"),
            @Result(property = "contentdate",column = "contentdate"),
            @Result(property = "browsevolume",column = "browsevolume"),
            @Result(property = "des",column = "des"),
            @Result(property = "hygiene",column = "hygiene"),
            @Result(property = "location",column = "location"),
            @Result(property = "score",column = "score"),
            @Result(property = "hid",column = "hid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "user",column = "uid",one=@One(select = "com.jtw.homestay.dao.UserDao.findByUid",fetchType = FetchType.EAGER))
    })
    Comment findByCid(Integer cid);

    /**
     * 查询所有评论
     * @return
     */
    @Select("select * from tab_comment")
    @ResultMap("commentMap")
    List<Comment> findAll();

    /**
     * 根据房源查询评论
     * @param hid
     * @return
     */
    @Select("select * from tab_comment where hid = #{hid}")
    @ResultMap("commentMap")
    List<Comment> findByHid(Integer hid);

    @Delete("delete from tab_comment where hid = #{hid}")
    Boolean deleteCommentByHid(Integer hid);

}
