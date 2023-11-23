package com.ssafy.wao.model.dto;

import java.sql.Timestamp;

public class LogDto {
    private int logNo;
    private int userNo;
    private String nickname;
    private String imgPath;
    private Timestamp sttTime;
    private Timestamp endTime;
    private int distance;
    private int runningCnt;


    public int getLogNo() {
        return logNo;
    }

    public void setLogNo(int logNo) {
        this.logNo = logNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Timestamp getSttTime() {
        return sttTime;
    }

    public void setSttTime(Timestamp sttTime) {
        this.sttTime = sttTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getRunningCnt() {
        return runningCnt;
    }

    public void setRunningCnt(int runningCnt) {
        this.runningCnt = runningCnt;
    }

    @Override
    public String toString() {
        return "LogDto{" +
                "logNo=" + logNo +
                ", userNo=" + userNo +
                ", nickname='" + nickname + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", sttTime=" + sttTime +
                ", endTime=" + endTime +
                ", distance=" + distance +
                ", runningCnt=" + runningCnt +
                '}';
    }
}
