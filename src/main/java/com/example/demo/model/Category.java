package com.example.demo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", columnDefinition = "JSON")
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "access_id", referencedColumnName = "id")
    private AccessLogs accessLogs;

    public void setName(String name) {
        try {
            this.name = new ObjectMapper().writeValueAsString(name);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
