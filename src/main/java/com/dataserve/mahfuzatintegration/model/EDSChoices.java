package com.dataserve.mahfuzatintegration.model;

import com.dataserve.mahfuzatintegration.model.keys.EDSChoicesCompositeKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EDS_CHOICES")
@Getter
@Setter
public class EDSChoices {
    @EmbeddedId
    private EDSChoicesCompositeKey compositeKey;
    @Column(name = "LISTDISPNAME")
    private String listDisplayName;
    @Column(name = "DISPNAME")
    private String displayName;
    @Column(name = "DEPON")
    private String depon;
    @Column(name = "DEPVALUE")
    private String depValue;
    @Column(name = "orderN")
    private int orderNo;



}
