package com.gcm.pusher.log;

import it.algos.webbase.web.entity.BaseEntity_;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Log.class)
public class Log_ extends BaseEntity_ {
    public static volatile SingularAttribute<Log, Date> timestamp;
    public static volatile SingularAttribute<Log, String> level;
    public static volatile SingularAttribute<Log, String> tag;
    public static volatile SingularAttribute<Log, String> message;
}// end of entity class

