/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.print.user.sync;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import coop.synthro.cobot.print.user.sync.model.CobotMember;

/**
 *
 * @author thorsten
 */
public class CobotUserWebClient {

    public void Authenticate(){
        
    }
    
    public CobotMember GetCobotMemberInfo(String Id) {

        CobotMember member = null;
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("https://:subdomain.cobot.me/api/memberships/"+Id);

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            member = response.getEntity(CobotMember.class);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return member;

    }
}
