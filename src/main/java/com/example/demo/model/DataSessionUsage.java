package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "data_session_usage")
@Data
public class DataSessionUsage {
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

    @Column(name = "framed_ip")
    private String framedIp;

    @Column(name = "location_info")
    private String locationInfo;

    @Column(name = "sgsn_address")
    private Boolean sgsnAddress;

    @Column(name = "called_station_id")
    private String calledStationId;

    @Column(name = "session_state")
    private Boolean sessionState;

    @Column(name = "session_start_ts")
    private Boolean sessionStartTs;

    @Column(name = "session_end_ts")
    private Boolean sessionEndTs;

    @Column(name = "total_octates")
    private Long totalOctates;

    @Column(name = "bitrate")
    private Long bitrate;

    @Column(name = "total_input_octets")
    private Long totalInputOctets;

    @Column(name = "total_output_octets")
    private Long totalOutputOctets;

    @Column(name = "session_status")
    private Boolean sessionStatus;
}

