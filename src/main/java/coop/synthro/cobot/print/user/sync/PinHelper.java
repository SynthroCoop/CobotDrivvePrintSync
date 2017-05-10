/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.print.user.sync;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author thorsten
 */
public class PinHelper {

    public int CreateNewPin() {

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
        return randomNum;
    }

    public String CreateUniquePin(List<String> existingPins) {

        int pin = CreateNewPin();
        String formattedPIN = String.format("%04d", pin);
        while (existingPins.contains(formattedPIN)) {
            pin = CreateNewPin();
            formattedPIN = String.format("%04d", pin);
        }

        return formattedPIN;
    }
}
