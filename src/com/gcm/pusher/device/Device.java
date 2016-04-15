package com.gcm.pusher.device;

import com.gcm.pusher.Pusher;
import it.algos.webbase.web.entity.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity per un Device
 */
@Entity
public class Device extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // device id
    @NotEmpty
    private String deviceId="";

    // username
    @NotEmpty
    private String name="";

    // model
    @NotEmpty
    private String model="";

    // token
    @NotEmpty
    @Column(columnDefinition = "text")
    private String token="";


    /**
     * Costruttore senza argomenti
     * Necessario per le specifiche JavaBean
     */
    public Device() {
    }// end of constructor

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void sendPush(String msg){
        Pusher.push(this, msg);
    }

}// end of domain class
