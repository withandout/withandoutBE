package com.ssafy.wao.model.dto;

public class UserDto {
    // imgPath, imgName default 주세요...

    private int userNo = -1;
    private String userId = "";
    private String password = "";
    private String nickname = "";
    private String region = "";
    private String gender = "";
    private int age = -1;
    private int isAuthorized = 0;
    private String content = "";
    private String imgPath = "";
    private String imgName = "";

    public String getContent() { return content; }

    public void setContent(String content) {this.content = content; }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIsAuthorized() {
      return isAuthorized;
    }

    public void setIsAuthorized(int isAuthorized) {
      this.isAuthorized = isAuthorized; 
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

    @Override
    public String toString() {
        return "UserDto{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", isAuthorized=" + isAuthorized +
                ", content='" + content + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
