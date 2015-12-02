package com.duke.thing;

import com.duke.thing.building.Building;
import com.duke.thing.gui.Window;
import java.io.IOException;
import java.io.PrintStream;

public class Game
  implements Runnable
{
  public void run()
  {
    if (!Player.savesExist()) {
      Player.player = new Player();
      Player.player.buildings = Player.player.initBuildings();
    } else {
      Player.player = Player.loadGame();
    }

    int diff = (int)((System.nanoTime() - Player.player.systemTime) / 1000000000.0D) / 60;
    System.out.println(diff);
    int offTimeEarning = 0;
    offTimeEarning = (int)Math.ceil(diff * Player.player.offlineRate * Player.player.TPS);
    Player.player.totalThings += offTimeEarning;

    Player.player.upgrades = Player.player.initUpgrades();
    Player.player.assignUpgrades();

    if (Player.player.upgrades == null)
      System.out.println("There are no upgrades.");
    else {
      System.out.println("There are upgrades.");
    }
    boolean buildingsHaveUpgrades = true;

    for (Building b : Player.player.buildings) {
      if (b.possibleUpgrades == null) {
        buildingsHaveUpgrades = false;
      }
    }
    if (!buildingsHaveUpgrades)
      System.out.println("Buildings do not have upgrades.");
    else {
      System.out.println("Buildings have upgrades.");
    }
    int counter = 0;
    Window window = new Window();
    window.setVisible(true);
    if (offTimeEarning != 0)
      Window.alert("Offtime Earning: " + Player.displayNumber(offTimeEarning));
    while (true) {
      counter++;
      Player.player.refreshTPS();
      Player.player.addThings();
      window.displayThings();
      if (counter == 10) {
        try {
          Player.saveGame(Player.player, "Thing Maker V0.3.0 autosave.dat");
          System.out.println("Autosaved");
        } catch (IOException e) {
          e.printStackTrace();
        }
        counter -= 10;
      }
      try {
        Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException1)
      {
      }
    }
  }
}

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.Game
 * JD-Core Version:    0.6.2
 */