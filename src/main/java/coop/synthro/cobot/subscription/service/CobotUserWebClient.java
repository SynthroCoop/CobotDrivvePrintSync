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
import coop.synthro.utils.PropertyReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thorsten
 */
public class CobotUserWebClient {

    String cobotAccessToken = "";
    String appKey="";

    public CobotUserWebClient() {
        cobotAccessToken = PropertyReader.getProperty("cobotAccessToken");
        appKey= "Bearer " + cobotAccessToken;
    }


    public CobotMember GetCobotMemberInfo(String Id) {

        CobotMember member = null;
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("https://:subdomain.cobot.me/api/memberships/" + Id);

            ClientResponse response = webResource
                    .accept("application/json")
                    .header("Authorization", appKey)
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            member = response.getEntity(CobotMember.class);
            Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.INFO, "Got cobot member info for user "+member.getName());


        } catch (Exception ex) {
            Logger.getLogger(CobotUserWebClient.class.getName()).log(Level.SEVERE, "Got cobot member info for user "+member.getName(),ex);
            throw ex;
        }
        return member;

    }
}
