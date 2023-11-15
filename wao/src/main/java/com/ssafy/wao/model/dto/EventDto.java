package com.ssafy.wao.model.dto;

// 변환 필요할 수도 ?
import java.sql.Date;

public class EventDto {
    private int eventNo;
    private Date sttTime;
    private Date endTime;
    private int partyNo;

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

    @Override
    public String toString() {
        return "EventDto{" +
                "eventNo=" + eventNo +
                ", sttTime=" + sttTime +
                ", endTime=" + endTime +
                ", partyNo=" + partyNo +
                '}';
    }
}
