package com.example.airlinefeedback.bean;

public class UserInfo {

    private String name;
    private String phoneNo;
    private String password;
    private String email;

    public UserInfo(String email, String loginPassword) {
        this.email = email;
        this.loginPassword = loginPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    private String loginPassword;


    public UserInfo(String name, String phoneNo, String password) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
