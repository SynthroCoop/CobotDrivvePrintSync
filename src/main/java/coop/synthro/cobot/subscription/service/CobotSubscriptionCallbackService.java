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
import java.util.logging.Level;
import java.util.logging.Logger;
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

        String memberId = "";
        try {
            //get request body and extract membershipId
            if (body == null) {
                Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.SEVERE, "Missing message body in created_membership");
                throw new IllegalArgumentException("Missing message body in created_membership");
            }
            memberId = body.substring(body.lastIndexOf('/') + 1, body.lastIndexOf('"'));
            Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "Create membership with id " + memberId);

            //Get user information from cobot for this membershipId. 
            CobotMember cobotUser = cobotClient.getCobotMemberInfo(memberId);
            //Get all existing members from database
            List<PrintUser> existingUsers = databaseManager.getAllUsersFromDB();

            //check if the user already existis
            //If yes, check if the user is locked
            //If locked unlock the user
            List<PrintUser> matchingUsers = existingUsers.stream() // convert list to stream
                    .filter(user -> user.getUserEMAIL().equals(cobotUser.getEmail())) //get user with matching email
                    .collect(Collectors.toList());

            if (matchingUsers.size() > 1) {  //we should not have more users. We ignore this for now.
                Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "A cobot member has been added multiple times to the drivve print database");
                throw new IllegalArgumentException("A cobot member has been added multiple times to the drivve print database");
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

            Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "Successfully created membership with id " + memberId);
        } catch (Exception ex) {
            Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.SEVERE, "Error creating membership with id " + memberId + " " + ex.getMessage());
        }
    }

    @POST
    @Path("/canceled_membership")
    @Consumes(MediaType.APPLICATION_JSON)
    public void canceled_membership(String body) {

        String memberId = "";
        try {
            //get call body and extract membershipId
            if (body == null) {
                Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.SEVERE, "Missing message body in canceled_membership");
                throw new IllegalArgumentException("Missing message body in canceled_membership");
            }
            memberId = body.substring(body.lastIndexOf('/') + 1, body.lastIndexOf('"'));
            Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "Cancel membership with id " + memberId);

            //Get user information from cobot for this membershipId. 
            CobotMember cobotUser = cobotClient.getCobotMemberInfo(memberId);

            //Get all existing members from database
            List<PrintUser> existingUsers = databaseManager.getAllUsersFromDB();

            //check if the user already existis
            List<PrintUser> matchingUsers = existingUsers.stream() // convert list to stream
                    .filter(user -> user.getUserEMAIL().equals(cobotUser.getEmail())) //get user with matching email
                    .collect(Collectors.toList());

            if (matchingUsers.isEmpty()) {//we have found no match
                Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "NOT found matching user");
            }
            if (matchingUsers.size() > 1) {  //we should not have more users. We ignore this for now.
                for (PrintUser user : matchingUsers) {
                    Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "Found user :" + user.getUserFULLNAME());
                }
                return;
            }

            if (matchingUsers.size() == 1) {//we have found a match

                Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "Found matching user with email " + matchingUsers.get(0).getUserEMAIL());

//this user must be locked. 
                PrintUser matchingUser = matchingUsers.get(0);
                databaseManager.lockUserInDB(matchingUser.getUserID());

                Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "Successfully canceled membership with id" + memberId);

            }
        } catch (Exception ex) {
            Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.SEVERE, "Error cancelling membership with id " + memberId + " " + ex.getMessage());
        }

    }

}
