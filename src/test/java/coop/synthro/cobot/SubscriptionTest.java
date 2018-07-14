/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot;

import coop.synthro.cobot.member.model.CobotMember;
import coop.synthro.cobot.subscription.model.EventSubscription;
import coop.synthro.cobot.subscription.service.CobotSubscriptionManager;
import coop.synthro.cobot.subscription.service.CobotUserWebClient;
import coop.synthro.cobot.syncall.service.CobotSyncAllUsersService;
import coop.synthro.print.DrivvePrintDatabaseManager;
import coop.synthro.print.PinHelper;
import coop.synthro.print.PrintUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class SubscriptionTest {

    public SubscriptionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//    @Test
//    public void getSubscriptions() {
//
//        CobotSubscriptionManager subscriptionManager = new CobotSubscriptionManager();
//        List<EventSubscription> subs = subscriptionManager.listCobotSubscriptions();
//        Assert.assertNotNull(subs);
//    }
//    @Test
//    public void getCobotMemberInfo() {
//
//        CobotUserWebClient userClient = new CobotUserWebClient();
//        CobotMember member = userClient.getCobotMemberInfo("82a1416891b901f529526e9e3c02ebc8");
//        Assert.assertNotNull(member);
//    }
//        @Test
//    public void getCobotMembers() {
//
//        CobotUserWebClient userClient = new CobotUserWebClient();
//        List<CobotMember> members = userClient.listMembers();
//        Assert.assertNotNull(members);
//    }
    @Test
    public void removeAllSubscriptions() {

        //CobotSubscriptionManager subscriptionManager = new CobotSubscriptionManager();
        //subscriptionManager.removeAllSubscriptions();
    }

    @Test
    public void syncAllCobotUsers() {
//        CobotUserWebClient cobotClient = new CobotUserWebClient();
//        DrivvePrintDatabaseManager dbManager = new DrivvePrintDatabaseManager();
//        List<PrintUser> printUsers = dbManager.getAllUsersFromDB();
//
//        //get all cobot users
//        List<CobotMember> cobotUsers = cobotClient.listMembers();
//
//        //get the cobotusers not in the print database yet
//        List<CobotMember> missingUsers = cobotUsers.stream() // convert list to stream
//                .filter(cobotUser -> printUsers.stream().noneMatch(printUser -> printUser.getUserEMAIL().equals(cobotUser.getEmail())))
//                .collect(Collectors.toList());
//
//        for (CobotMember member : missingUsers) {
//
//            Logger.getLogger(CobotSyncAllUsersService.class.getName()).log(Level.INFO, "User Sync: Cobot user still missing: " + member.getEmail());
//
//            //If the user does not exist, create it and create a new PIN before
//            //get a list of all existing Pins
//            List<String> allPins = printUsers.stream() // convert list to stream
//                    .map(PrintUser::getUserPIN)
//                    .collect(Collectors.toList());
//
//            String newPin = PinHelper.createUniquePin(allPins);
//
//            dbManager.addUserToDB(member.getEmail(), member.getName(), member.getEmail(), newPin);
//            Logger.getLogger(CobotSyncAllUsersService.class.getName()).log(Level.INFO, "Successfully synced member with email" + member.getEmail());
//
//        }
    }

    @Test
    public void getAllDbUsers() {

//        DrivvePrintDatabaseManager dbManager = new DrivvePrintDatabaseManager();
        //        List<PrintUser> users = dbManager.getAllUsersFromDB();
//        Assert.assertNotNull(users);
    }
}
