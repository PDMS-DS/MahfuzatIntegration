package com.dataserve.mahfuzatintegration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "module")
//	@JsonBackReference
//	private Set<Permissions> permissionsM;

}
