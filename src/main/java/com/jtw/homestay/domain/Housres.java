package com.jtw.homestay.domain;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-02
 */

public class Housres {

    private Integer hid;  //编号
    private String hname;  //户型名称
    private String hdetail;  //户型介绍
    private Integer checkincount;  //可入住人数
    private String bedtype;  //床型
    private Integer bedcount;  //床数
    private Double harea;  //房间面积
    private Double hprice;  //价格
    private Integer rid;  //地区编号
    private Integer fid;  //设施号
    private Boolean issell=false;  //是否出售
    private Integer uid; //用户编号
    private Double des; // 描述
    private Double hygiene; // 卫生
    private Double location; // 位置
    private Double hscore; // 总评分
    private String detailregion; //详细地址

    private Facilities facilities; //设施
    private User user; //房东
    private RoomImg roomImg; //房间图片
    private Region region; //地区
    private List<Comment> comment; //评论


    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHdetail() {
        return hdetail;
    }

    public void setHdetail(String hdetail) {
        this.hdetail = hdetail;
    }

    public Integer getCheckincount() {
        return checkincount;
    }

    public void setCheckincount(Integer checkincount) {
        this.checkincount = checkincount;
    }

    public String getBedtype() {
        return bedtype;
    }

    public void setBedtype(String bedtype) {
        this.bedtype = bedtype;
    }

    public Integer getBedcount() {
        return bedcount;
    }

    public void setBedcount(Integer bedcount) {
        this.bedcount = bedcount;
    }

    public Double getHarea() {
        return harea;
    }

    public void setHarea(Double harea) {
        this.harea = harea;
    }

    public Double getHprice() {
        return hprice;
    }

    public void setHprice(Double hprice) {
        this.hprice = hprice;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Boolean getIssell() {
        return issell;
    }

    public void setIssell(Boolean issell) {
        this.issell = issell;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public void setFacilities(Facilities facilities) {
        this.facilities = facilities;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoomImg getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(RoomImg roomImg) {
        this.roomImg = roomImg;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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
        this.hygiene = Double.parseDouble(df.format(hygiene));;
    }

    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.location = Double.parseDouble(df.format(location));
    }

    public Double getHscore() {
        return hscore;
    }

    public void setHscore(Double hscore) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.hscore = Double.parseDouble(df.format(hscore));
    }


    public String getDetailregion() {
        return detailregion;
    }

    public void setDetailregion(String detailregion) {
        this.detailregion = detailregion;
    }

    @Override
    public String toString() {
        return "Housres{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                ", hdetail='" + hdetail + '\'' +
                ", checkincount=" + checkincount +
                ", bedtype='" + bedtype + '\'' +
                ", bedcount=" + bedcount +
                ", harea=" + harea +
                ", hprice=" + hprice +
                ", rid='" + rid + '\'' +
                ", fid=" + fid +
                ", issell=" + issell +
                ", uid=" + uid +
                ", des=" + des +
                ", hygiene=" + hygiene +
                ", location=" + location +
                ", hscore=" + hscore +
                ", detailregion='" + detailregion + '\'' +
                ", facilities=" + facilities +
                ", user=" + user +
                ", roomImg=" + roomImg +
                ", region=" + region +
                ", comment=" + comment +
                '}';
    }
}
