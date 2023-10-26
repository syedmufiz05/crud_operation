package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "destination")
@Data
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "active")
    private Boolean active;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "access_id", referencedColumnName = "idaccess_logs_id")
    private AccessLogs accessLogs;
}
