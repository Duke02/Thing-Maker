/*     */ package com.duke.thing;
/*     */ 
/*     */ import com.duke.thing.building.Building;
/*     */ import com.duke.thing.gui.Window;
/*     */ import com.duke.thing.upgrades.Upgrade;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class Player
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   public int totalThings = 0;
/*     */ 
/*  29 */   public Building[] buildings = { 
/*  30 */     new Building("Artisan", 1.0F, "1A", 10), 
/*  31 */     new Building("Garden", 5.0F, "2B", 50), 
/*  32 */     new Building("Chemistry Kit", 10.0F, "2C", 200), 
/*  33 */     new Building("Water Mill", 25.0F, "3C", 600), 
/*  34 */     new Building("Plantation", 50.0F, "4D", 1300), 
/*  35 */     new Building("Factory", 100.0F, "5E", 3000), 
/*  36 */     new Building("Monopoly", 250.0F, "6F", 7500), 
/*  37 */     new Building("Space Program", 500.0F, "7G", 175000), 
/*  38 */     new Building("Alchemist", 600.0F, "8H", 400000), 
/*  39 */     new Building("HyperSpeed Rocket", 750.0F, "9I", 1000000), 
/*  40 */     new Building("Thing Planet", 1000.0F, "1B", 2500000), 
/*  41 */     new Building("Dimensional Teleporter", 1750.0F, "2C", 5100000) };
/*     */ 
/*  44 */   public Upgrade[][] upgrades = new Upgrade[this.buildings.length][2];
/*     */ 
/*  46 */   public float TPS = getBuildingTPS();
/*     */   public static final int frameRate = 60;
/*  48 */   public float thingsPerClickTPS = 0.0F;
/*  49 */   public float thingsPerClick = 1.0F + this.TPS * this.thingsPerClickTPS;
/*  50 */   public long systemTime = System.nanoTime();
/*  51 */   public float offlineRate = 0.5F;
/*     */   public static final String VERSION = "0.3.0";
/*  53 */   public final int maxUpgrades = this.upgrades[0].length;
/*     */   public static final String autoSave = "Thing Maker V0.3.0 autosave.dat";
/*     */   public static final String save = "Thing Maker V0.3.0 save.dat";
/*  60 */   public static Player player = new Player();
/*     */ 
/*     */   public Player()
/*     */   {
/*  64 */     this.buildings = initBuildings();
/*  65 */     this.upgrades = initUpgrades();
/*     */ 
/*  67 */     if (this.upgrades == null)
/*  68 */       System.out.println("Upgrades not loaded");
/*     */     else {
/*  70 */       System.out.println("Upgrades loaded");
/*     */     }
/*     */ 
/*  73 */     assignUpgrades();
/*     */ 
/*  75 */     if (this.buildings[0].possibleUpgrades == null)
/*  76 */       System.out.println("1st Building has no upgrades.");
/*     */     else {
/*  78 */       System.out.println("1st Building has upgrades");
/*     */     }
/*  80 */     if (this.buildings[(this.buildings.length - 1)].possibleUpgrades == null)
/*  81 */       System.out.println("Last buildings has no upgrades.");
/*     */     else {
/*  83 */       System.out.println("Last building has upgrades.");
/*     */     }
/*  85 */     System.out.println("Doing a thing");
/*     */   }
/*     */ 
/*     */   public void assignUpgrades() {
/*  89 */     for (int i = 0; i < this.buildings.length; i++)
/*  90 */       this.buildings[i].addUpgrades(this.upgrades[i]);
/*     */   }
/*     */ 
/*     */   public Building[] initBuildings() {
/*  94 */     Building[] b = { 
/*  95 */       new Building("Artisan", 1.0F, "1A", 10), 
/*  96 */       new Building("Garden", 5.0F, "2B", 50), 
/*  97 */       new Building("Chemistry Kit", 10.0F, "2C", 200), 
/*  98 */       new Building("Water Mill", 25.0F, "3C", 600), 
/*  99 */       new Building("Plantation", 50.0F, "4D", 1300), 
/* 100 */       new Building("Factory", 100.0F, "5E", 3000), 
/* 101 */       new Building("Monopoly", 250.0F, "6F", 7500), 
/* 102 */       new Building("Space Program", 500.0F, "7G", 175000), 
/* 103 */       new Building("Alchemist", 600.0F, "8H", 400000), 
/* 104 */       new Building("HyperSpeed Rocket", 750.0F, "9I", 1000000), 
/* 105 */       new Building("Thing Planet", 1000.0F, "1B", 2500000), 
/* 106 */       new Building("Dimensional Teleporter", 1750.0F, "2C", 5100000) };
/*     */ 
/* 108 */     return b;
/*     */   }
/*     */ 
/*     */   public Upgrade[][] initUpgrades() {
/* 112 */     Upgrade[][] upgradeS = { 
/* 113 */       { 
/* 115 */       new Upgrade("More Clients", 2.0F, false, 15), 
/* 116 */       new Upgrade("Better Tools", 2.0F, true, 25) }, 
/* 118 */       { 
/* 120 */       new Upgrade("Resizing", 3.0F, false, 55), 
/* 121 */       new Upgrade("Steel Trowel", 2.0F, true, 80) }, 
/* 123 */       { 
/* 125 */       new Upgrade("Sodium Bicarbonate", 7.0F, false, 180), 
/* 126 */       new Upgrade("Ion Tester", 2.0F, true, 225) }, 
/* 128 */       { 
/* 130 */       new Upgrade("Grain Production", 16.0F, false, 590), 
/* 131 */       new Upgrade("Tough Rivers", 2.0F, true, 650) }, 
/* 133 */       { 
/* 135 */       new Upgrade("Cash Crops", 45.0F, false, 1200), 
/* 136 */       new Upgrade("Family Operated", 2.25F, true, 1400) }, 
/* 138 */       { 
/* 140 */       new Upgrade("Pay Raise", 90.0F, false, 2850), 
/* 141 */       new Upgrade("Mass Production", 2.5F, true, 3150) }, 
/* 144 */       { 
/* 146 */       new Upgrade("No Bull Moose", 240.0F, false, 7400), 
/* 147 */       new Upgrade("No Tafty", 260.0F, false, 7650) }, 
/* 149 */       { 
/* 151 */       new Upgrade("Space Race", 450.0F, false, 170000), 
/* 152 */       new Upgrade("Titanium Rockets", 2.75F, true, 182000) }, 
/* 154 */       { 
/* 156 */       new Upgrade("Alchemy Journal", 590.0F, false, 385000), 
/* 157 */       new Upgrade("New Alchemy Set", 3.0F, true, 450000) }, 
/* 159 */       { 
/* 161 */       new Upgrade("Nuclear Fusion", 800.0F, false, 1500000), 
/* 162 */       new Upgrade("Galatic Crew", 2.0F, true, 1005000) }, 
/* 164 */       { 
/* 166 */       new Upgrade("Mining Operation", 900.0F, false, 2750000), 
/* 167 */       new Upgrade("Native Assimilation", 3.0F, true, 3000000) }, 
/* 169 */       { 
/* 171 */       new Upgrade("Coordinate System", 1000.0F, false, 4900000), 
/* 172 */       new Upgrade("Portal Realignment", 2.0F, true, 5100000) } };
/*     */ 
/* 176 */     return upgradeS;
/*     */   }
/*     */ 
/*     */   public float getBuildingTPS() {
/* 180 */     float total = 0.0F;
/* 181 */     for (Building b : this.buildings)
/* 182 */       total += b.getTotalTPB();
/* 183 */     return total;
/*     */   }
/*     */ 
/*     */   public void refreshTPS() {
/* 187 */     this.TPS = getBuildingTPS();
/*     */   }
/*     */ 
/*     */   public void addThings() {
/* 191 */     this.totalThings = ((int)(this.totalThings + this.TPS));
/*     */   }
/*     */ 
/*     */   public void buyBuilding(Building b, int amt) {
/* 195 */     int index = 0;
/* 196 */     for (int i = 0; i < this.buildings.length; i++) {
/* 197 */       if (b.equals(this.buildings[i])) {
/* 198 */         index = i;
/* 199 */         break;
/*     */       }
/*     */     }
/* 202 */     this.buildings[index].buy(amt);
/* 203 */     refreshTPS();
/*     */   }
/*     */ 
/*     */   public static void saveGame(Player obj, String filePath) throws IOException {
/* 207 */     ObjectOutputStream outputStream = null;
/*     */     try {
/* 209 */       outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
/* 210 */       outputStream.writeObject(obj);
/*     */     } catch (FileNotFoundException e) {
/* 212 */       System.out.println("File not found.");
/* 213 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 218 */         if (outputStream != null) {
/* 219 */           outputStream.flush();
/* 220 */           outputStream.close();
/*     */         }
/*     */       } catch (IOException e) {
/* 223 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 215 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 218 */         if (outputStream != null) {
/* 219 */           outputStream.flush();
/* 220 */           outputStream.close();
/*     */         }
/*     */       } catch (IOException e) {
/* 223 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 218 */         if (outputStream != null) {
/* 219 */           outputStream.flush();
/* 220 */           outputStream.close();
/*     */         }
/*     */       } catch (IOException e) {
/* 223 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Player loadGame(String filePath)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 234 */       FileInputStream fileIn = new FileInputStream(filePath);
/* 235 */       ObjectInputStream in = new ObjectInputStream(fileIn);
/* 236 */       Player p = (Player)in.readObject();
/* 237 */       in.close();
/* 238 */       return p;
/*     */     } catch (FileNotFoundException e) {
/* 240 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 242 */       e.printStackTrace();
/*     */     } catch (ClassNotFoundException e) {
/* 244 */       e.printStackTrace();
/*     */     }
/* 246 */     return null;
/*     */   }
/*     */ 
/*     */   public static String displayNumber(float number) {
/* 250 */     return NumberFormat.getNumberInstance(Locale.US).format(number);
/*     */   }
/*     */ 
/*     */   public static Player loadGame() {
/* 254 */     Player player = new Player();
/*     */     try {
/* 256 */       if ((new File("Thing Maker V0.3.0 save.dat").exists()) && 
/* 257 */         (new File("Thing Maker V0.3.0 autosave.dat").exists()))
/*     */       {
/* 259 */         if (loadGame("Thing Maker V0.3.0 save.dat").systemTime > 
/* 259 */           loadGame("Thing Maker V0.3.0 autosave.dat").systemTime)
/* 260 */           player = loadGame("Thing Maker V0.3.0 save.dat");
/*     */         else
/* 262 */           player = loadGame("Thing Maker V0.3.0 autosave.dat");
/* 263 */       } else if (new File("Thing Maker V0.3.0 save.dat").exists())
/* 264 */         player = loadGame("Thing Maker V0.3.0 save.dat");
/* 265 */       else if (new File("Thing Maker V0.3.0 autosave.dat").exists())
/* 266 */         player = loadGame("Thing Maker V0.3.0 autosave.dat");
/*     */     }
/*     */     catch (IOException ex) {
/* 269 */       Window.alert("Cannot load save.");
/*     */     }
/* 271 */     return player;
/*     */   }
/*     */ 
/*     */   public static boolean savesExist() {
/*     */     try {
/* 276 */       return (new File("Thing Maker V0.3.0 save.dat").exists()) || (new File("Thing Maker V0.3.0 autosave.dat").exists()); } catch (Exception e) {
/*     */     }
/* 278 */     return false;
/*     */   }
/*     */ }

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.Player
 * JD-Core Version:    0.6.2
 */