/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.member.model;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Administrator
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class Parsed_Timespans {
     
    private String from_hour;

    private String from_minute;

    private String to_hour;

    private String to_minute;
    
    private String duration_hours;
    
    private String duration_minutes;
    
    private String[] weekdays;

    /**
     * @return the from_hour
     */
    public String getFrom_hour() {
        return from_hour;
    }

    /**
     * @param from_hour the from_hour to set
     */
    public void setFrom_hour(String from_hour) {
        this.from_hour = from_hour;
    }

    /**
     * @return the from_minute
     */
    public String getFrom_minute() {
        return from_minute;
    }

    /**
     * @param from_minute the from_minute to set
     */
    public void setFrom_minute(String from_minute) {
        this.from_minute = from_minute;
    }

    /**
     * @return the to_hour
     */
    public String getTo_hour() {
        return to_hour;
    }

    /**
     * @param to_hour the to_hour to set
     */
    public void setTo_hour(String to_hour) {
        this.to_hour = to_hour;
    }

    /**
     * @return the to_minute
     */
    public String getTo_minute() {
        return to_minute;
    }

    /**
     * @param to_minute the to_minute to set
     */
    public void setTo_minute(String to_minute) {
        this.to_minute = to_minute;
    }

    /**
     * @return the duration_hours
     */
    public String getDuration_hours() {
        return duration_hours;
    }

    /**
     * @param duration_hours the duration_hours to set
     */
    public void setDuration_hours(String duration_hours) {
        this.duration_hours = duration_hours;
    }

    /**
     * @return the duration_minutes
     */
    public String getDuration_minutes() {
        return duration_minutes;
    }

    /**
     * @param duration_minutes the duration_minutes to set
     */
    public void setDuration_minutes(String duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    /**
     * @return the weekdays
     */
    public String[] getWeekdays() {
        return weekdays;
    }

    /**
     * @param weekdays the weekdays to set
     */
    public void setWeekdays(String[] weekdays) {
        this.weekdays = weekdays;
    }
    
}
    

