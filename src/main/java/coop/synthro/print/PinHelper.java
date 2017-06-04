/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.print;

import coop.synthro.cobot.subscription.service.CobotSubscriptionCallbackService;
import coop.synthro.utils.PropertyReader;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thorsten
 */
public class PinHelper {

    private static String pinSize = "4";

    public PinHelper() {
        pinSize = PropertyReader.getProperty("pinSize");
    }

    public static int createNewPin() {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
        return randomNum;
    }

    public static String createUniquePin(List<String> existingPins) {

        int pin = createNewPin();
        String formattedPIN = String.format("%0" + pinSize + "d", pin);
        while (existingPins.contains(formattedPIN)) {
            pin = createNewPin();
            formattedPIN = String.format("%0" + pinSize + "d", pin);
        }

        Logger.getLogger(CobotSubscriptionCallbackService.class.getName()).log(Level.INFO, "Created unique pin " + formattedPIN);

        return formattedPIN;
    }
}
