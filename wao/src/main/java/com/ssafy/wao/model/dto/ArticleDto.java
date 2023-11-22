package com.ssafy.wao.model.dto;

// 변환 필요할 수도 ?
import java.sql.Timestamp;

public class ArticleDto {
    private int userNo;
    private int partyNo;
    private int articleNo;
    private String nickname;
    private String imgPath;
    private String imgName;
    private String content;
    private Timestamp regDate;

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getPartyNo() {
        return partyNo;
    }

    public void setPartyNo(int partyNo) {
        this.partyNo = partyNo;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
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

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "userNo=" + userNo +
                ", partyNo=" + partyNo +
                ", articleNo=" + articleNo +
                ", nickname='" + nickname + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", imgName='" + imgName + '\'' +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}