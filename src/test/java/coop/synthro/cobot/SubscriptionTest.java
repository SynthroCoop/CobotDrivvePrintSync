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
import coop.synthro.print.DrivvePrintDatabaseManager;
import coop.synthro.print.PrintUser;
import java.util.List;
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

        CobotSubscriptionManager subscriptionManager = new CobotSubscriptionManager();
        subscriptionManager.removeAllSubscriptions();
    }
    
    
    @Test
    public void getAllDbUsers() {

//        DrivvePrintDatabaseManager dbManager = new DrivvePrintDatabaseManager();
//        List<PrintUser> users = dbManager.getAllUsersFromDB();
//        Assert.assertNotNull(users);
    }
}
