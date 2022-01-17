package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class UsersGroups {

    private int userId;
    private int groupsId;

    public UsersGroups() {
    }

    public UsersGroups(int userId, int groupsId) {
        this.userId = userId;
        this.groupsId = groupsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(int groupsId) {
        this.groupsId = groupsId;
    }

    @Override
    public String toString() {
        return "UsersGroups{" +
                "userId=" + userId +
                ", groupsId=" + groupsId +
                '}';
    }
}
