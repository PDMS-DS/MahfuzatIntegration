package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "MODULES")
@Getter
@Setter
@NoArgsConstructor
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MODULE_ID", nullable = false)
	private Long moduleId;

	@Column(name = "MODULE_NAME_AR", nullable = true)
	private String moduleNameAr;

	@Column(name = "MODULE_NAME_EN", nullable = true)
	private String moduleNameEn;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "module")
	private Set<Permissions> permissionsM;

}
