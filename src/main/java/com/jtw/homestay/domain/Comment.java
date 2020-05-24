package com.jtw.homestay.domain;

import java.text.DecimalFormat;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-02
 */

public class Comment {

    private Integer cid; //编号
    private String content; //评价内容
    private String reply; //店家回复
    private String contentdate; //评价时间
    private String replydate; //回复时间
    private Integer browsevolume; //浏览量
    private Double des=0d; // 描述
    private Double hygiene=0d; // 卫生
    private Double location=0d; // 位置
    private Double score=0d; //评分
    private String img; //图片
    private Integer hid; //房源号
    private Integer uid; //用户

    private User user; //一对一



    public void setScore(Double score) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.score = Double.parseDouble(df.format(score));
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getContentdate() {
        return contentdate;
    }

    public void setContentdate(String contentdate) {
        this.contentdate = contentdate;
    }

    public Integer getBrowsevolume() {
        return browsevolume;
    }

    public void setBrowsevolume(Integer browsevolume) {
        this.browsevolume = browsevolume;
    }

    public Double getScore() {
        return score;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getDes() {
        return des;
    }

    public void setDes(Double des) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.des = Double.parseDouble(df.format(des));
    }

    public Double getHygiene() {
        return hygiene;
    }

    public void setHygiene(Double hygiene) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.hygiene = Double.parseDouble(df.format(hygiene));
    }

    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.location = Double.parseDouble(df.format(location));
    }

    public String getReplydate() {
        return replydate;
    }

    public void setReplydate(String replydate) {
        this.replydate = replydate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", content='" + content + '\'' +
                ", reply='" + reply + '\'' +
                ", contentdate='" + contentdate + '\'' +
                ", replydate='" + replydate + '\'' +
                ", browsevolume=" + browsevolume +
                ", des=" + des +
                ", hygiene=" + hygiene +
                ", location=" + location +
                ", score=" + score +
                ", img='" + img + '\'' +
                ", hid=" + hid +
                ", uid=" + uid +
                ", user=" + user +
                '}';
    }
}
