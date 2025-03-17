package com.dataserve.mahfuzatintegration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MOBILE_AUDIT")
@Getter
@Setter
@NoArgsConstructor
public class MobileAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOBILE_AUDIT_ID", nullable = false)
    private Long id;

    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "RESULT")
    @Lob
    private String result;
    @Column(name = "METHOD_NAME")
    private String methodName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private AppUsers appUser;
    @OneToMany(mappedBy = "mobileAudit")
    private List<MobileAuditParameter> auditParameterList;


    public MobileAudit(Long id) {
        this.id = id;
    }
}