package org.cinema.service.dbservice.dbrequest;

public class UserRequest {

  public static final String SELECT_ID =
      "SELECT id FROM cinema.users "
          + "WHERE id = ?";
  public static final String SELECT_ID_BY_LOGIN =
      "SELECT id FROM cinema.users "
          + "WHERE login = ?";
  public static final String SELECT_ID_USER_GROUP_BY_LOGIN =
      "SELECT id, user_group FROM cinema.users "
          + "WHERE login = ?";
  public static final String SELECT_LOGIN =
      "SELECT login FROM cinema.users "
          + "WHERE login = ?";
  public static final String SELECT_LOGIN_BY_ID =
      "SELECT login FROM cinema.users "
          + "WHERE id = ?";
  public static final String SELECT_PASSWORD_BY_LOGIN =
      "SELECT password FROM cinema.users "
          + "WHERE login = ?";
  public static final String SELECT_ID_LOGIN_USER_GROUP =
      "SELECT id, login, user_group FROM cinema.users";
  public static final String INSERT_SIMPLE_USER =
      "INSERT INTO cinema.users (login, password, user_group) "
          + "VALUES (?, ?, ?)";
  public static final String UPDATE_LOGIN_BY_ID =
      "UPDATE cinema.users SET login = ? "
          + "WHERE id = ?";
  public static final String UPDATE_PASSWORD_BY_ID =
      "UPDATE cinema.users SET password = ? "
          + "WHERE id = ?";
  public static final String UPDATE_USER_GROUP_BY_ID =
      "UPDATE cinema.users SET user_group = ? "
          + "WHERE id = ?";
  public static final String UPDATE_LOGIN_PASSWORD_USER_GROUP_BY_ID =
      "UPDATE cinema.users SET login = ?, password = ?, user_group = ? "
          + "WHERE id = ?";
  public static final String DELETE_BY_ID =
      "DELETE FROM cinema.users "
          + "WHERE id = ?";
  public static final String DELETE_BY_LOGIN =
      "DELETE FROM cinema.users "
          + "WHERE login = ?";

}
