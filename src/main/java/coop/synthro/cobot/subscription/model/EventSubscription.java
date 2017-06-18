/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thorsten
 */
package coop.synthro.cobot.subscription.model;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventSubscription {

    private String id;
    private String event;
    private String url;
    private Boolean active;
    private String callback_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callbackUrl) {
        this.callback_url = callbackUrl;
    }



}
