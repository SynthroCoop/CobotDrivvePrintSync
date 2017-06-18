/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.subscription.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import coop.synthro.cobot.subscription.model.EventSubscription;
import coop.synthro.utils.PropertyReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author thorsten
 */
public class CobotSubscriptionManager {

    String syncDomain = "cobotsync.synthro.coop";
    String cobotSubscriptionUrl = "https://members.coworking-m1.de/api/subscriptions";
    String callbackApplicationPath = "/cobot-print-user-sync/cobotCallback/";

    public CobotSubscriptionManager() {
        syncDomain = PropertyReader.getProperty("syncDomain");
        cobotSubscriptionUrl = PropertyReader.getProperty("cobotSubscriptionUrl");
        callbackApplicationPath = PropertyReader.getProperty("callbackApplicationPath");
    }

    public void initializeSubscriptions() {

        //Check if we already have subscriptions
        List<EventSubscription> subs = listCobotSubscriptions();
        if (subs != null) {
            //Now check if we have subscriptions for new members and canceled members
            //If not we subscribe.
            EventSubscription newMemberSub = subs.stream()
                    .filter((s) -> s.getEvent().equalsIgnoreCase("confirmed_membership"))
                    .findFirst()
                    .orElse(null);
            if (newMemberSub == null) {
                subscribeForNewMember();
            }
            EventSubscription canceledMemberSub = subs.stream()
                    .filter((s) -> s.getEvent().equalsIgnoreCase("canceled_membership"))
                    .findFirst()
                    .orElse(null);
            if (canceledMemberSub == null) {
                subscribeForCanceledMember();
            }
        } else {
            Logger.getLogger(CobotSubscriptionManager.class.getName()).log(Level.INFO, "No subscriptions in Cobot found!");
            //If we have nothing
            subscribeForNewMember();
            subscribeForCanceledMember();
        }
    }

    public void removeAllSubscriptions() {

        //Check if we still have subscriptions
        List<EventSubscription> subs = listCobotSubscriptions();
        if (subs == null || subs.isEmpty()) {
            //If we have no subscriptions, we are done
            return;
        } else {
            //Unsubscribe all
            for (EventSubscription sub : subs) {
                if(sub.getEvent().equals("canceled_membership")||sub.getEvent().equals("confirmed_membership") )
                unsubscribeById(sub.getId());
            }
        }
    }

    public List<EventSubscription> listCobotSubscriptions() {
        try {
            AccessToken token = CobotAuthenticator.authenticate();

            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);

            Logger.getLogger(CobotSubscriptionManager.class.getName()).log(Level.INFO, "App Token= " + token.getAccess_token());

            WebResource webResource = client.resource(cobotSubscriptionUrl);

            ClientResponse response = webResource
                    .type(MediaType.APPLICATION_JSON)
                    .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                    .get(ClientResponse.class);
            //String subString=response.getEntity(String.class);
            List<EventSubscription> subs = response.getEntity(new GenericType<List<EventSubscription>>() {
            });

            if (response.getStatus() != 200) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            Logger.getLogger(CobotSubscriptionManager.class.getName()).log(Level.INFO, "Listed all cobot subscriptions");

            return subs;

        } catch (UniformInterfaceException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void subscribeForNewMember() {

        try {
            AccessToken token = CobotAuthenticator.authenticate();

            Client client = Client.create();

            WebResource webResourcePost = client.resource(cobotSubscriptionUrl);

            JSONObject obj = new JSONObject();
            obj.put("event", "confirmed_membership");
            obj.put("callback_url", "https://" + syncDomain + callbackApplicationPath + "created_membership");

            ClientResponse response = webResourcePost
                    .type("application/json")
                    .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                    .post(ClientResponse.class, obj);

            EventSubscription sub = response.getEntity(EventSubscription.class);

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

        } catch (JSONException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(CobotSubscriptionManager.class.getName()).log(Level.INFO, "Event subscription for new member successful");

    }

    private void subscribeForCanceledMember() {

        try {
            AccessToken token = CobotAuthenticator.authenticate();

            Client client = Client.create();

            WebResource webResourcePost = client.resource(cobotSubscriptionUrl);

            JSONObject obj = new JSONObject();
            obj.put("event", "canceled_membership");
            obj.put("callback_url", "https://" + syncDomain + callbackApplicationPath + "canceled_membership");

            ClientResponse response = webResourcePost
                    .type("application/json")
                    .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                    .post(ClientResponse.class, obj);

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

        } catch (JSONException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(CobotSubscriptionManager.class.getName()).log(Level.INFO, "Event subscription for canceled member successful");

    }

    private void unsubscribeById(String subscriptionId) {
        try {
            AccessToken token = CobotAuthenticator.authenticate();
            Client client = Client.create();

            WebResource webResourcePost = client.resource(cobotSubscriptionUrl + "/" + subscriptionId);

            ClientResponse response = webResourcePost
                    .type("application/json")
                    .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                    .delete(ClientResponse.class
                    );

            if (response.getStatus() != 204) {
                Logger.getLogger(CobotSubscriptionManager.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Unsubscribe from webhook failed : HTTP error code : " + response.getStatus());
            }

        } catch (UniformInterfaceException ex) {
            Logger.getLogger(CobotSubscriptionManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        Logger.getLogger(CobotSubscriptionManager.class.getName()).log(Level.INFO, "Unsubscribed for subscriptionId " + subscriptionId);

    }
}
