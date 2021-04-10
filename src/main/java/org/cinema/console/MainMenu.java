package org.cinema.console;

import org.apache.log4j.Logger;
import org.cinema.model.User;
import org.cinema.service.UserService;

public class MainMenu extends ConsoleMenu implements Startable {

  private static final Logger logger = Logger.getLogger(MainMenu.class);

  public static void start() {
    System.out.println("Hello!");
    while (true) {
      System.out.println("1. Sign in");
      System.out.println("2. Create an account");
      System.out.println("3. Exit");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1:
          loginMenu();
          break;
        case 2:
          registrationMenu();
          break;
        case 3:
          return;
      }
    }
  }

  private static void loginMenu() {
    do {
      System.out.println("Enter login/password to log in, or 0 to return:");
      System.out.println("Login*");
      String login = scanner.next();
      if (login.equals("0")) {
        return;
      }
      System.out.println("Password*");
      String password = scanner.next();
      if (password.equals("0")) {
        return;
      }
      switch (UserService.loginUser(login, password)) {
        case 1 -> {
          logger.info("logged in " + User.login);
          System.err.printf("You are logged in as %s\n", login);
          SimpleUserMenu.start();
        }
        case 2 -> {
          logger.info("logged in " + User.login);
          System.err.printf("You are logged in as %s\n", login);
          ManagerMenu.start();
        }
        case 3 -> {
          logger.info("logged in " + User.login);
          System.err.printf("You are logged in as %s\n", login);
          AdminMenu.start();
        }
        case -1 -> {
          logger.info("Login attempt failed");
          System.err.println("Wrong login/password!");
        }
      }
    } while (true);
  }

  private static void registrationMenu() {
    do {
      System.out.println("Enter login/password for registration, or 0 to return:");
      System.err.println("Login must contain only letters and have a length from 8 to 25");
      System.out.println("Login*");
      String login = scanner.next();
      if (login.equals("0")) {
        return;
      }
      System.err.println("Password must contain at least one uppercase letter, one lowercase, "
          + "one number and have a length from 8 to 25");
      System.out.println("Password*");
      String password = scanner.next();
      if (password.equals("0")) {
        return;
      }
      if (!UserService.hasUserLogin(login)) {
        if (UserService.registerUser(login, password)) {
          logger.info(login + " registered");
          System.err.println("You are registered!");
          return;
        } else {
          logger.info("Register attempt failed");
          System.err.println("Invalid login/password!");
        }
      } else {
        logger.info("Register attempt failed");
        System.err.println("User with such name exists!");
      }
    } while (true);
  }

}
