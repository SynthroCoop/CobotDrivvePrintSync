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
import coop.synthro.utils.PropertyReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author thorsten
 */
public class CobotSubscriptionManager {

    String syncDomain="cobotsync.synthro.coop";
    String cobotSubscriptionUrl="https://coworking-m1.cobot.me/api/subscriptions";
    String callbackApplicationPath="/drivveprint/cobotCallback/";

    public CobotSubscriptionManager() {
        syncDomain = PropertyReader.getProperty("syncDomain");
        cobotSubscriptionUrl= PropertyReader.getProperty("cobotSubscriptionUrl");
        callbackApplicationPath=PropertyReader.getProperty("callbackApplicationPath");
    }

    public void initializeSubscriptions() {

        //Check if we already have subscriptions
        EventSubscriptionList subs = listCobotSubscriptions();
        if (subs != null) {

            //Now check if we have subscriptions for new members and canceled members
            //If not we subscribe.
            EventSubscription newMemberSub = subs.getEventSubscriptions().stream()
                    .filter((s) -> s.getEvent().equalsIgnoreCase("confirmed_membership"))
                    .findFirst()
                    .orElse(null);
            if (newMemberSub == null) {
                subscribeForNewMember();
            }
            EventSubscription canceledMemberSub = subs.getEventSubscriptions().stream()
                    .filter((s) -> s.getEvent().equalsIgnoreCase("canceled_membership"))
                    .findFirst()
                    .orElse(null);
            if (canceledMemberSub == null) {
                subscribeForCanceledMember();
            }
        }
    }

    public void removeAllSubscriptions() {

        //Check if we still have subscriptions
        EventSubscriptionList subs = listCobotSubscriptions();
        if (subs == null || subs.getEventSubscriptions().isEmpty()) {
            //If we have no subscriptions, we are done
            return;
        } else {
            //Unsubscribe all
            for (EventSubscription sub : subs.getEventSubscriptions()) {
                unsubscribeById(sub.getId());
            }
        }
    }

    private EventSubscriptionList listCobotSubscriptions() {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(cobotSubscriptionUrl);

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

    private void subscribeForNewMember() {

        try {
            Client client = Client.create();

            WebResource webResourcePost = client.resource(cobotSubscriptionUrl);

            JSONObject obj = new JSONObject();
            obj.put("event", "confirmed_membership");
            obj.put("callback_url", "https://" + syncDomain + callbackApplicationPath+ "created_membership");

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

    private void subscribeForCanceledMember() {

        try {
            Client client = Client.create();

            WebResource webResourcePost = client.resource(cobotSubscriptionUrl);

            JSONObject obj = new JSONObject();
            obj.put("event", "canceled_membership");
            obj.put("callback_url", "https://" + syncDomain + callbackApplicationPath+ "canceled_membership");

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

    private void unsubscribeById(String subscriptionId) {
        try {
            Client client = Client.create();

            WebResource webResourcePost = client.resource(cobotSubscriptionUrl+"/" + subscriptionId);

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
