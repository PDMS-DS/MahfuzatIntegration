package com.dataserve.archivemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MOBILE_AUDIT_PARAMETERS")
@Getter
@Setter
@NoArgsConstructor
public class MobileAuditParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_PARAM_ID", nullable = false)
    private Long id;

    @Column(name = "PARAMETER_NAME")
    private String paramName;

    @Column(name = "PARAMETER_VALUE")
    private String paramValue;

    @ManyToOne
    @JoinColumn(name = "MOBILE_AUDIT_ID")
    private MobileAudit mobileAudit;

    public MobileAuditParameter(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;

    }

}