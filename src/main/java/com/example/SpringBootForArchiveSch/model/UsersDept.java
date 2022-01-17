package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class UsersDept {

    private int userId;
    private int deptId;

    public UsersDept() {
    }

    public UsersDept(int userId, int deptId) {
        this.userId = userId;
        this.deptId = deptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "UsersDept{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                '}';
    }
}
