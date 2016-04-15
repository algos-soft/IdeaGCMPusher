package com.gcm.pusher.log;

import it.algos.webbase.web.entity.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Entity per le righe di log
 */
@Entity
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // log timestamp
    private Date timestamp;

    // log level I(nfo) D(ebug) W(arning) E(rror)
    private String level="";

    // log tag
    private String tag="";

    // log message
    private String message="";


    public Log() {
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void i(String tag, String msg){
        createLog("I",tag, msg);
    }
    public static void d(String tag, String msg){
        createLog("D",tag, msg);
    }
    public static void w(String tag, String msg){
        createLog("W",tag, msg);
    }
    public static void e(String tag, String msg){
        createLog("E",tag, msg);
    }

    private static void createLog(String level, String tag, String msg){
        Log log = new Log();
        log.setTimestamp(new Date(System.currentTimeMillis()));
        log.setLevel(level);
        log.setTag(tag);
        log.setMessage(msg);
        log.save();
    }

}
