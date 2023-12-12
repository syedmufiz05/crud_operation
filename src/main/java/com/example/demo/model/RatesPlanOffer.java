package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rates_plan_offer")
@Data
public class RatesPlanOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "period")
    private Integer period;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
