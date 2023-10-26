package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rating_plan")
@Data
public class RatingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_rates_id", referencedColumnName = "id")
    private DestinationRates destinationRates;
}
