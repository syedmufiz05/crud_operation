package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Sim_Management")
@Data
public class SimMgmt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sim_id")
    private Integer id;

    @Column(name = "imsi")
    private String imsi;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "batch_date")
    @CreationTimestamp
    private Date batchDate;

    @Column(name = "allocation_date")
    @UpdateTimestamp
    private Date allocationDate;

    @Column(name = "sim_type")
    private String simType;

    @Column(name = "key_id")
    private Integer keyId;

    @Column(name = "auth_id")
    private Integer authId;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "status")
    private Boolean status;
}
