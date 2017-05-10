/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.print.user.sync;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author thorsten
 */
@Path("/cobotCallback")
public class CobotSubscriptionCallbackService {

    @POST
    @Path("/created_membership")
    public void created_membership() {

    }

    @POST
    @Path("/canceled_membership")
    public void canceled_membership() {

    }

}
