package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "call_session_usage")
@Data
public class CallSessionUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "peer_session_id")
    private String peerSessionId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "imsi")
    private String imsi;

    @Column(name = "called_msisdn")
    private String calledMsisdn;

    @Column(name = "location_info")
    private String locationInfo;

    @Column(name = "session_state")
    private Boolean sessionState;

    @Column(name = "call_start_ts")
    private Long callStartTs;

    @Column(name = "call_end_ts")
    private Long callEndTs;

    @Column(name = "total_seconds")
    private Long totalSeconds;

    @Column(name = "call_status")
    private Boolean callStatus;
}
