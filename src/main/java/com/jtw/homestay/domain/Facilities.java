package com.jtw.homestay.domain;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-02
 */

public class Facilities {

    private Integer fid;  //设施编号
    private Boolean wifi=true;  //wifi
    private Boolean network=true;  //有线网络
    private Boolean airconditioner=true;  //空调
    private Boolean heating=true;  //暖气
    private Boolean hotshower=true;  //热水淋浴
    private Boolean shampoo=true;  //洗发水
    private Boolean showergel=true;  //沐浴露
    private Boolean tableware=true;  //餐具
    private Boolean gasstove=true;  //燃气灶
    private Boolean freeparking=true;  //免费停车

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getNetwork() {
        return network;
    }

    public void setNetwork(Boolean network) {
        this.network = network;
    }

    public Boolean getAirconditioner() {
        return airconditioner;
    }

    public void setAirconditioner(Boolean airconditioner) {
        this.airconditioner = airconditioner;
    }

    public Boolean getHeating() {
        return heating;
    }

    public void setHeating(Boolean heating) {
        this.heating = heating;
    }

    public Boolean getHotshower() {
        return hotshower;
    }

    public void setHotshower(Boolean hotshower) {
        this.hotshower = hotshower;
    }

    public Boolean getShampoo() {
        return shampoo;
    }

    public void setShampoo(Boolean shampoo) {
        this.shampoo = shampoo;
    }

    public Boolean getShowergel() {
        return showergel;
    }

    public void setShowergel(Boolean showergel) {
        this.showergel = showergel;
    }

    public Boolean getTableware() {
        return tableware;
    }

    public void setTableware(Boolean tableware) {
        this.tableware = tableware;
    }

    public Boolean getGasstove() {
        return gasstove;
    }

    public void setGasstove(Boolean gasstove) {
        this.gasstove = gasstove;
    }

    public Boolean getFreeparking() {
        return freeparking;
    }

    public void setFreeparking(Boolean freeparking) {
        this.freeparking = freeparking;
    }

    @Override
    public String toString() {
        return "Facilities{" +
                "fid=" + fid +
                ", wifi=" + wifi +
                ", network=" + network +
                ", airconditioner=" + airconditioner +
                ", heating=" + heating +
                ", hotshower=" + hotshower +
                ", shampoo=" + shampoo +
                ", showergel=" + showergel +
                ", tableware=" + tableware +
                ", gasstove=" + gasstove +
                ", freeparking=" + freeparking +
                '}';
    }
}
