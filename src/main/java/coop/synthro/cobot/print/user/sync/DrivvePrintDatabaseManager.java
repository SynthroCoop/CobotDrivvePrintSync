/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.cobot.print.user.sync;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.sun.media.jfxmedia.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thorsten
 */
public class DrivvePrintDatabaseManager {

    String dbURL = "jdbc:sqlserver://localhost\\sqlexpress";
    String user = "sa";
    String pass = "secret";
    Connection conn = null;

    public void InitializeDatabase() {

        try {
            DriverManager.registerDriver(new SQLServerDriver());
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void AddUser(String UserId, String UserName, String Pin) {

    }

    public void DeleteUser(String UserId) {

    }

}
