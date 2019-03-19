package com.zsc.study.kafka.product;

import java.io.Serializable;

/**
 * @Auther: zhangshanchuang
 * @Date: 18/7/27 19:31
 * @Description: 如家city实体类
 */
public class City implements Serializable {

    //省份编号
    private String stateCode;

    //城市code
    private String cD;

    //城市name
    private String descript;

    //城市拼音
    private String cityHYPY;

    //暂不使用
    private Boolean recommend;

    //暂不使用
    private String color;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getcD() {
        return cD;
    }

    public void setcD(String cD) {
        this.cD = cD;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getCityHYPY() {
        return cityHYPY;
    }

    public void setCityHYPY(String cityHYPY) {
        this.cityHYPY = cityHYPY;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "City{" +
                "stateCode='" + stateCode + '\'' +
                ", cD='" + cD + '\'' +
                ", descript='" + descript + '\'' +
                ", cityHYPY='" + cityHYPY + '\'' +
                ", recommend=" + recommend +
                ", color='" + color + '\'' +
                '}';
    }
}
