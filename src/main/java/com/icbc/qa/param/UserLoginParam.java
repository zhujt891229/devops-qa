package com.icbc.qa.param;

import javax.validation.constraints.NotBlank;

public class UserLoginParam {
    @NotBlank(message="userId must be not blank")
    private String userId;

    @NotBlank(message = "password must be not blank")
    private String password;

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
}
