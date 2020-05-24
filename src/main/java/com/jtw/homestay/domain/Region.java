package com.jtw.homestay.domain;

/**
 * @author 扣人心弦的稳
 * @project homestay
 * @create 2020-04-04
 */

public class Region {

    private Integer rid;
    private String region;
    private String detailregion;

    public String getDetailregion() {
        return detailregion;
    }

    public void setDetailregion(String detailregion) {
        this.detailregion = detailregion;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Region{" +
                "rid=" + rid +
                ", region='" + region + '\'' +
                ", detailregion='" + detailregion + '\'' +
                '}';
    }
}
