package com.example.SpringBootForArchiveSch.model.dto;

public class DepartmentsDto {
    private Long deptId;
    private String deptArName;
    private String deptEnName;
    private boolean enabled;
    private Long deptCode;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptArName() {
        return deptArName;
    }

    public void setDeptArName(String deptArName) {
        this.deptArName = deptArName;
    }

    public String getDeptEnName() {
        return deptEnName;
    }

    public void setDeptEnName(String deptEnName) {
        this.deptEnName = deptEnName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Long deptCode) {
        this.deptCode = deptCode;
    }
}
