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
public class RoomImg {
    private Integer rgid;
    private Integer hid;
    private String bigpic;
    private String smallpic1;
    private String smallpic2;
    private String smallpic3;
    private String smallpic4;

    public Integer getRgid() {
        return rgid;
    }

    public void setRgid(Integer rgid) {
        this.rgid = rgid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getSmallpic1() {
        return smallpic1;
    }

    public void setSmallpic1(String smallpic1) {
        this.smallpic1 = smallpic1;
    }

    public String getSmallpic2() {
        return smallpic2;
    }

    public void setSmallpic2(String smallpic2) {
        this.smallpic2 = smallpic2;
    }

    public String getSmallpic3() {
        return smallpic3;
    }

    public void setSmallpic3(String smallpic3) {
        this.smallpic3 = smallpic3;
    }

    public String getSmallpic4() {
        return smallpic4;
    }

    public void setSmallpic4(String smallpic4) {
        this.smallpic4 = smallpic4;
    }

    public String getBigpic() {
        return bigpic;
    }

    public void setBigpic(String bigpic) {
        this.bigpic = bigpic;
    }

    @Override
    public String toString() {
        return "RoomImg{" +
                "rgid=" + rgid +
                ", hid=" + hid +
                ", bigpic='" + bigpic + '\'' +
                ", smallpic1='" + smallpic1 + '\'' +
                ", smallpic2='" + smallpic2 + '\'' +
                ", smallpic3='" + smallpic3 + '\'' +
                ", smallpic4='" + smallpic4 + '\'' +
                '}';
    }
}
