/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.main;

import coop.synthro.cobot.subscription.service.CobotSubscriptionManager;
import javax.servlet.ServletContextEvent;

/**
 *
 * @author thorsten
 */
public class Listener implements javax.servlet.ServletContextListener {

    CobotSubscriptionManager subscriptionManager= new CobotSubscriptionManager();
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      subscriptionManager.initializeSubscriptions();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       //subscriptionManager.removeAllSubscriptions();
    }
}
