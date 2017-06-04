/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.synthro.print;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import coop.synthro.utils.PropertyReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thorsten
 */
public class DrivvePrintDatabaseManager {

        String dbURL;
        String user;
        String pass;
        String usertable;
        
    public DrivvePrintDatabaseManager() {
        dbURL = PropertyReader.getProperty("dbURL");
        user = PropertyReader.getProperty("user");
        pass = PropertyReader.getProperty("pass");
        usertable = PropertyReader.getProperty("usertable");
    }

    private Connection GetConnection() {

        Connection con = null;
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            con = DriverManager.getConnection(dbURL, user, pass);

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    private void AddUserToDB(String UserId, String UserName, String UserEmail, String Pin) {

        String insertStatement = "INSERT INTO " + usertable + " (userLOGIN, userFULLNAME, userEMAIL,userCODE.userPIN.userPUK)VALUES (" + UserId + "," + UserName + "," + UserEmail + "," + Pin + "," + Pin + "," + Pin + ")";
        Connection con = null;
        try {
            con = GetConnection();
            Statement s1 = con.createStatement();
            s1.executeQuery(insertStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void LockUserInDB(String UserId) {

        String updateStatement = "UPDATE " + usertable + " SET userLOCKED=1 WHERE userID=" + UserId + ";";
        Connection con = null;
        try {
            con = GetConnection();
            Statement s1 = con.createStatement();
            s1.executeQuery(updateStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void UnlockUserInDB(String UserId) {

        String updateStatement = "UPDATE " + usertable + " SET userLOCKED=NULL WHERE userID=" + UserId + ";";
        Connection con = null;
        try {
            con = GetConnection();
            Statement s1 = con.createStatement();
            s1.executeQuery(updateStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private List<PrintUser> GetAllUsersFromDB() {

        List<PrintUser> users = new ArrayList<>();
        Connection con = null;
        try {
            con = GetConnection();
            Statement s1 = con.createStatement();
            ResultSet rs = s1.executeQuery("SELECT userID,userLOGIN,userFULLNAME,userEMAIL,userCODE,userPIN,userLOCKED,userPUK FROM " + usertable);
            if (rs != null) {

                PrintUser printUser = new PrintUser();
                while (rs.next()) {
                    //Retrieve by column name

                    printUser.setUserID(rs.getString("userID"));
                    printUser.setUserLOGIN(rs.getString("userLOGIN"));
                    printUser.setUserFULLNAME(rs.getString("userFULLNAME"));
                    printUser.setUserEMAIL(rs.getString("userEMAIL"));
                    printUser.setUserCODE(rs.getString("userCODE"));
                    printUser.setUserPIN(rs.getString("userPIN"));
                    printUser.setUserLOCKED(rs.getString("userLOCKED"));
                    printUser.setUserPUK(rs.getString("userPUK"));
                    users.add(printUser);

                }
                return users;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DrivvePrintDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

}
