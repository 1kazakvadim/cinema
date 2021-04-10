package org.cinema.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.cinema.service.dbservice.DBConnection;
import org.cinema.service.dbservice.dbrequest.TicketRequest;


public class TicketService {

  public static boolean buyTicket(int userID, int ticketID) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.UPDATE_USER_ID_SOLD_BY_ID)) {
      preparedStatement.setInt(1, userID);
      preparedStatement.setInt(2, ticketID);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean deleteTicket(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.DELETE_BY_ID)) {
      preparedStatement.setInt(1, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean deleteTicket(int ticketID, int userID) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.DELETE_BY_ID_AND_USER_ID)) {
      preparedStatement.setInt(1, ticketID);
      preparedStatement.setInt(2, userID);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean editTicketPrice(int id, int price) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.UPDATE_PRICE_BY_FILM_ID)) {
      preparedStatement.setInt(1, price);
      preparedStatement.setInt(2, id);
      preparedStatement.setInt(3, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void showPurchasedUserTicket(String login) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_BY_USER_LOGIN)) {
      preparedStatement.setString(1, login);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf("ID %-5d login %-25s user_id %-5d name %-35s date %-10s time %-10s \n",
              resultSet.getInt("id"),
              resultSet.getString("login"),
              resultSet.getInt("users.id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"));
        }
      } else {
        System.err.println("No purchased tickets");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void showPurchasedUserTicket(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_BY_USER_ID)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf("ID %-5d login %-25s user_id %-5d name %-35s date %-10s time %-10s \n",
              resultSet.getInt("id"),
              resultSet.getString("login"),
              resultSet.getInt("users.id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"));
        }
      } else {
        System.err.println("No purchased tickets");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void showPurchasedFilmTicket(String name) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_BY_FILM_NAME)) {
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf("ID %-5d login %-25s user_id %-5d name %-35s date %-10s time %-10s \n",
              resultSet.getInt("id"),
              resultSet.getString("login"),
              resultSet.getInt("users.id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"));
        }
      } else {
        System.err.println("No purchased tickets");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void showPurchasedFilmTicket(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_BY_FILM_ID)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf("ID %-5d login %-25s user_id %-5d name %-35s date %-10s time %-10s \n",
              resultSet.getInt("id"),
              resultSet.getString("login"),
              resultSet.getInt("users.id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"));
        }
      } else {
        System.err.println("No purchased tickets");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void showAllTicket() {
    try (Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(TicketRequest.SELECT_ALL)) {
      while (resultSet.next()) {
        System.out.printf(
            "ID %-5d name %-45s date %-10s time %-10s place_number %-5d price %-5d has_sold %-5s \n",
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getDate("date"),
            resultSet.getTime("time"),
            resultSet.getInt("place_number"),
            resultSet.getInt("price"),
            resultSet.getString("has_sold"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void showAllPurchasedTicket() {
    try (Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(TicketRequest.SELECT_ALL_PURCHASED)) {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf(
              "ID %-5d login %-25s user_id %-5d name %-45s date %-10s time %-10s place_number %-5d price %-5d \n",
              resultSet.getInt("id"),
              resultSet.getString("login"),
              resultSet.getInt("users.id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"),
              resultSet.getInt("place_number"),
              resultSet.getInt("price"));
        }
      } else {
        System.err.println("No purchased tickets");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public static void showAllNotPurchasedTicket() {
    try (Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(TicketRequest.SELECT_ALL_NOT_PURCHASED)) {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf(
              "ID %-5d name %-45s date %-10s time %-10s place_number %-5d price %-5d \n",
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"),
              resultSet.getInt("place_number"),
              resultSet.getInt("price"));
        }
      } else {
        System.err.println("No available tickets");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static boolean hasTicketID(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_ID)) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean hasTicketSold(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.SELECT_SOLD_BY_ID)) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  public static boolean setDefaultSold(int userID) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(TicketRequest.UPDATE_SOLD_BY_ID)) {
      preparedStatement.setInt(1, userID);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
