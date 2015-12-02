/*    */ package com.duke.thing.gui;
/*    */ 
/*    */ import com.duke.thing.Player;
/*    */ import com.duke.thing.building.Building;
/*    */ import com.duke.thing.upgrades.Upgrade;
/*    */ import java.awt.Container;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ public class UpgradeBox extends JButton
/*    */ {
/*    */   private static final long serialVersionUID = 4012063398921360765L;
/*    */   private Upgrade upgrade;
/*    */ 
/*    */   public UpgradeBox(Upgrade u, int buildingIndex)
/*    */   {
/* 22 */     this(u, Player.player.buildings[buildingIndex]);
/*    */   }
/*    */ 
/*    */   public UpgradeBox(Upgrade u, Building building) {
/* 26 */     super(u.getName());
/*    */ 
/* 28 */     this.upgrade = u;
/*    */ 
/* 30 */     makeThings(building);
/*    */   }
/*    */ 
/*    */   public Upgrade getUpgrade() {
/* 34 */     return this.upgrade;
/*    */   }
/*    */ 
/*    */   public void makeThings(Building building) {
/* 38 */     setPreferredSize(new Dimension(50, 50));
/* 39 */     setText(building.ID + "U" + building.lastUpgradeIndex);
/*    */ 
/* 41 */     if (building.numberOf == 0) {
/* 42 */       setVisible(false);
/*    */     }
/*    */ 
/* 45 */     addMouseListener(new MouseListener()
/*    */     {
/*    */       public void mouseClicked(MouseEvent e)
/*    */       {
/*    */       }
/*    */ 
/*    */       public void mousePressed(MouseEvent e)
/*    */       {
/*    */       }
/*    */ 
/*    */       public void mouseReleased(MouseEvent e)
/*    */       {
/*    */       }
/*    */ 
/*    */       public void mouseEntered(MouseEvent e)
/*    */       {
/* 64 */         Window.alert(UpgradeBox.this.upgrade.toString());
/*    */       }
/*    */ 
/*    */       public void mouseExited(MouseEvent e)
/*    */       {
/* 69 */         Window.alert("");
/*    */       }
/*    */     });
/*    */   }
/*    */ 
/*    */   public void buy(Building b)
/*    */   {
/* 76 */     if (Player.player.totalThings > this.upgrade.getCost()) {
/* 77 */       b.buyUpgrade();
/* 78 */       setText(b.ID + "U" + b.lastUpgradeIndex);
/* 79 */       if (b.lastUpgradeIndex + 1 < b.possibleUpgrades.length) {
/* 80 */         this.upgrade.setPurchased(true);
/* 81 */         this.upgrade = b.getNextUpgrade();
/*    */       }
/* 83 */       else if (b.lastUpgradeIndex + 1 == b.possibleUpgrades.length) {
/* 84 */         getParent().remove(this);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.gui.UpgradeBox
 * JD-Core Version:    0.6.2
 */