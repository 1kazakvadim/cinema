package org.cinema;

import org.apache.log4j.Logger;
import org.cinema.console.MainMenu;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class);

  public static void main(String[] args) {

    logger.info("program has started");
    MainMenu.start();


  }

}
