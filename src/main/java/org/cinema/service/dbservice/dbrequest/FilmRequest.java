package org.cinema.service.dbservice.dbrequest;

public class FilmRequest {

  public static final String SELECT_ALL_BY_NOT_NULL_NAME =
      "SELECT id, name, date, time FROM cinema.films "
          + "WHERE name IS NOT NULL "
          + "ORDER BY cinema.films.name";
  public static final String SELECT_ALL_BY_NULL_NAME =
      "SELECT id, name, date, time FROM cinema.films "
          + "WHERE name IS NULL";
  public static final String SELECT_ID =
      "SELECT id FROM cinema.films "
          + "WHERE id = ?";
  public static final String SELECT_ID_BY_NAME =
      "SELECT id FROM cinema.films "
          + "WHERE name = ?";
  public static final String SELECT_NAME_BY_ID =
      "SELECT name FROM cinema.films "
          + "WHERE id = ?";
  public static final String SELECT_NAME =
      "SELECT name FROM cinema.films "
          + "WHERE name = ?";
  public static final String UPDATE_NAME_BY_ID =
      "UPDATE cinema.films SET name = ? "
          + "WHERE id = ?";
  public static final String SELECT_NULL_NAME =
      "SELECT name FROM cinema.films "
          + "WHERE name IS NULL "
          + "AND id = ?";
  public static final String UPDATE_NAME_DATE_TIME_BY_ID =
      "UPDATE cinema.films SET name = ?, date = ?, time = ? "
          + "WHERE id = ?";
  public static final String UPDATE_DATE_BY_ID =
      "UPDATE cinema.films SET date = ? "
          + "WHERE id = ?";
  public static final String UPDATE_TIME_BY_ID =
      "UPDATE cinema.films SET time = ? "
          + "WHERE id = ?";
  public static final String DELETE =
      "UPDATE cinema.films, cinema.tickets "
          + "SET cinema.films.name = DEFAULT, cinema.films.date = DEFAULT, "
          + "cinema.films.time = DEFAULT, cinema.tickets.user_id = DEFAULT, "
          + "cinema.tickets.has_sold = DEFAULT "
          + "WHERE cinema.films.id = ? AND cinema.tickets.film_id = cinema.films.id";

}
