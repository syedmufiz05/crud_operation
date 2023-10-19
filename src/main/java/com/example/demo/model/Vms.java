package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vms_record")
public class Vms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vms_id")
    private Integer vmsId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "system_id")
    private Integer systemId;

    @Column(name = "mailbox_id")
    private Integer mailboxId;

    @Column(name = "register_flag")
    private Boolean registerFlag;

    @Column(name = "active_flag")
    private Boolean activeFlag;

    @Column(name = "locked_flag")
    private Boolean lockedFlag;

    @Column(name = "language")
    private Integer language;

    @Column(name = "temporary_greeting")
    private Boolean temporaryGreeting;

    @Column(name = "greeting_type_system")
    private String greetingTypeSystem;

    @Column(name = "password_flag")
    private Boolean passwordFlag;

    @Column(name = "callback_flag")
    private Boolean callbackFlag;

    @Column(name = "cli_flag")
    private Boolean cliFlag;

    @Column(name = "password")
    private String password;

    @Column(name = "callback_timeout")
    private String callbackTimeout;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "access_id", referencedColumnName = "idaccess_logs_id")
    @JsonProperty("access_id")
    private AccessLogs accessLogs;

}
