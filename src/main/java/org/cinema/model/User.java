package org.cinema.model;

public class User {

  public static final int SIMPLE_USER_GROUP = 1;
  public static final String LOGIN_PATTERN =
      "^[a-zA-Z_]{8,25}$";
  public static final String PASSWORD_PATTERN =
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,25}$";

  public static String login;
  public static int id;


}