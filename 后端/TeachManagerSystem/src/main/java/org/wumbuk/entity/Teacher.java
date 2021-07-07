package org.wumbuk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Teacher {
    private Integer tid;

    private String tname;

    private Integer tsex;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tbirth;

    private String tedu;

    private String ttitle;



    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date tentrytime;

    private Integer tdepature;



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


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Integer getTsex() {
        return tsex;
    }

    public void setTsex(Integer tsex) {
        this.tsex = tsex;
    }

    public Date getTbirth() {
        return tbirth;
    }

    public void setTbirth(Date tbirth) {
        this.tbirth = tbirth;
    }

    public String getTedu() {
        return tedu;
    }

    public void setTedu(String tedu) {
        this.tedu = tedu == null ? null : tedu.trim();
    }

    public String getTtitle() {
        return ttitle;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle == null ? null : ttitle.trim();
    }

    public Date getTentrytime() {
        return tentrytime;
    }

    public void setTentrytime(Date tentrytime) {
        this.tentrytime = tentrytime;
    }

    public Integer getTdepature() {
        return tdepature;
    }

    public void setTdepature(Integer tdepature) {
        this.tdepature = tdepature;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tsex=" + tsex +
                ", tbirth=" + tbirth +
                ", tedu='" + tedu + '\'' +
                ", ttitle='" + ttitle + '\'' +
                ", avatar='" + avatar + '\'' +
                ", tentrytime=" + tentrytime +
                ", tdepature=" + tdepature +
                ", phone='" + phone + '\'' +
                '}';
    }
}