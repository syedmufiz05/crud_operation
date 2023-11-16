package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "access_logs")
@Data
public class AccessLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @UpdateTimestamp
    @Column(name = "access_date_time", nullable = false)
    private Date accessDateTime;
    @Column(name = "req_payload", columnDefinition = "JSON")
    private String reqPayload;

    @Column(name = "response_payload")
    private String responsePayload;

    @Column(name = "authtoken")
    private String authToken;

    public void setReqPayload(String reqPayload) {
        try {
            this.reqPayload = new ObjectMapper().writeValueAsString(reqPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
