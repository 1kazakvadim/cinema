package org.cinema.console;


import org.apache.log4j.Logger;
import org.cinema.model.User;
import org.cinema.service.FileService;
import org.cinema.service.FilmService;
import org.cinema.service.TicketService;

public class SimpleUserMenu extends ConsoleMenu implements Startable {

  private static final Logger logger = Logger.getLogger(ManagerMenu.class);

  public static void start() {
    do {
      System.out.println("1. Show all films");
      System.out.println("2. Show available tickets");
      System.out.println("3. Buy ticket");
      System.out.println("4. Show purchased tickets");
      System.out.println("5. Return ticket");
      System.out.println("6. Save tickets to file");
      System.out.println("7. back");
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
          logger.info("menu selected: Show available tickets");
          showAvailableTicketMenu();
        }
        case 3 -> {
          logger.info("menu selected: Buy ticket");
          buyTicketMenu();
        }
        case 4 -> {
          logger.info("menu selected: Show purchased tickets");
          showAllPurchasedTicketMenu();
        }
        case 5 -> {
          logger.info("menu selected: Return ticket");
          returnTicketMenu();
        }
        case 6 -> {
          logger.info("menu selected: Save tickets to file");
          saveTicketMenu();
        }
        case 7 -> {
          logger.info(User.login + " logged out");
          return;
        }
      }
    } while (true);
  }

  private static void showAllFilmMenu() {
    FilmService.showAllFilm();
  }

  private static void showAvailableTicketMenu() {
    TicketService.showAllNotPurchasedTicket();
  }

  private static void buyTicketMenu() {
    do {
      System.out.println("Enter ticket`s ID to buy, or 0 to return:");
      while (!scanner.hasNextInt()) {
        System.err.println("Invalid value!");
        scanner.nextLine();
      }
      selection = scanner.nextInt();
      if (selection == 0) {
        break;
      }
      if (TicketService.hasTicketSold(selection)) {
        TicketService.buyTicket(User.id, selection);
        logger.info(String.format("bought a ticket: ID %s %s", selection,
            FilmService.getFilmNameByID(selection)));
        System.err.println("You bought a ticket!");
      } else {
        System.err.println("Ticket doesn't exist!");
      }
    } while (true);
  }

  private static void showAllPurchasedTicketMenu() {
    TicketService.showPurchasedUserTicket(User.login);
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
      if (TicketService.deleteTicket(selection, User.id)) {
        logger.info(String.format("return a ticket: ID %s %s", selection,
            FilmService.getFilmNameByID(selection)));
        System.err.println("Ticket returned!");
      } else {
        System.err.println("Ticket doesn't exist!");
      }
    } while (true);
  }

  private static void saveTicketMenu() {
    if (FileService.saveUserTicketToFile(User.id)) {
      System.err.println("File saved!");
    } else {
      System.err.println("No tickets to save!");
    }
  }

}
