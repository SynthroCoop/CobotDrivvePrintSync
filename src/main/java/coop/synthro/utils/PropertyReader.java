/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author thorsten
 */
public class PropertyReader {

    static String folderName = null;
    static Properties properties = new Properties();

    public static String getProperty(String key) {

        if (properties == null || properties.isEmpty()) {
            LoadPropertiesFile();
        }
        return properties.getProperty(key, "");
    }

    private static void LoadPropertiesFile() {
        try {
            InitialContext context = new InitialContext();
            folderName = (String) context.lookup("java:comp/env/propertiesLocation");
        } catch (NamingException ex) {
            System.out.println("exception in jndi lookup");
        }
        if (folderName != null) {

            File configFile = new File(folderName + "cobotsync.properties");
            try (InputStream is = new FileInputStream(configFile)) {
                properties.load(is);
            } catch (IOException ex) {
                Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
