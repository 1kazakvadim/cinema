package org.cinema.service.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private static final String URL = "jdbc:mysql://localhost:3306/mysql";
  private static final String USER = "root";
  private static final String PASSWORD = "root";
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

  private static Connection connection;

  public static Connection getConnection() {
      try {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    return connection;
  }
}
