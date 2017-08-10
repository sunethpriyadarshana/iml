/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contoller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PermissionChecker {

    private int userId = 0;
    private boolean isPermissionGranted = false;

    public PermissionChecker(String key) {
        String query = "SELECT iduser FROM `user` WHERE api_key='" + key + "'";
        try {
            ResultSet user = new DatabaseConnector().search(query);
            if (user.next()) {
                userId = user.getInt("iduser");
                isPermissionGranted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermissionChecker.class.getName()).log(Level.SEVERE, null, ex);
            userId = 0;
            isPermissionGranted = false;
        }
    }

    public boolean isPermissionGranted() {
        return isPermissionGranted;
    }

    public int getUserId() {
        return userId;
    }

}
