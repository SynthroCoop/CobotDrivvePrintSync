/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.print;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author thorsten
 */
public class PinHelper {

    public static int createNewPin() {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
        return randomNum;
    }

    public static String createUniquePin(List<String> existingPins) {

        int pin = createNewPin();
        String formattedPIN = String.format("%04d", pin);
        while (existingPins.contains(formattedPIN)) {
            pin = createNewPin();
            formattedPIN = String.format("%04d", pin);
        }

        return formattedPIN;
    }
}
