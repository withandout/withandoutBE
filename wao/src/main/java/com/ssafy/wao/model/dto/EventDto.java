package com.ssafy.wao.model.dto;

// 변환 필요할 수도 ?
import java.sql.Date;

public class EventDto {
    private int eventNo;
    private Date sttTime;
    private Date endTime;
    private int partyNo;
    private int userNo;
    private String content;
    private int isApplied;
    private int noParticipant;

    public int getEventNo() {
        return eventNo;
    }

    public void setEventNo(int eventNo) {
        this.eventNo = eventNo;
    }

    public Date getSttTime() {
        return sttTime;
    }

    public void setSttTime(Date sttTime) {
        this.sttTime = sttTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getPartyNo() {
        return partyNo;
    }

    public void setPartyNo(int partyNo) {
        this.partyNo = partyNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsApplied() {
        return isApplied;
    }

    public void setIsApplied(int isApplied) {
        this.isApplied = isApplied;
    }

    public int getNoParticipant() {
        return noParticipant;
    }

    public void setNoParticipant(int noParticipant) {
        this.noParticipant = noParticipant;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "eventNo=" + eventNo +
                ", sttTime=" + sttTime +
                ", endTime=" + endTime +
                ", partyNo=" + partyNo +
                ", userNo=" + userNo +
                ", content='" + content + '\'' +
                ", isApplied=" + isApplied +
                ", noParticipant=" + noParticipant +
                '}';
    }
}
