package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS_DEPT")
public class UsersDept {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)

    private Long userId;
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DEPT_ID", nullable = false)
    private Long deptId;



    public UsersDept() {
    }


    @Override
    public String toString() {
        return "UsersDept{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                '}';
    }
}
