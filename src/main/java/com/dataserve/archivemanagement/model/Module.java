package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "MODULES")
public class Module {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MODULE_ID", nullable = false)
    private Long moduleId;

    @Column(name = "MODULE_NAME_AR", nullable = true)
    private String moduleNameAr;

    @Column(name = "MODULE_NAME_EN", nullable = true)
    private String moduleNameEn;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL , mappedBy = "module")
    private Set<Permissions> permissionsM ;

    public Module() {
    }

    public Module(Long moduleId, String moduleNameAr, String moduleNameEn) {
        this.moduleId = moduleId;
            this.moduleNameAr = moduleNameAr;
        this.moduleNameEn = moduleNameEn;
    }


    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }


    public String getModuleNameAr() {
        return moduleNameAr;
    }

    public void setModuleNameAr(String moduleNameAr) {
        this.moduleNameAr = moduleNameAr;
    }


    public String getModuleNameEn() {
        return moduleNameEn;
    }

    public void setModuleNameEn(String moduleNameEn) {
        this.moduleNameEn = moduleNameEn;
    }


    @JsonManagedReference
    public Set<Permissions> getPermissionsM() {
        return permissionsM;
    }

    public void setPermissionsM(Set<Permissions> permissions) {
        this.permissionsM = permissions;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", moduleNameAr='" + moduleNameAr + '\'' +
                ", moduleNameEn='" + moduleNameEn + '\'' +
                '}';
    }
}
