package com.ssafy.wao.model.dto;

public class PartyDto {
    private int partyNo;
    private String name;
    private String sports;
    private String content;
    private String imgPath;
    private String imgName;
    private int leadUserNo;

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

    public int getLeadUserNo() {
        return leadUserNo;
    }

    public void setLeadUserNo(int leadUserNo) {
        this.leadUserNo = leadUserNo;
    }

    @Override
    public String toString() {
        return "PartyDto{" +
                "partyNo=" + partyNo +
                ", name='" + name + '\'' +
                ", sports='" + sports + '\'' +
                ", content='" + content + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", imgName='" + imgName + '\'' +
                ", leadUserNo=" + leadUserNo +
                '}';
    }
}
