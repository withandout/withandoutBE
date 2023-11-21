package com.ssafy.wao.model.dto;

import java.sql.Timestamp;

public class PartyDto {
    private int partyNo;
    private String name;
    private String sports;
    private String region;
    private String content;
    private String imgPath;
    private String imgName;
    private int sizeLimit;
    private int sizeCurrent;
    private int userNo;
    private int isAccepted;
    private Timestamp invitedDate;
    private Timestamp acceptedDate;

    public int getPartyNo() {
        return partyNo;
    }

    public void setPartyNo(int partyNo) {
        this.partyNo = partyNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }

    public void setSizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public int getSizeCurrent() {
        return sizeCurrent;
    }

    public void setSizeCurrent(int sizeCurrent) {
        this.sizeCurrent = sizeCurrent;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(int isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Timestamp getInvitedDate() {
        return invitedDate;
    }

    public void setInvitedDate(Timestamp invitedDate) {
        this.invitedDate = invitedDate;
    }

    public Timestamp getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Timestamp acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    @Override
    public String toString() {
        return "PartyDto{" +
                "partyNo=" + partyNo +
                ", name='" + name + '\'' +
                ", sports='" + sports + '\'' +
                ", region='" + region + '\'' +
                ", content='" + content + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", imgName='" + imgName + '\'' +
                ", sizeLimit=" + sizeLimit +
                ", sizeCurrent=" + sizeCurrent +
                ", userNo=" + userNo +
                ", isAccepted=" + isAccepted +
                ", invitedDate=" + invitedDate +
                ", acceptedDate=" + acceptedDate +
                '}';
    }
}
