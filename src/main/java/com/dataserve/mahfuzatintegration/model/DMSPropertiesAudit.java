package com.dataserve.mahfuzatintegration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DMS_PROPERTIES_AUDIT")
@Getter
@Setter
@NoArgsConstructor
public class DMSPropertiesAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "PROPERTY_NAME", nullable = false)
    private String propertyName;
    @Column(name = "PROPERTY_VALUE", nullable = false)
    private String propertyValue;
    @ManyToOne
    @JoinColumn(name = "DMS_AUDIT_ID")
    private DMSAudit dmsAudit;

    public DMSPropertiesAudit(String propertyName, String propertyValue, DMSAudit dmsAudit) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.dmsAudit = dmsAudit;
    }


}
