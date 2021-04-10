package org.cinema.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cinema.model.User;
import org.cinema.service.dbservice.DBConnection;
import org.cinema.service.dbservice.dbrequest.UserRequest;


public class UserService {

  public static int loginUser(String login, String password) {
    if (UserService.hasUserLogin(login) && BCrypt
        .checkpw(password, UserService.getPassword(login))) {
      User.login = login;
      try (Connection connection = DBConnection.getConnection();
          PreparedStatement preparedStatement = connection
              .prepareStatement(UserRequest.SELECT_ID_USER_GROUP_BY_LOGIN)) {
        preparedStatement.setString(1, login);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        if (resultSet.next()) {
          User.id = resultSet.getInt("id");
          return resultSet.getInt("user_group");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      return -1;
    }
    return -1;
  }

  public static boolean registerUser(String login, String password) {
    if (validateLogin(login) && validatePassword(password)) {
      try (Connection connection = DBConnection.getConnection();
          PreparedStatement preparedStatementInsert = connection
              .prepareStatement(UserRequest.INSERT_SIMPLE_USER)) {
        preparedStatementInsert.setString(1, login);
        preparedStatementInsert.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
        preparedStatementInsert.setInt(3, User.SIMPLE_USER_GROUP);
        preparedStatementInsert.executeUpdate();
        return true;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public static boolean editUserLoginPasswordGroup(int id, String login, String password,
      int group) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.UPDATE_LOGIN_PASSWORD_USER_GROUP_BY_ID)) {
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
      preparedStatement.setInt(3, group);
      preparedStatement.setInt(4, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLIntegrityConstraintViolationException e) {
      System.err.println("Login exists!");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean editUserLogin(int id, String login) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.UPDATE_LOGIN_BY_ID)) {
      preparedStatement.setString(1, login);
      preparedStatement.setInt(2, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLIntegrityConstraintViolationException e) {
      System.err.println("Login exists!");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean editUserPassword(int id, String password) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.UPDATE_PASSWORD_BY_ID)) {
      preparedStatement.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
      preparedStatement.setInt(2, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean editUserGroup(int id, int group) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.UPDATE_USER_GROUP_BY_ID)) {
      preparedStatement.setInt(1, group);
      preparedStatement.setInt(2, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean deleteUser(int id) {
    TicketService.setDefaultSold(id);
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.DELETE_BY_ID)) {
      preparedStatement.setInt(1, id);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean deleteUser(String login) {
    TicketService.setDefaultSold(User.id);
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.DELETE_BY_LOGIN)) {
      preparedStatement.setString(1, login);
      return preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void showAllUser() {
    try (Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(UserRequest.SELECT_ID_LOGIN_USER_GROUP)) {
      while (resultSet.next()) {
        System.out.printf("ID %-5d login %-25s user_group %-5d \n",
            resultSet.getInt("id"),
            resultSet.getString("login"),
            resultSet.getInt("user_group"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static boolean hasUserID(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.SELECT_ID)) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean hasUserLogin(String login) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.SELECT_LOGIN)) {
      preparedStatement.setString(1, login);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean validateLogin(String login) {
    Pattern pattern = Pattern.compile(User.LOGIN_PATTERN);
    Matcher matcher = pattern.matcher(login);
    return matcher.matches();
  }

  public static boolean validatePassword(String password) {
    Pattern pattern = Pattern.compile(User.PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }

  public static String getPassword(String login) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.SELECT_PASSWORD_BY_LOGIN)) {
      preparedStatement.setString(1, login);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      if (resultSet.next()) {
        return resultSet.getString("password");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static int getIdByLogin(String login) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.SELECT_ID_BY_LOGIN)) {
      preparedStatement.setString(1, login);
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

  public static String getLoginByID(int id) {
    try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection
            .prepareStatement(UserRequest.SELECT_LOGIN_BY_ID)) {
      preparedStatement.setInt(1, id);
      preparedStatement.execute();
      ResultSet resultSet = preparedStatement.getResultSet();
      if (resultSet.next()) {
        return resultSet.getString("login");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "";
  }

}




