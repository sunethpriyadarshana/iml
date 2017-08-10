/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contoller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {

    private final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final String MYSQL_DATABASE = "jdbc:mysql://localhost/citypak";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";

    Statement statement;

    public DatabaseConnector() throws SQLException {
        if (statement == null) {
            try {
                Class.forName(MYSQL_DRIVER);
                Connection connection = DriverManager.getConnection(MYSQL_DATABASE, MYSQL_USERNAME, MYSQL_PASSWORD);
                statement = connection.createStatement();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet search(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public boolean update(String query) throws SQLException {
        int result = statement.executeUpdate(query);
        return result != 0;
    }
    
    public boolean delete(String query) throws SQLException {
       return 0!= statement.executeUpdate(query);
        
    }
    
    public ResultSet insert(String query) throws SQLException {
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        return statement.getResultSet();
    }
}
