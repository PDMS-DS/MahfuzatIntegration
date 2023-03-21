package com.dataserve.archivemanagement.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS_GROUPS")
@Getter
@Setter
@NoArgsConstructor
public class UsersGroups {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupsId;


}
