package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory_management")
@Data
public class InventoryMgmt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inventory_id")
    private Integer id;

    @Column(name = "imsi")
    private String imsi;

    @Column(name = "p_imsi")
    private String pImsi;

    @Column(name = "batch_id")
    private Integer batchId;

    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "prov_status")
    private Boolean provStatus;

    @CreationTimestamp
    @Column(name = "allocation_date")
    private Date allocationDate;

    @UpdateTimestamp
    @Column(name = "activation_date")
    private Date activationDate;
}
