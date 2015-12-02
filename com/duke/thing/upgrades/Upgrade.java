/*     */ package com.duke.thing.upgrades;
/*     */ 
/*     */ import com.duke.thing.Player;
/*     */ import com.duke.thing.building.Building;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Upgrade
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String name;
/*     */   private float effect;
/*     */   private boolean multiply;
/*     */   private int cost;
/*     */   private int index;
/*     */   private String ID;
/*     */   private boolean purchased;
/*  23 */   public static final Upgrade NULL = new Upgrade("NULL", 0.0F, false, 0);
/*     */ 
/*     */   public Upgrade(String name, float eff, boolean mult, int cost) {
/*  26 */     this.name = name;
/*  27 */     this.effect = eff;
/*  28 */     this.cost = cost;
/*  29 */     this.multiply = mult;
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  33 */     return this.name + "\nEffect: " + this.effect + "\nMultiplies: " + this.multiply + "\nCost: " + this.cost + "\nPurchased: " + isPurchased();
/*     */   }
/*     */ 
/*     */   public void buy(Building b) {
/*  37 */     if (Player.player.totalThings - this.cost < 0)
/*  38 */       return;
/*  39 */     Player.player.totalThings -= this.cost;
/*  40 */     if (this.multiply)
/*  41 */       b.thingsPerBuilding *= this.effect;
/*     */     else
/*  43 */       b.thingsPerBuilding += this.effect;
/*  44 */     b.refreshTPB();
/*  45 */     b.purchasedUpgrades[this.index] = this;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  49 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  53 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public float getEffect() {
/*  57 */     return this.effect;
/*     */   }
/*     */ 
/*     */   public void setEffect(float effect) {
/*  61 */     this.effect = effect;
/*     */   }
/*     */ 
/*     */   public boolean isMultiply() {
/*  65 */     return this.multiply;
/*     */   }
/*     */ 
/*     */   public void setMultiply(boolean multiply) {
/*  69 */     this.multiply = multiply;
/*     */   }
/*     */ 
/*     */   public int getCost() {
/*  73 */     return this.cost;
/*     */   }
/*     */ 
/*     */   public void setCost(int cost) {
/*  77 */     this.cost = cost;
/*     */   }
/*     */ 
/*     */   public int getIndex() {
/*  81 */     return this.index;
/*     */   }
/*     */ 
/*     */   public void setIndex(int index) {
/*  85 */     this.index = index;
/*     */   }
/*     */ 
/*     */   public String getID() {
/*  89 */     return this.ID;
/*     */   }
/*     */ 
/*     */   public void setID(String iD) {
/*  93 */     this.ID = iD;
/*     */   }
/*     */ 
/*     */   public boolean isPurchased() {
/*  97 */     return this.purchased;
/*     */   }
/*     */ 
/*     */   public void setPurchased(boolean purchased) {
/* 101 */     this.purchased = purchased;
/*     */   }
/*     */ }

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.upgrades.Upgrade
 * JD-Core Version:    0.6.2
 */