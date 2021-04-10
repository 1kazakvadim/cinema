package org.cinema.console;

import org.apache.log4j.Logger;
import org.cinema.model.User;
import org.cinema.service.FileService;
import org.cinema.service.FilmService;
import org.cinema.service.TicketService;
import org.cinema.service.UserService;

public class ManagerMenu extends ConsoleMenu implements Startable {

  private static final Logger logger = Logger.getLogger(ManagerMenu.class);

  public static void start() {
    do {
      System.out.println("1. Show all films");
      System.out.println("2. Edit film");
      System.out.println("3. Show all tickets");
      System.out.println("4. Sell ticket");
      System.out.println("5. Return ticket");
      System.out.println("6. Show purchased tickets");
      System.out.println("7. Save tickets to file");
      System.out.println("8. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1 -> {
          logger.info("menu selected: Show all films");
          showAllFilmMenu();
        }
        case 2 -> {
          logger.info("menu selected: Edit film");
          editFilmMenu();
        }
        case 3 -> {
          logger.info("menu selected: Show all tickets");
          showAllTicketMenu();
        }
        case 4 -> {
          logger.info("menu selected: Sell ticket");
          sellTicketMenu();
        }
        case 5 -> {
          logger.info("menu selected: Return ticket");
          returnTicketMenu();
        }
        case 6 -> {
          logger.info("menu selected: Show purchased tickets");
          showPurchasedTicketMenu();
        }
        case 7 -> {
          logger.info("menu selected: Save tickets to file");
          saveTicketMenu();
        }
        case 8 -> {
          logger.info(User.login + " logged out");
          return;
        }
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
        System.err.println("Invalid value!");
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

  private static void showAllTicketMenu() {
    TicketService.showAllTicket();
  }

  private static void sellTicketMenu() {
    do {
      System.out.println("Enter ticket`s ID and user`s login to sell, or 0 to return:");
      System.out.println("Ticket`s ID*");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      System.out.println("User`s login*");
      String login = scanner.next();
      if (login.equals("0")) {
        return;
      }
      if (TicketService.hasTicketSold(selection) && UserService.hasUserLogin(login)) {
        TicketService.buyTicket(UserService.getIdByLogin(login), selection);
        logger.info(String.format("sell a ticket: ID %d %s %s", selection,
            FilmService.getFilmNameByID(selection), login));
        System.err.println("You sell a ticket!");
      } else {
        System.err.println("Invalid ticket`s ID or user`s login!");
      }
    } while (true);
  }

  private static void returnTicketMenu() {
    do {
      System.out.println("Enter ticket`s ID to return, or 0 to return to the menu:");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (TicketService.hasTicketID(selection)) {
        if (!TicketService.hasTicketSold(selection)) {
          TicketService.deleteTicket(selection);
          logger.info(String.format("return a ticket: ID %d", selection));
          System.err.println("Ticket returned!");
        } else {
          System.err.println("Ticket has not sold!");
        }
      } else {
        System.err.println("Ticket doesn't exist!");
      }
    } while (true);
  }

  private static void showPurchasedTicketMenu() {
    do {
      System.out.println("1. Show all purchased tickets");
      System.out.println("2. Show purchased tickets by user`s ID");
      System.out.println("3. Show purchased tickets by user`s login");
      System.out.println("4. Show purchased tickets by film`s ID");
      System.out.println("5. Show purchased tickets by film`s name");
      System.out.println("6. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1:
          logger.info("menu selected: Show all purchased tickets");
          TicketService.showAllPurchasedTicket();
          break;
        case 2:
          logger.info("menu selected: Show purchased tickets by user`s ID");
          showPurchasedTicketByUserIDMenu();
          break;
        case 3:
          logger.info("menu selected: Show purchased tickets by user`s login");
          showPurchasedTicketByUserLoginMenu();
          break;
        case 4:
          logger.info("menu selected: Show purchased tickets by film`s ID");
          showPurchasedTicketByFilmIDMenu();
          break;
        case 5:
          logger.info("menu selected: Show purchased tickets by film`s name");
          showPurchasedTicketByFilmNameMenu();
          break;
        case 6:
          return;
      }
    } while (true);
  }

  private static void showPurchasedTicketByUserIDMenu() {
    do {
      System.out.println("Enter user`s ID to show tickets, or 0 to return:");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (UserService.hasUserID(selection)) {
        TicketService.showPurchasedUserTicket(selection);
        logger.info(String.format("all purchased tickets by user ID: %d", selection));
      } else {
        System.err.println("Invalid user`s ID!");
      }
    } while (true);
  }

  private static void showPurchasedTicketByUserLoginMenu() {
    do {
      System.out.println("Enter user`s login to show tickets, or 0 to return:");
      String login = scanner.next();
      if (login.equals("0")) {
        break;
      }
      if (UserService.hasUserLogin(login)) {
        TicketService.showPurchasedUserTicket(login);
        logger.info(String.format("all purchased tickets by user login: %s", login));
      } else {
        System.err.println("Invalid user`s login!");
      }
    } while (true);
  }

  private static void showPurchasedTicketByFilmIDMenu() {
    do {
      System.out.println("Enter film`s ID to show tickets, or 0 to return:");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (FilmService.hasFilmID(selection)) {
        TicketService.showPurchasedFilmTicket(selection);
        logger.info(String.format("all purchased tickets by film ID: %d", selection));
      } else {
        System.err.println("Invalid film`s ID!");
      }
    } while (true);
  }

  private static void showPurchasedTicketByFilmNameMenu() {
    do {
      System.out.println("Enter film`s name to show tickets, or 0 to return:");
      String name = scanner.next();
      if (name.equals("0")) {
        break;
      }
      if (FilmService.hasFilmName(name)) {
        TicketService.showPurchasedFilmTicket(name);
        logger.info(String.format("all purchased tickets by film name: %s", name));
      } else {
        System.err.println("Invalid film`s name!");
      }
    } while (true);
  }

  private static void saveTicketMenu() {
    do {
      System.out.println("1. Save all purchased tickets to file");
      System.out.println("2. Save purchased tickets by user`s ID");
      System.out.println("3. Save purchased tickets by user`s login");
      System.out.println("4. back");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      switch (selection) {
        case 1:
          logger.info("menu selected: Save all purchased tickets to file");
          saveAllPurchasedTicketMenu();
          break;
        case 2:
          logger.info("menu selected: Save purchased tickets by user`s ID");
          savePurchasedTicketByUserIDMenu();
          break;
        case 3:
          logger.info("menu selected: Save purchased tickets by user`s login");
          savePurchasedTicketByUserLoginMenu();
          break;
        case 4:
          return;
      }
    } while (true);
  }

  private static void saveAllPurchasedTicketMenu() {
    if (FileService.saveAllPurchasedTicketToFile()) {
      System.err.println("File saved!");
    } else {
      System.err.println("No tickets to save!");
    }
  }

  private static void savePurchasedTicketByUserIDMenu() {
    do {
      System.out.println("Enter user`s ID to save tickets, or 0 to return:");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (UserService.hasUserID(selection)) {
        if (FileService.saveUserTicketToFile(selection)) {
          logger.info(String.format("save purchased tickets by user ID: %d", selection));
          System.err.println("File saved!");
        } else {
          System.err.println("No tickets to save!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

  private static void savePurchasedTicketByUserLoginMenu() {
    do {
      System.out.println("Enter user`s login to save tickets, or 0 to return:");
      String login = scanner.next();
      if (login.equals("0")) {
        break;
      }
      if (UserService.hasUserLogin(login)) {
        if (FileService.saveUserTicketToFile(login)) {
          logger.info(String.format("save purchased tickets by user login: %s", login));
          System.err.println("File saved!");
        } else {
          System.err.println("No tickets to save!");
        }
      } else {
        System.err.println("Invalid value!");
      }
    } while (true);
  }

}
