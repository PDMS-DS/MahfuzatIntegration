package com.dataserve.archivemanagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DMS_FILES")
@Getter
@Setter
@NoArgsConstructor
public class DmsFiles {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "FILE_ID", nullable = false)
	private Long fileId;
	
	@Column(name = "DOCUMENT_ID", nullable = false)
	private String documentId;
	

	@Column(name = "DOCUMENT_CLASS", nullable = false)
	private String documentClass;
	
	@JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name = "DEPT_ID")
	private Departments departments;
	
	@Column(name = "DOCUMENT_NAME", nullable = false)
	private String documentName;
	
	
	@Column(name = "NO_PAGES", nullable = true)
	Long noPages;
	

	
}
 