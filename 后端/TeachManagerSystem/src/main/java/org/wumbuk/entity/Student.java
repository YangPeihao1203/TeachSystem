package org.wumbuk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Student {
    private Integer sid;

    private String sname;

    private Integer ssex;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sbirth;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sentrytime;

    private Integer sdepature;

    private String avatar;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAvatar() {

        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Integer getSsex() {
        return ssex;
    }

    public void setSsex(Integer ssex) {
        this.ssex = ssex;
    }

    public Date getSbirth() {
        return sbirth;
    }

    public void setSbirth(Date sbirth) {
        this.sbirth = sbirth;
    }

    public Date getSentrytime() {
        return sentrytime;
    }

    public void setSentrytime(Date sentrytime) {
        this.sentrytime = sentrytime;
    }

    public Integer getSdepature() {
        return sdepature;
    }

    public void setSdepature(Integer sdepature) {
        this.sdepature = sdepature;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", ssex=" + ssex +
                ", sbirth=" + sbirth +
                ", sentrytime=" + sentrytime +
                ", sdepature=" + sdepature +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}





