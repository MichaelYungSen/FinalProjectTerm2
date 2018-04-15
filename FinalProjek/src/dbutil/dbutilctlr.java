/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbutilctlr {
    private static final String USERNAME = "dbuser";
  private static final String PASSWORD = "dbpassword";
  private static final String CONN = "jdbc:mysql://localhost/login";
  private static final String SCONN = "jdbc:sqlite:studentsystem.sqlite";
  
  public static Connection getConnection()
    throws SQLException
  {
    try
    {
      Class.forName("org.sqlite.JDBC");
      
      return DriverManager.getConnection("jdbc:sqlite:schoolsystem.sqlite");
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      ex.printStackTrace();
    }
    return null;
  }
}
