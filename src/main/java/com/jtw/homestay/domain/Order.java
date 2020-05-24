/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtw.homestay.domain;


/**
 *
 * @author 扣人心弦的稳
 */
public class Order {
    
    private Integer oid; //订单编号
    private String onum; //订单号
    private String odate; //下单日期
    private Integer uid; //会员id
    private Double totalprice; //总金额
    private Double realpay; //实际支付
    private String checkindate; //入住时间
    private String checkoutdate; //退房时间
    private Boolean ischeck = false; //是否入住了
    private Integer hid; //房间编号
    private Boolean ispay=false; //是否支付
    private Boolean ishandle= false; //是否处理
    private Integer cid; //评价编号

    private Housres housres; //房源信息，一对一
    private Comment comment; //评价,一对一
    private User user; //用户，一对一
    
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOnum() {
        return onum;
    }

    public void setOnum(String onum) {
        this.onum = onum;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getRealpay() {
        return realpay;
    }

    public void setRealpay(Double realpay) {
        this.realpay = realpay;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Housres getHousres() {
        return housres;
    }

    public void setHousres(Housres housres) {
        this.housres = housres;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }


    public Boolean getIspay() {
        return ispay;
    }

    public void setIspay(Boolean ispay) {
        this.ispay = ispay;
    }

    public Boolean getIshandle() {
        return ishandle;
    }

    public void setIshandle(Boolean ishandle) {
        this.ishandle = ishandle;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", onum='" + onum + '\'' +
                ", odate='" + odate + '\'' +
                ", uid=" + uid +
                ", totalprice=" + totalprice +
                ", realpay=" + realpay +
                ", checkindate='" + checkindate + '\'' +
                ", checkoutdate='" + checkoutdate + '\'' +
                ", ischeck=" + ischeck +
                ", hid=" + hid +
                ", ispay=" + ispay +
                ", ishandle=" + ishandle +
                ", cid=" + cid +
                ", housres=" + housres +
                ", comment=" + comment +
                ", user=" + user +
                '}';
    }
}
