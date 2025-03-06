package com.dataserve.archivemanagement.model;

import com.dataserve.archivemanagement.util.LocalizationUtil;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CLASSIFICTIONS")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "classificationId", "saveType", "classArName", "classEnName", "className", "sympolicName", "parentID", "classCode"
})
public class Classifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASSIFICATION_ID", nullable = false)
    private Long classificationId;

    @Column(name = "SAVE_TYPE")
    private Long saveType;

    @Column(name = "CLASS_AR_NAME", nullable = true)
    private String classArName;

    @Column(name = "CLASS_EN_NAME", nullable = true)
    private String classEnName;

    @Transient // Ensures it is not stored in the DB
    @JsonProperty("className") // Ensures it appears in the JSON response
    private String getClassName() {
        return LocalizationUtil.getLocalizedValue(this.classArName, this.classEnName);
    }

    @Column(name = "SYMPOLIC_NAME", nullable = true)
    private String sympolicName;

    @Column(name = "PARENT_ID", nullable = true)
    private Long parentID;

    @Column(name = "is_fn_added", nullable = true)
    private boolean isFnAdded;

    @Column(name = "CLASS_CODE", nullable = true)
    private String classCode;

    @JsonIgnore
    @OneToMany(mappedBy = "classifications", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClassDept> classDept;

    @JsonBackReference
    @OneToMany(mappedBy = "classifications", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Folder> folder;
}
