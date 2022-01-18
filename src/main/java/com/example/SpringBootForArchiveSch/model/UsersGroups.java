package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS_GROUPS")
public class UsersGroups {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupsId;




    public UsersGroups() {
    }


    @Override
    public String toString() {
        return "UsersGroups{" +
                "userId=" + userId +
                ", groupsId=" + groupsId +
                '}';
    }
}
