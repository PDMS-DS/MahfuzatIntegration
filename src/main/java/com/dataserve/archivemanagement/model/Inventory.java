package com.dataserve.archivemanagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INVENTORY")
@Getter
@Setter
@NoArgsConstructor
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INVENTORY_ID", nullable = false)
	private Long inventoryId;

	@Column(name = "NAME_AR", nullable = false)
	private String nameAr;

	@Column(name = "NAME_EN", nullable = true)
	private String nameEr;

	@Column(name = "CAPACITY", nullable = true)
	private long capacity;

	@Column(name = "CENTER_ID", nullable = true, insertable = false, updatable = false)
	private long centerId;

	@Column(name = "SERIAL", nullable = true)
	private long serial;

	@JsonManagedReference
	@OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Line> line;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CENTER_ID", nullable = true)
	private StorageCenter storageCenter;

}
