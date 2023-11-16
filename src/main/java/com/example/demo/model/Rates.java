package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rates")
@Data
public class Rates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dest_name")
    private String destName;

    @Column(name = "dest_type")
    private String destType;

    @Column(name = "rates_index")
    private Integer ratesIndex;

    @Column(name = "description")
    private String description;

    @Column(name = "is_rates_active")
    private Boolean isRatesActive;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "access_id", referencedColumnName = "id")
    private AccessLogs accessLogs;
}
