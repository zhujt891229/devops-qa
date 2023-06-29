package com.manage.project.param;

import javax.validation.constraints.NotBlank;

public class LoginParam {
    @NotBlank(message="userName must be not blank")
    private String userName;

    private String phoneNumber;
    private String captcha;

    @NotBlank(message = "password must be not blank")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
