/*     */ package com.duke.thing.building;
/*     */ 
/*     */ import com.duke.thing.Player;
/*     */ import com.duke.thing.gui.BuildingPanel;
/*     */ import com.duke.thing.gui.Window;
/*     */ import com.duke.thing.upgrades.Upgrade;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Building
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  16 */   public int numberOf = 0;
/*     */   public float thingsPerBuilding;
/*  18 */   public float totalTPB = this.numberOf * this.thingsPerBuilding;
/*     */   public String description;
/*     */   public String ID;
/*     */   public int cost;
/*     */   public Upgrade[] purchasedUpgrades;
/*     */   public Upgrade[] possibleUpgrades;
/*     */   public String name;
/*  25 */   public static final Building NULL = new Building("NULL", 0.0F, "NULL", 0);
/*  26 */   public int lastUpgradeIndex = 0;
/*     */ 
/*     */   public Building(String name, float TPB, String ID, int cost) {
/*  29 */     this.name = name;
/*  30 */     this.thingsPerBuilding = TPB;
/*  31 */     this.ID = ID;
/*  32 */     this.cost = cost;
/*     */   }
/*     */ 
/*     */   public BuildingPanel getBuildingPanel() {
/*  36 */     return Window.buildingPanels[getBuildingPanelIndex()];
/*     */   }
/*     */ 
/*     */   public int getBuildingPanelIndex() {
/*  40 */     for (int i = 0; i < Window.buildingPanels.length; i++) {
/*  41 */       if (Window.buildingPanels[i].getBuilding() == this)
/*  42 */         return i;
/*     */     }
/*  44 */     return 0;
/*     */   }
/*     */ 
/*     */   public void addUpgrades(Upgrade[] upgrades) {
/*  48 */     this.possibleUpgrades = upgrades;
/*     */ 
/*  53 */     this.purchasedUpgrades = new Upgrade[this.possibleUpgrades.length];
/*  54 */     for (int i = 0; i < this.purchasedUpgrades.length; i++)
/*  55 */       this.purchasedUpgrades[i] = Upgrade.NULL;
/*     */   }
/*     */ 
/*     */   public int getNumberOf()
/*     */   {
/*  61 */     return this.numberOf;
/*     */   }
/*     */   public void setNumberOf(int numberOf) {
/*  64 */     this.numberOf = numberOf;
/*     */   }
/*     */   public float getThingsPerBuilding() {
/*  67 */     return this.thingsPerBuilding;
/*     */   }
/*     */   public void setThingsPerBuilding(float thingsPerBuilding) {
/*  70 */     this.thingsPerBuilding = thingsPerBuilding;
/*     */   }
/*     */   public float getTotalTPB() {
/*  73 */     return this.totalTPB;
/*     */   }
/*     */   public void setTotalTPB(float totalTPB) {
/*  76 */     this.totalTPB = totalTPB;
/*     */   }
/*     */   public String getDescription() {
/*  79 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  82 */     this.description = description;
/*     */   }
/*     */   public String getID() {
/*  85 */     return this.ID;
/*     */   }
/*     */   public void setID(String iD) {
/*  88 */     this.ID = iD;
/*     */   }
/*     */   public int getCost() {
/*  91 */     return this.cost;
/*     */   }
/*     */   public void setCost(int cost) {
/*  94 */     this.cost = cost;
/*     */   }
/*     */ 
/*     */   public Upgrade getNextUpgrade() {
/*  98 */     return this.possibleUpgrades[this.lastUpgradeIndex];
/*     */   }
/*     */ 
/*     */   public void buyUpgrade() {
/* 102 */     if (this.possibleUpgrades[(this.lastUpgradeIndex + 1)].getCost() < Player.player.totalThings) {
/* 103 */       this.possibleUpgrades[(this.lastUpgradeIndex + 1)].buy(this);
/* 104 */       if (this.lastUpgradeIndex + 1 < this.possibleUpgrades.length)
/* 105 */         this.lastUpgradeIndex += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void buy(int amt) {
/* 110 */     if (Player.player.totalThings - this.cost >= 0) {
/* 111 */       for (int i = 0; i < amt; i++) {
/* 112 */         if (Player.player.totalThings - this.cost < 0)
/*     */           break;
/* 114 */         this.numberOf += 1;
/* 115 */         Player.player.totalThings -= this.cost;
/* 116 */         int tempCost = this.cost;
/* 117 */         this.cost = ((int)(this.cost * (1.2D + Math.round(Math.random() * 35.0D) / 100L)));
/* 118 */         this.cost += 3;
/* 119 */         if (tempCost > this.cost)
/* 120 */           this.cost = (tempCost + 3);
/*     */       }
/*     */     }
/* 123 */     refreshTPB();
/*     */   }
/*     */ 
/*     */   public void sell() {
/* 127 */     if (this.numberOf == 0)
/* 128 */       return;
/* 129 */     this.numberOf -= 1;
/* 130 */     Player.player.totalThings += this.cost;
/* 131 */     this.cost = ((int)(this.cost / (1.05D + Math.round(Math.random() * 10.0D) / 100L)));
/* 132 */     refreshTPB();
/*     */   }
/*     */ 
/*     */   public void sellAll()
/*     */   {
/* 137 */     int amt = this.numberOf;
/* 138 */     for (int i = amt; i > 0; i--)
/* 139 */       sell();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o) {
/* 143 */     Building b = (Building)o;
/* 144 */     if (b.ID.equals(this.ID))
/* 145 */       return true;
/* 146 */     return false;
/*     */   }
/*     */ 
/*     */   public void refreshTPB() {
/* 150 */     this.totalTPB = (this.numberOf * this.thingsPerBuilding);
/*     */ 
/* 157 */     Player.player.refreshTPS();
/*     */   }
/*     */ }

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.building.Building
 * JD-Core Version:    0.6.2
 */