package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "auc_record")
@Data
public class Auc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auc_id")
    private Integer aucId;

    @Column(name = "imsi")
    private String imsi;

    @Column(name = "ki")
    private String ki;

    @Column(name = "opc")
    private String opc;

    @Column(name = "a3a8_version")
    private Integer a3a8Version;

    @Column(name = "status")
    private String status;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "access_id", referencedColumnName = "idaccess_logs_id")
    @JsonProperty("access_id")
    private AccessLogs accessLogs;

}
