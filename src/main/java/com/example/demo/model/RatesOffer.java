package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rates_offer")
@Data
public class RatesOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Integer price;

    @Column(name = "price_type")
    private String priceType;

    @Column(name = "period")
    private Integer period;

    @Column(name = "description")
    private String description;

}
