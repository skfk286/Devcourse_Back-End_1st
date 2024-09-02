package com.grepp.boot.model.dto;

public class MemberDTO {
    private String userid;
    private String userpw;
    private String usernickname;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "userid='" + userid + '\'' +
                ", userpw='" + userpw + '\'' +
                ", userNickname='" + usernickname + '\'' +
                '}';
    }
}
