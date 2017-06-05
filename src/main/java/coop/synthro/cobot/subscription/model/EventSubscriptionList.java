/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.subscription.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thorsten
 */
@XmlRootElement
public class EventSubscriptionList {
    
    private List<EventSubscription> eventSubscriptions;

    /**
     * @return the eventSubscriptions
     */
    public List<EventSubscription> getEventSubscriptions() {
        return eventSubscriptions;
    }

    /**
     * @param eventSubscriptions the eventSubscriptions to set
     */
    public void setEventSubscriptions(List<EventSubscription> eventSubscriptions) {
        this.eventSubscriptions = eventSubscriptions;
    }
    
}
