package com.zjt.qas.model.entity;

import java.time.LocalDateTime;

public class UserInfo {
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private int isDelete;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
//    private String deptId;
//    private String deptName;
    private String phoneNumber;
//    private String roleId;
//    private String roleName;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getDeptId() {
//        return deptId;
//    }
//
//    public void setDeptId(String deptId) {
//        this.deptId = deptId;
//    }
//
//    public String getDeptName() {
//        return deptName;
//    }
//
//    public void setDeptName(String deptName) {
//        this.deptName = deptName;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public String getRoleId() {
//        return roleId;
//    }

//    public void setRoleId(String roleId) {
//        this.roleId = roleId;
//    }

//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
