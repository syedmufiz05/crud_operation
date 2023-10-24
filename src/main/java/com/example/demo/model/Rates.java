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

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "rates_index")
    private Integer ratesIndex;

    @Column(name = "description")
    private String description;

    @Column(name = "is_rates_active")
    private Boolean isRatesActive;
}
