package org.cinema.console;

import org.apache.log4j.Logger;
import org.cinema.model.User;
import org.cinema.service.FilmService;
import org.cinema.service.TicketService;
import org.cinema.service.UserService;

public class AdminMenu extends ConsoleMenu implements Startable {

  private static final Logger logger = Logger.getLogger(AdminMenu.class);

  public static void start() {
    do {
      System.out.println("1. Show all users");
      System.out.println("2. Delete user");
      System.out.println("3. Edit user");
      System.out.println("4. Show all films");
      System.out.println("5. Edit film");
      System.out.println("6. Add new film");
      System.out.println("7. Delete film");
      System.out.println("8. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1 -> {
          logger.info("menu selected: Show all users");
          showAllUserMenu();
        }
        case 2 -> {
          logger.info("menu selected: Delete user");
          deleteUserMenu();
        }
        case 3 -> {
          logger.info("menu selected: Edit user");
          editUserMenu();
        }
        case 4 -> {
          logger.info("menu selected: Show all films");
          showAllFilmMenu();
        }
        case 5 -> {
          logger.info("menu selected: Edit film");
          editFilmMenu();
        }
        case 6 -> {
          logger.info("menu selected: Add new film");
          addNewFilmMenu();
        }
        case 7 -> {
          logger.info("menu selected: Delete film");
          deleteFilmMenu();
        }
        case 8 -> {
          logger.info(User.login + " logged out");
          return;
        }
      }
    } while (true);
  }

  private static void showAllUserMenu() {
    UserService.showAllUser();
  }

  private static void deleteUserMenu() {
    System.out.println("Delete by:");
    do {
      System.out.println("1. ID");
      System.out.println("2. login");
      System.out.println("3. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1:
          logger.info("menu selected: Delete by ID");
          deleteUserByIDMenu();
          break;
        case 2:
          logger.info("menu selected: Delete by login");
          deleteUserByLoginMenu();
          break;
        case 3:
          return;
      }
    } while (true);
  }

  private static void deleteUserByIDMenu() {
    do {
      System.out.println("Enter user`s ID to delete, or 0 to return:");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (UserService.hasUserID(selection)) {
        UserService.deleteUser(selection);
        logger.info(String.format("user deleted ID: %d", selection));
        System.err.println("User deleted!");
      } else {
        System.err.println("User doesn't exist!");
      }
    } while (true);
  }

  private static void deleteUserByLoginMenu() {
    do {
      System.out.println("Enter user`s login to delete, or 0 to return:");
      String login = scanner.next();
      if (login.equals("0")) {
        break;
      }
      if (UserService.hasUserLogin(login)) {
        UserService.deleteUser(login);
        logger.info(String.format("user deleted login: %s", login));
        System.err.println("User deleted!");
      } else {
        System.err.println("User doesn't exist!");
      }
    } while (true);
  }

  private static void editUserMenu() {
    do {
      System.out.println("1. Edit user`s login, password, group");
      System.out.println("2. Edit user`s login");
      System.out.println("3. Edit user`s password");
      System.out.println("4. Edit user`s group");
      System.out.println("5. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1:
          logger.info("menu selected: Edit user`s login, password, group");
          editUserLoginPasswordGroupMenu();
          break;
        case 2:
          logger.info("menu selected: Edit user`s login");
          editUserLoginMenu();
          break;
        case 3:
          logger.info("menu selected: Edit user`s password");
          editUserPasswordMenu();
          break;
        case 4:
          logger.info("menu selected: Edit user`s group");
          editUserGroupMenu();
          break;
        case 5:
          return;
      }
    } while (true);
  }

  private static void editUserLoginPasswordGroupMenu() {
    UserService.showAllUser();
    do {
      System.out.println("Enter user`s ID and new user`s login, password, group, or 0 to return:");
      System.out.println("User`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (UserService.hasUserID(selection)) {
        System.out.println("User`s new login*");
        String login = scanner.next();
        if (login.equals("0")) {
          return;
        }
        if (!UserService.hasUserLogin(login) && UserService.validateLogin(login)) {
          System.out.println("User`s new password*");
          String password = scanner.next();
          if (password.equals("0")) {
            return;
          }
          if (UserService.validatePassword(password)) {
            System.out.println("User`s new group*");
            while (!scanner.hasNextInt()) {
              System.err.println("Invalid value!");
              scanner.nextLine();
            }
            int group = scanner.nextInt();
            if (group == 0) {
              break;
            }
            if (group <= 3 && group > 0) {
              UserService.editUserLoginPasswordGroup(selection, login, password, group);
              logger.info(String
                  .format("user ID %d changed to: %s ***** %d", selection, login, group));
              System.err.println("User has changed!");
            } else {
              System.err.println("Invalid group!");
            }
          } else {
            System.err.println("Invalid password!");
          }
        } else {
          System.err.println("User with such login exists or invalid login!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void editUserLoginMenu() {
    UserService.showAllUser();
    do {
      System.out.println("Enter user`s ID and new user`s login, or 0 to return:");
      System.out.println("User`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (UserService.hasUserID(selection)) {
        System.out.println("User`s new login*");
        String login = scanner.next();
        if (login.equals("0")) {
          return;
        }
        if (!UserService.hasUserLogin(login) && UserService.validateLogin(login)) {
          UserService.editUserLogin(selection, login);
          logger.info(String
              .format("user`s login ID %d changed to: %s", selection, login));
          System.err.println("Login has changed!");
        } else {
          System.err.println("User with such login exists or invalid login!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void editUserPasswordMenu() {
    UserService.showAllUser();
    do {
      System.out.println("Enter user`s ID and new user`s password, or 0 to return:");
      System.out.println("User`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (UserService.hasUserID(selection)) {
        System.out.println("User`s new password*");
        String password = scanner.next();
        if (password.equals("0")) {
          return;
        }
        if (UserService.validatePassword(password)) {
          UserService.editUserPassword(selection, password);
          logger.info(String
              .format("user`s password ID %d changed", selection));
          System.err.println("Password has changed!");
        } else {
          System.err.println("Invalid password!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void editUserGroupMenu() {
    UserService.showAllUser();
    do {
      System.out.println("Enter user`s ID and new user`s group, or 0 to return:");
      System.out.println("User`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (UserService.hasUserID(selection)) {
        System.out.println("User`s new group*");
        while (!scanner.hasNextInt()) {
          System.err.println("Invalid value!");
          scanner.nextLine();
        }
        int group = scanner.nextInt();
        if (group <= 3 && group > 0) {
          UserService.editUserGroup(selection, group);
          logger.info(String
              .format("user`s group ID %d changed to: %d", selection, group));
          System.err.println("Group has changed!");
        } else {
          System.err.println("Invalid group!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void showAllFilmMenu() {
    FilmService.showAllFilm();
  }

  private static void editFilmMenu() {
    do {
      System.out.println("1. Edit film`s name and date/time");
      System.out.println("2. Edit film`s name");
      System.out.println("3. Edit film`s date");
      System.out.println("4. Edit film`s time");
      System.out.println("5. Edit film`s ticket price");
      System.out.println("6. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1:
          logger.info("menu selected: Edit film`s name and date/time");
          editFilmNameDateTimeMenu();
          break;
        case 2:
          logger.info("menu selected: Edit film`s name");
          editFilmNameMenu();
          break;
        case 3:
          logger.info("menu selected: Edit film`s date");
          editFilmDateMenu();
          break;
        case 4:
          logger.info("menu selected: Edit film`s time");
          editFilmTimeMenu();
          break;
        case 5:
          logger.info("menu selected: Edit film`s ticket price");
          editFilmTicketPriceMenu();
          break;
        case 6:
          return;
      }
    } while (true);
  }

  private static void editFilmNameDateTimeMenu() {
    FilmService.showAllFilm();
    do {
      System.out.println(
          "Enter films`s ID and new film`s name, date in format yyyy-mm-dd, "
              + "time in format hh:mm, or 0 to return:");
      System.out.println("Film`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.hasFilmID(selection) && !FilmService.isFilmIdNull(selection)) {
        System.out.println("Film`s new name*");
        String name = scanner.next();
        if (name.equals("0")) {
          return;
        }
        System.out.println("Film`s new date*");
        String date = scanner.next();
        if (date.equals("0")) {
          return;
        }
        if (FilmService.validateDate(date)) {
          System.out.println("Film`s new time*");
          String time = scanner.next();
          if (time.equals("0")) {
            return;
          }
          if (FilmService.validateTime(time)) {
            FilmService.editFilmNameDateTime(selection, name, date, time);
            logger.info(String
                .format("film ID %d changed to: %s %s %s", selection, name, date, time));
            System.err.println("Film has changed!");
          } else {
            System.err.println("Invalid time!");
          }
        } else {
          System.err.println("Invalid date!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void editFilmNameMenu() {
    FilmService.showAllFilm();
    do {
      System.out.println("Enter films`s ID and new film`s name, or 0 to return:");
      System.out.println("Film`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.hasFilmID(selection) && !FilmService.isFilmIdNull(selection)) {
        System.out.println("Film`s new name*");
        String name = scanner.next();
        if (name.equals("0")) {
          return;
        }
        FilmService.editFilmName(selection, name);
        logger.info(String
            .format("film`s name ID %d changed to: %s", selection, name));
        System.err.println("Name has changed!");
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void editFilmDateMenu() {
    FilmService.showAllFilm();
    do {
      System.out
          .println("Enter films`s ID and new film`s date in format yyyy-mm-dd, or 0 to return:");
      System.out.println("Film`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.hasFilmID(selection) && !FilmService.isFilmIdNull(selection)) {
        System.out.println("Film`s new date*");
        String date = scanner.next();
        if (date.equals("0")) {
          return;
        }
        if (FilmService.validateDate(date)) {
          FilmService.editFilmDate(selection, date);
          logger.info(String
              .format("film`s date ID %d changed to: %s", selection, date));
          System.err.println("Date has changed!");
        } else {
          System.err.println("Invalid date!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void editFilmTimeMenu() {
    FilmService.showAllFilm();
    do {
      System.out
          .println("Enter films`s ID and new film`s time in format hh:mm, or 0 to return:");
      System.out.println("Film`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.hasFilmID(selection) && !FilmService.isFilmIdNull(selection)) {
        System.out.println("Film`s new time*");
        String time = scanner.next();
        if (time.equals("0")) {
          return;
        }
        if (FilmService.validateTime(time)) {
          FilmService.editFilmTime(selection, time);
          logger.info(String
              .format("film`s time ID %d changed to: %s", selection, time));
          System.err.println("Time has changed!");
        } else {
          System.err.println("Invalid time!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void editFilmTicketPriceMenu() {
    FilmService.showAllFilm();
    do {
      System.out.println("Enter film`s ID and new film`s price, or 0 to return:");
      System.out.println("Film`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.hasFilmID(selection) && !FilmService.isFilmIdNull(selection)) {
        System.out.println("Film`s new price*");
        while (!scanner.hasNextInt()) {
          System.err.println("Invalid value!");
          scanner.nextLine();
        }
        int price = scanner.nextInt();
        if (price == 0) {
          break;
        }
        if (price > 0) {
          TicketService.editTicketPrice(selection, price);
          logger.info(String
              .format("film`s price ID %d changed to: %s", selection, price));
          System.err.println("Price has changed!");
        } else {
          System.err.println("Invalid price!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void addNewFilmMenu() {
    do {
      System.out.println("1. Show ID to add film");
      System.out.println("2. Add film");
      System.out.println("3. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      switch (selection) {
        case 1:
          logger.info("menu selected: Show ID to add film");
          showIDToAddFilmMenu();
          break;
        case 2:
          logger.info("menu selected: Add film");
          addFilmMenu();
          break;
        case 3:
          return;
      }
    } while (true);
  }

  private static void showIDToAddFilmMenu() {
    FilmService.showIDToAddFilm();
  }

  private static void addFilmMenu() {
    do {
      System.out.println("Enter ID, film`s name, date in format yyyy-mm-dd, "
          + "time in format hh:mm, or 0 to return:");
      System.out.println("ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.hasFilmID(selection) && FilmService.isFilmIdNull(selection)) {
        System.out.println("Film`s new name*");
        String name = scanner.next();
        if (name.equals("0")) {
          return;
        }
        System.out.println("Film`s new date*");
        String date = scanner.next();
        if (date.equals("0")) {
          return;
        }
        if (FilmService.validateDate(date)) {
          System.out.println("Film`s new time*");
          String time = scanner.next();
          if (time.equals("0")) {
            return;
          }
          if (FilmService.validateTime(time)) {
            FilmService.editFilmNameDateTime(selection, name, date, time);
            logger.info(String
                .format("film added: %d %s %s %s", selection, name, date, time));
            System.err.println("Film added!");
          } else {
            System.err.println("Invalid time!");
          }
        } else {
          System.err.println("Invalid date!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void deleteFilmMenu() {
    System.out.println("Delete by:");
    do {
      System.out.println("1. ID");
      System.out.println("2. name");
      System.out.println("3. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1:
          logger.info("menu selected: Delete by ID");
          deleteFilmByIDMenu();
          break;
        case 2:
          logger.info("menu selected: Delete by name");
          deleteFilmByNameMenu();
          break;
        case 3:
          return;
      }
    } while (true);
  }

  private static void deleteFilmByIDMenu() {
    do {
      System.out.println("Enter film`s ID to delete, or 0 to return:");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.deleteFilm(selection)) {
        logger.info(String
            .format("film ID %d deleted", selection));
        System.err.println("Film deleted!");
      } else {
        System.err.println("Film doesn't exist!");
      }
    } while (true);
  }

  private static void deleteFilmByNameMenu() {
    do {
      System.out.println("Enter film`s name to delete, or 0 to return:");
      String name = scanner.next();
      if (name.equals("0")) {
        break;
      }
      if (FilmService.deleteFilm(name)) {
        logger.info(String
            .format("film name %s deleted", name));
        System.err.println("Film deleted!");
      } else {
        System.err.println("Film doesn't exist!");
      }
    } while (true);
  }
}
