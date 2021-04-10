package org.cinema.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cinema.service.dbservice.DBConnection;
import org.cinema.service.dbservice.dbrequest.FilmRequest;
;

public class FilmService {

  private static final String DATE_PATTERN =
      "^\\d{4}-((0\\d)|(1[012]))-(([012]\\d)|3[01])$";
  private static final String TIME_PATTERN =
      "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

  public static boolean editFilmNameDateTime(int id, String name, String date, String time) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.UPDATE_NAME_DATE_TIME_BY_ID)) {
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, date);
      preparedStatement.setString(3, time);
      preparedStatement.setInt(4, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean editFilmName(int id, String name) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.UPDATE_NAME_BY_ID)) {
      preparedStatement.setString(1, name);
      preparedStatement.setInt(2, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean editFilmDate(int id, String date) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.UPDATE_DATE_BY_ID)) {
      preparedStatement.setString(1, date);
      preparedStatement.setInt(2, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean editFilmTime(int id, String time) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.UPDATE_TIME_BY_ID)) {
      preparedStatement.setString(1, time);
      preparedStatement.setInt(2, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean deleteFilm(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.DELETE)) {
      preparedStatement.setInt(1, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean deleteFilm(String name) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.DELETE)) {
      preparedStatement.setInt(1, getFilmIdByName(name));
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void showAllFilm() {
    try (Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(FilmRequest.SELECT_ALL_BY_NOT_NULL_NAME)) {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf("ID %-5d name %-35s date %-10s time %-10s \n",
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"));
        }
      } else {
        System.err.println("No available films");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void showIDToAddFilm() {
    try (Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(FilmRequest.SELECT_ALL_BY_NULL_NAME)) {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          System.out.printf("ID %-5d name %-35s date %-10s time %-10s \n",
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getDate("date"),
              resultSet.getTime("time"));
        }
      } else {
        System.err.println("No available ID to add film");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static int getFilmIdByName(String name) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.SELECT_ID_BY_NAME)) {
      preparedStatement.setString(1, name);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      if (resultSet.next()) {
        return resultSet.getInt("id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public static String getFilmNameByID(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.SELECT_NAME_BY_ID)) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      if (resultSet.next()) {
        return resultSet.getString("name");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean hasFilmID(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.SELECT_ID)) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean hasFilmName(String name) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.SELECT_NAME)) {
      preparedStatement.setString(1, name);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean isFilmIdNull(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(FilmRequest.SELECT_NULL_NAME)) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean validateDate(String date) {
    Pattern pattern = Pattern.compile(DATE_PATTERN);
    Matcher matcher = pattern.matcher(date);
    return matcher.matches();
  }

  public static boolean validateTime(String time) {
    Pattern pattern = Pattern.compile(TIME_PATTERN);
    Matcher matcher = pattern.matcher(time);
    return matcher.matches();
  }
}
