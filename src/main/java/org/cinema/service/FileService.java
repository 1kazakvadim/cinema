package org.cinema.service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.cinema.service.dbservice.DBConnection;
import org.cinema.service.dbservice.dbrequest.TicketRequest;


public class FileService {

  public static boolean saveAllPurchasedTicketToFile() {
    try (Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(TicketRequest.SELECT_ALL_PURCHASED)) {
      if (resultSet.isBeforeFirst()) {
        try (FileWriter fw = new FileWriter("purchased_tickets.txt")) {
          while (resultSet.next()) {
            fw.write("Ticket ID: " + resultSet.getInt("id") + "\n");
            fw.write("Login: " + resultSet.getString("login") + "\n");
            fw.write("User ID: " + resultSet.getInt("users.id") + "\n");
            fw.write("Film`s name: " + resultSet.getString("name") + "\n");
            fw.write("date/time: " + resultSet.getDate("date") + " ");
            fw.write(resultSet.getTime("time") + "\n");
            fw.write("place: " + resultSet.getInt("place_number") + "\n");
            fw.write("price: " + resultSet.getInt("price") + "\n");
            fw.write("\n");
          }
          return true;
        }
      } else {
        return false;
      }
    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean saveUserTicketToFile(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_FOR_SAVE_TO_FILE_BY_USER_ID)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.isBeforeFirst()) {
        try (FileWriter fw = new FileWriter(UserService.getLoginByID(id) + "_tickets.txt");) {
          while (resultSet.next()) {
            fw.write("Ticket ID: " + resultSet.getInt("id") + "\n");
            fw.write("Film`s name: " + resultSet.getString("name") + "\n");
            fw.write("place: " + resultSet.getInt("place_number") + "\n");
            fw.write("price: " + resultSet.getInt("price") + "\n");
            fw.write("date/time: " + resultSet.getDate("date") + " ");
            fw.write(resultSet.getTime("time") + "\n");
            fw.write("\n");
          }
          return true;
        }
      } else {
        return false;
      }
    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static boolean saveUserTicketToFile(String login) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_FOR_SAVE_TO_FILE_BY_USER_LOGIN)) {
      preparedStatement.setString(1, login);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.isBeforeFirst()) {
        try (FileWriter fw = new FileWriter(login + "_tickets.txt")) {
          while (resultSet.next()) {
            fw.write("Ticket ID: " + resultSet.getInt("id") + "\n");
            fw.write("Film`s name: " + resultSet.getString("name") + "\n");
            fw.write("place: " + resultSet.getInt("place_number") + "\n");
            fw.write("price: " + resultSet.getInt("price") + "\n");
            fw.write("date/time: " + resultSet.getDate("date") + " ");
            fw.write(resultSet.getTime("time") + "\n");
            fw.write("\n");
          }
          return true;
        }
      } else {
        return false;
      }
    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

}
