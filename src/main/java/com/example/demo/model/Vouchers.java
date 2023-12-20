package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vouchers")
@Data
public class Vouchers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "activated_date")
    private Date activatedDate;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "batch_id")
    private String batchId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "currency")
    private String currency;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "group_code")
    private String groupCode;

    @Column(name = "instruction_id", unique = true)
    private String instructionId;

    @Column(name = "payee_functional_id")
    private String payeeFunctionalId;

    @Column(name = "registering_institution_id")
    private String registeringInstitutionId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "serial_no", unique = true)
    private String serialNo;

    @Column(name = "status")
    private String status;

    @Column(name = "voucher_no", unique = true)
    private String voucherNo;
}
