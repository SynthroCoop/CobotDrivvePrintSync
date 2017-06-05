/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.subscription.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import coop.synthro.cobot.member.model.CobotMember;
import coop.synthro.utils.PasswordCryptor;
import coop.synthro.utils.PropertyReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thorsten
 */
public class CobotAuthenticator {

    static AccessToken cobotAccessToken = null;
    static String cobotUser = null;
    static String cobotPassword = null;
    static String cobotClientId = null;
    static String cobotSecret = null;
    static String cobotAccessTokenUrl = null;

    public static AccessToken authenticate() {
        if (cobotAccessToken == null) {
            try {

                cobotUser = PasswordCryptor.decrypt(PropertyReader.getProperty("cobotUser"));
                cobotPassword = PasswordCryptor.decrypt(PropertyReader.getProperty("cobotPassword"));
                cobotClientId = PasswordCryptor.decrypt(PropertyReader.getProperty("cobotClientId"));
                cobotSecret = PasswordCryptor.decrypt(PropertyReader.getProperty("cobotSecret"));
                cobotAccessTokenUrl = PropertyReader.getProperty("cobotAccessTokenUrl");

                Client client = Client.create();
  
                WebResource webResource = client
                        .resource(cobotAccessTokenUrl)
                        .queryParam("scope", "read_memberships read_user and write_subscriptions")
                        .queryParam("grant_type", "password")
                        .queryParam("username", cobotUser)
                        .queryParam("passowrd", cobotPassword)
                        .queryParam("client_id", cobotClientId)
                        .queryParam("client_secret", cobotSecret);

                ClientResponse response = webResource
                        .accept("application/json")
                        .post(ClientResponse.class);

                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }

                cobotAccessToken = response.getEntity(AccessToken.class);

                Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.INFO, "Got access token from cobot");

            } catch (Exception ex) {
                Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.SEVERE, "Error gettng access token from cobot", ex);
                throw ex;
            }
        }

        return cobotAccessToken;
    }

}
