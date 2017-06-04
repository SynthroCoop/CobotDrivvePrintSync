/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.subscription.service;

import coop.synthro.cobot.member.model.CobotMember;
import coop.synthro.print.DrivvePrintDatabaseManager;
import coop.synthro.print.PinHelper;
import coop.synthro.print.PrintUser;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author thorsten
 */
@Path("/cobotCallback")
public class CobotSubscriptionCallbackService {

    private DrivvePrintDatabaseManager databaseManager = new DrivvePrintDatabaseManager();
    private CobotUserWebClient cobotClient = new CobotUserWebClient();

    @POST
    @Path("/created_membership")
    @Consumes(MediaType.APPLICATION_JSON)
    public void created_membership(String body) {

        //get request body and extract membershipId
        if(body==null){
            throw new IllegalArgumentException("Missing message body in created_membership");
        }
        String memberId = body.substring(body.lastIndexOf('/') + 1,body.lastIndexOf('"') -1);
                
        //Get user information from cobot for this membershipId. 
        cobotClient.Authenticate();
        CobotMember cobotUser = cobotClient.GetCobotMemberInfo(memberId);
        //Get all existing members from database
        List<PrintUser> existingUsers = databaseManager.getAllUsersFromDB();

        //check if the user already existis
        //If yes, check if the user is locked
        //If locked unlock the user
        List<PrintUser> matchingUsers = existingUsers.stream() // convert list to stream
                .filter(user -> user.getUserEMAIL().equals(cobotUser.getEmail())) //get user with matching email
                .collect(Collectors.toList());

        if (matchingUsers.size() > 1) {  //we should not have more users. We ignore this for now.
            return;
        }

        if (matchingUsers.size() == 1) {//we have found a match
            //this user should be locked. If locked, the  we must unlock it.
            PrintUser matchingUser = matchingUsers.get(0);
            if ("1".equals(matchingUser.getUserLOCKED())) {
                databaseManager.unlockUserInDB(matchingUser.getUserID());
            }
            return;
        }

        //if we did not find a match
        //If the user does not exist, create it and create a new PIN before
        //get a list of all existing Pins
        List<String> allPins = existingUsers.stream() // convert list to stream
                .map(PrintUser::getUserPIN)
                .collect(Collectors.toList());

        String newPin = PinHelper.createUniquePin(allPins);

        databaseManager.addUserToDB(cobotUser.getEmail(), cobotUser.getName(), cobotUser.getEmail(), newPin);
    }

    @POST
    @Path("/canceled_membership")
    @Consumes(MediaType.APPLICATION_JSON)
    public void canceled_membership(String body) {
        //get call body and extract membershipId
        if(body==null){
            throw new IllegalArgumentException("Missing message body in created_membership");
        }
        String memberId = body.substring(body.lastIndexOf('/') + 1,body.lastIndexOf('"') -1);

        //Get user information from cobot for this membershipId. 
        cobotClient.Authenticate();
        CobotMember cobotUser = cobotClient.GetCobotMemberInfo(memberId);

        //Get all existing members from database
        List<PrintUser> existingUsers = databaseManager.getAllUsersFromDB();

        //check if the user already existis
        List<PrintUser> matchingUsers = existingUsers.stream() // convert list to stream
                .filter(user -> user.getUserEMAIL().equals(cobotUser.getEmail())) //get user with matching email
                .collect(Collectors.toList());

        if (matchingUsers.size() > 1) {  //we should not have more users. We ignore this for now.
            return;
        }

        if (matchingUsers.size() == 1) {//we have found a match
            //this user must be locked. 
            PrintUser matchingUser = matchingUsers.get(0);
            databaseManager.lockUserInDB(matchingUser.getUserID());

            return;
        }
    }

}
