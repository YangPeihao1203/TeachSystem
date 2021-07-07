package org.wumbuk.entity;

public class Coursestudent {
    private Integer cid;

    private Integer sid;

    private Integer type;

    private Float score;

    private Integer tid;

    private String  sname;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Coursestudent{" +
                "cid=" + cid +
                ", sid=" + sid +
                ", type=" + type +
                ", score=" + score +
                ", tid=" + tid +
                ", sname='" + sname + '\'' +
                '}';
    }
}