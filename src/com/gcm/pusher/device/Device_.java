package com.gcm.pusher.device;

import it.algos.webbase.web.entity.BaseEntity_;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Device.class)
public class Device_ extends BaseEntity_ {
    public static volatile SingularAttribute<Device, String> deviceId;
    public static volatile SingularAttribute<Device, String> name;
    public static volatile SingularAttribute<Device, String> model;
    public static volatile SingularAttribute<Device, String> token;
}// end of entity class
