package org.cinema.service.dbservice.dbrequest;

public class TicketRequest {

  public static final String SELECT_ID =
      "SELECT id FROM cinema.tickets "
          + "WHERE id = ?";
  public static final String SELECT_SOLD_BY_ID =
      "SELECT id FROM cinema.tickets "
          + "WHERE id = ? "
          + "AND has_sold = 'no'";
  public static final String SELECT_BY_USER_ID =
      "SELECT cinema.tickets.id, cinema.users.id, cinema.users.login, "
          + "cinema.films.name, cinema.films.date, cinema.films.time "
          + "FROM cinema.tickets, cinema.users, cinema.films "
          + "WHERE tickets.user_id = cinema.users.id "
          + "AND tickets.film_id = films.id "
          + "AND users.id = ?";
  public static final String SELECT_BY_USER_LOGIN =
      "SELECT cinema.tickets.id, cinema.users.id, cinema.users.login, "
          + "cinema.films.name, cinema.films.date, cinema.films.time "
          + "FROM cinema.tickets, cinema.users, cinema.films "
          + "WHERE tickets.user_id = cinema.users.id "
          + "AND tickets.film_id = films.id "
          + "AND users.login = ?";
  public static final String SELECT_BY_FILM_NAME =
      "SELECT cinema.tickets.id, cinema.users.id, cinema.users.login, "
          + "cinema.films.name, cinema.films.date, cinema.films.time "
          + "FROM cinema.tickets, cinema.users, cinema.films "
          + "WHERE tickets.user_id = cinema.users.id "
          + "AND tickets.film_id = films.id "
          + "AND films.name = ?";
  public static final String SELECT_BY_FILM_ID =
      "SELECT cinema.tickets.id, cinema.users.id, cinema.users.login, "
          + "cinema.films.name, cinema.films.date, cinema.films.time "
          + "FROM cinema.tickets, cinema.users, cinema.films "
          + "WHERE tickets.user_id = cinema.users.id "
          + "AND tickets.film_id = films.id "
          + "AND films.id = ?";
  public static final String SELECT_ALL_PURCHASED =
      "SELECT cinema.tickets.id, cinema.users.id, cinema.users.login, "
          + "cinema.films.name, cinema.films.date, cinema.films.time, cinema.tickets.place_number, "
          + "cinema.tickets.price "
          + "FROM cinema.tickets, cinema.users, cinema.films "
          + "WHERE tickets.user_id = users.id "
          + "AND tickets.film_id = films.id "
          + "AND tickets.has_sold = 'yes'";
  public static final String SELECT_ALL_NOT_PURCHASED =
      "SELECT cinema.tickets.id, cinema.films.name, cinema.films.date, cinema.films.time, "
          + "cinema.tickets.place_number, cinema.tickets.price "
          + "FROM cinema.tickets, cinema.films "
          + "WHERE tickets.film_id = films.id "
          + "AND films.name IS NOT NULL "
          + "AND tickets.has_sold = 'no'";

  public static final String SELECT_ALL =
      "SELECT cinema.tickets.id, cinema.films.name, cinema.films.date, cinema.films.time, "
          + "cinema.tickets.place_number, cinema.tickets.price, cinema.tickets.has_sold "
          + "FROM cinema.tickets "
          + "LEFT JOIN cinema.films "
          + "ON cinema.tickets.film_id = cinema.films.id "
          + "WHERE cinema.films.name IS NOT NULL";

  public static final String SELECT_FOR_SAVE_TO_FILE_BY_USER_ID =
      "SELECT cinema.tickets.id, cinema.films.name, cinema.films.date, "
          + "cinema.films.time, cinema.tickets.place_number, cinema.tickets.price "
          + "FROM cinema.tickets, cinema.users, cinema.films "
          + "WHERE tickets.user_id = cinema.users.id "
          + "AND tickets.film_id = films.id "
          + "AND tickets.has_sold = 'yes' "
          + "AND users.id = ?";
  public static final String SELECT_FOR_SAVE_TO_FILE_BY_USER_LOGIN =
      "SELECT cinema.tickets.id, cinema.films.name, cinema.films.date, "
          + "cinema.films.time, cinema.tickets.place_number, cinema.tickets.price "
          + "FROM cinema.tickets, cinema.users, cinema.films "
          + "WHERE tickets.user_id = cinema.users.id "
          + "AND tickets.film_id = films.id "
          + "AND tickets.has_sold = 'yes' "
          + "AND users.login = ?";
  public static final String UPDATE_USER_ID_SOLD_BY_ID =
      "UPDATE cinema.tickets SET user_id = ?, has_sold = 'yes' "
          + "WHERE id = ?";
  public static final String UPDATE_SOLD_BY_ID =
      "UPDATE cinema.tickets SET has_sold = DEFAULT "
          + "WHERE user_id = ? ";
  public static final String UPDATE_PRICE_BY_FILM_ID =
      "UPDATE cinema.films, cinema.tickets SET price = ? "
          + "WHERE films.id = ? AND films.name IS NOT NULL "
          + "AND film_id = ? ";
  public static final String DELETE_BY_ID =
      "UPDATE cinema.tickets SET user_id = DEFAULT, has_sold = DEFAULT "
          + "WHERE id = ? ";
  public static final String DELETE_BY_ID_AND_USER_ID =
      "UPDATE cinema.tickets SET user_id = DEFAULT, has_sold = DEFAULT "
          + "WHERE id = ? "
          + "AND cinema.tickets.user_id = ?";

}
