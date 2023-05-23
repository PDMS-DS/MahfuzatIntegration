package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERMISSIONS")
@Getter
@Setter
@NoArgsConstructor
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PermissionID")
    private Long permissionID;

    @Column(name = "PermissionArName", nullable = true)
    private String permissionArName;

    @Column(name = "PermissionEnName", nullable = true)
    private String permissionEnName;

    @Column(name = "PermissionDescription", nullable = true)
    private String permissionDescription;

    @Column(name = "Enabled", nullable = true)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODULE_ID")
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Action_TYPE_ID")
    private ActionTypes actionTypes;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
			CascadeType.ALL }, mappedBy = "permissionsG")
	Set<Groups> groups = new HashSet<>();

}
