package org.wumbuk.entity;

public class Course {
    private Integer cid;

    private String cname;

    private Integer ctime;

    private String cplace;

    private Integer cweek;

    private Integer ctype;

    private Integer cvalue;

    private Integer did;

    private Float score;

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    //任课老师编号
    private  Integer tid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public String getCplace() {
        return cplace;
    }

    public void setCplace(String cplace) {
        this.cplace = cplace == null ? null : cplace.trim();
    }

    public Integer getCweek() {
        return cweek;
    }

    public void setCweek(Integer cweek) {
        this.cweek = cweek;
    }

    public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public Integer getCvalue() {
        return cvalue;
    }

    public void setCvalue(Integer cvalue) {
        this.cvalue = cvalue;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }



    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", ctime=" + ctime +
                ", cplace='" + cplace + '\'' +
                ", cweek=" + cweek +
                ", ctype=" + ctype +
                ", cvalue=" + cvalue +
                ", did=" + did +
                ", score=" + score +
                ", tid=" + tid +
                '}';
    }
}