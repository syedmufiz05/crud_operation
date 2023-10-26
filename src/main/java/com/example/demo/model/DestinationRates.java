package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "destination_rates")
@Data
public class DestinationRates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Destination destination;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rates_tag", referencedColumnName = "id")
    private Rates rates;
}
