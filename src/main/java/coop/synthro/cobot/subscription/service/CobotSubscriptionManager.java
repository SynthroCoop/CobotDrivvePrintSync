/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.subscription.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import coop.synthro.cobot.subscription.model.EventSubscription;
import coop.synthro.cobot.subscription.model.EventSubscriptionList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author thorsten
 */
public class CobotSubscriptionManager {
    
    public void InitializeSubscriptions() {

        //Check if we already have subscriptions
        EventSubscriptionList subs = ListCobotSubscriptions();
        if (subs != null) {

            //Now check if we have subscriptions for new members and canceled members
            //If not we subscribe.
            EventSubscription newMemberSub = subs.getEventSubscriptions().stream()
                    .filter((s) -> s.getEvent().equalsIgnoreCase("confirmed_membership"))
                    .findFirst()
                    .orElse(null);
            if (newMemberSub == null) {
                SubscribeForNewMember();
            }
            EventSubscription canceledMemberSub = subs.getEventSubscriptions().stream()
                    .filter((s) -> s.getEvent().equalsIgnoreCase("canceled_membership"))
                    .findFirst()
                    .orElse(null);
            if (canceledMemberSub == null) {
                SubscribeForCanceledMember();
            }
        }
    }
    
    public void RemoveAllSubscriptions() {

        //Check if we still have subscriptions
        EventSubscriptionList subs = ListCobotSubscriptions();
        if (subs == null || subs.getEventSubscriptions().isEmpty()) {
            //If we have no subscriptions, we are done
            return;
        } else {
            //Unsubscribe all
            for (EventSubscription sub : subs.getEventSubscriptions()) {
                UnsubscribeById(sub.getId());
            }
        }
    }
    
    private EventSubscriptionList ListCobotSubscriptions() {
        try {
            Client client = Client.create();
            
            WebResource webResource = client.resource("https://coworking-m1.cobot.me/api/subscriptions");
            
            ClientResponse response = webResource.type("application/json").get(ClientResponse.class
            );
            EventSubscriptionList subs = response.getEntity(EventSubscriptionList.class
            );
            
            if (response.getStatus() != 200) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            
            return subs;
            
        } catch (UniformInterfaceException | ClientHandlerException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void SubscribeForNewMember() {
        
        try {
            Client client = Client.create();
            
            WebResource webResourcePost = client.resource("https://coworking-m1.cobot.me/api/subscriptions");
            
            JSONObject obj = new JSONObject();
            obj.put("event", "confirmed_membership");
            obj.put("callback_url", "https://usersync.synthro.coop/drivveprint/cobotCallback/created_membership");
            
            ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class, obj);
            
            EventSubscription sub = response.getEntity(EventSubscription.class
            );
            
            if (response.getStatus() == 422) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Invalid parameters send to cobot event subscription : " + response.getStatus());
                
            }
            if (response.getStatus() != 201) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            
        } catch (JSONException | UniformInterfaceException | ClientHandlerException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SubscribeForCanceledMember() {
        
        try {
            Client client = Client.create();
            
            WebResource webResourcePost = client.resource("https://coworking-m1.cobot.me/api/subscriptions");
            
            JSONObject obj = new JSONObject();
            obj.put("event", "canceled_membership");
            obj.put("callback_url", "https://usersync.synthro.coop/drivveprint/cobotCallback/canceled_membership");
            
            ClientResponse response = webResourcePost.type("application/json").post(ClientResponse.class, obj);
            
            EventSubscription sub = response.getEntity(EventSubscription.class
            );
            
            if (response.getStatus() == 422) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Invalid parameters send to cobot event subscription : " + response.getStatus());
                
            }
            if (response.getStatus() != 201) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            
        } catch (JSONException | UniformInterfaceException | ClientHandlerException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void UnsubscribeById(String subscriptionId) {
        try {
            Client client = Client.create();
            
            WebResource webResourcePost = client.resource("https://coworking-m1.cobot.me/api/subscriptions/" + subscriptionId);
            
            ClientResponse response = webResourcePost.type("application/json").delete(ClientResponse.class
            );
            
            if (response.getStatus() != 204) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Unsubscribe from webhook failed : HTTP error code : " + response.getStatus());
            }

            
        } catch (UniformInterfaceException | ClientHandlerException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
