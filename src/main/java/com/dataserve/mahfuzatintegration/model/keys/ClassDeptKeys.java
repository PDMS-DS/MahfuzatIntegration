package com.dataserve.mahfuzatintegration.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClassDeptKeys implements Serializable {

	
	@Column(name = "CLASSIFICATION_ID")
    Long classificationId;

    @Column(name = "DEPT_ID")
    Long deptId;


    public ClassDeptKeys() {
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDeptKeys that = (ClassDeptKeys) o;
        return Objects.equals(classificationId, that.classificationId) && Objects.equals(deptId, that.deptId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classificationId, deptId  );
        }
}
