/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.syncall.service;

import coop.synthro.cobot.member.model.CobotMember;
import coop.synthro.cobot.subscription.service.CobotSubscriptionCallbackService;
import coop.synthro.cobot.subscription.service.CobotUserWebClient;
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
 * @author thors
 */
@Path("/cobotCallback")
public class CobotSyncAllUsersService {

    private DrivvePrintDatabaseManager databaseManager = new DrivvePrintDatabaseManager();
    private CobotUserWebClient cobotClient = new CobotUserWebClient();

    @POST
    @Path("/syncallusers")
    @Consumes(MediaType.APPLICATION_JSON)

    public void sync_all_users() {
        //get all print users
        List<PrintUser> printUsers = databaseManager.getAllUsersFromDB();

        //get all cobot users
        List<CobotMember> cobotUsers = cobotClient.listMembers();

        //get the cobotusers not in the print database yet
        List<CobotMember> missingUsers = cobotUsers.stream() // convert list to stream
                .filter(cobotUser -> printUsers.stream().noneMatch(printUser -> printUser.getUserEMAIL().equals(cobotUser.getEmail())))
                .collect(Collectors.toList());

        for (CobotMember member : missingUsers) {

            Logger.getLogger(CobotSyncAllUsersService.class.getName()).log(Level.INFO, "User Sync: Cobot user still missing: " + member.getEmail());

            //If the user does not exist, create it and create a new PIN before
            //get a list of all existing Pins
            List<String> allPins = printUsers.stream() // convert list to stream
                    .map(PrintUser::getUserPIN)
                    .collect(Collectors.toList());

            String newPin = PinHelper.createUniquePin(allPins);

            //databaseManager.addUserToDB(member.getEmail(), member.getName(), member.getEmail(), newPin);

            Logger.getLogger(CobotSyncAllUsersService.class.getName()).log(Level.INFO, "Successfully synced member with email" + member.getEmail());

        }

    }
}
