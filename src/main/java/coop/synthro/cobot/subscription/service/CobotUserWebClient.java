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
import coop.synthro.cobot.member.model.CobotMember;
import coop.synthro.cobot.subscription.model.EventSubscription;
import coop.synthro.utils.PasswordCryptor;
import coop.synthro.utils.PropertyReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author thorsten
 */
public class CobotUserWebClient {

String cobotMembershipUrl = "https://members.coworking-m1.de/api/memberships";

    public CobotMember getCobotMemberInfo(String Id) {

        cobotMembershipUrl = PropertyReader.getProperty("cobotMembershipUrl");

        CobotMember member = null;
        try {
            
            AccessToken token =  CobotAuthenticator.authenticate();
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);

            String mUrl=cobotMembershipUrl+"/" + Id;
            Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.INFO, "Calling membershipUrl : " +mUrl );
            WebResource webResource = client
                    .resource(mUrl);

            ClientResponse response = webResource
                    .type(MediaType.APPLICATION_JSON)
                    .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            member = response.getEntity(CobotMember.class);
            Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.INFO, "Got cobot member info for user " + member.getName());

        } catch (Exception ex) {
            Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.SEVERE, "Could not get cobot member info for user " , ex);
            throw ex;
        }
        return member;

    }
    
    public List<CobotMember> listMembers(){
        
                try {
            AccessToken token = CobotAuthenticator.authenticate();

            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);

            Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.INFO, "App Token= " + token.getAccess_token());

            WebResource webResource = client.resource(cobotMembershipUrl);

            ClientResponse response = webResource
                    .type(MediaType.APPLICATION_JSON)
                    .header("Authorization", token.getToken_type() + " " + token.getAccess_token())
                    .get(ClientResponse.class);
            //String strings=response.getEntity(String.class);
            List<CobotMember> members = response.getEntity(new GenericType<List<CobotMember>>() {});

            if (response.getStatus() != 200) {
                Logger.getLogger(CobotUserWebClient.class
                        .getName()).log(Level.SEVERE, null, response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.INFO, "Listed all cobot members");

            return members;

        } catch (UniformInterfaceException ex) {
            Logger.getLogger(CobotUserWebClient.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (RuntimeException ex) {
            Logger.getLogger(CobotUserWebClient.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
