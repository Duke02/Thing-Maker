/*     */ package com.duke.thing.gui;
/*     */ 
/*     */ import com.duke.thing.Player;
/*     */ import com.duke.thing.building.Building;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class BuildingPanel extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Building building;
/*     */   private DLabel name;
/*     */   private DLabel TPS;
/*     */   private DLabel cost;
/*     */   private DLabel TPB;
/*     */   private DLabel amount;
/*     */   private JButton buy10;
/*     */   private JButton buy1;
/*     */   private JButton sell;
/*     */   private JButton sellAll;
/*     */   private Building lastBuilding;
/*     */ 
/*     */   public BuildingPanel(int buildingIndex)
/*     */   {
/*  25 */     this(buildingIndex, 0, 0);
/*     */   }
/*     */ 
/*     */   public BuildingPanel(int buildingIndex, int x, int y)
/*     */   {
/*  30 */     setPreferredSize(new Dimension(400, 64));
/*  31 */     setLocation(x, y);
/*  32 */     setBuilding(Player.player.buildings[buildingIndex]);
/*  33 */     makeThings();
/*  34 */     this.lastBuilding = Building.NULL;
/*     */   }
/*     */ 
/*     */   public BuildingPanel(int buildingIndex, BuildingPanel lastPanel) {
/*  38 */     this(buildingIndex, lastPanel.getX(), lastPanel.getY() + lastPanel.getHeight());
/*  39 */     this.lastBuilding = lastPanel.getBuilding();
/*  40 */     if (lastPanel.getBuilding().numberOf == 0)
/*  41 */       setVisible(false);
/*     */   }
/*     */ 
/*     */   public void makeThings()
/*     */   {
/*  46 */     setLayout(null);
/*     */ 
/*  48 */     this.name = new DLabel(getBuilding().name);
/*  49 */     this.name.setBounds(getX() + 5, 5);
/*  50 */     add(this.name);
/*     */ 
/*  52 */     this.TPS = new DLabel("TPS: " + getBuilding().thingsPerBuilding);
/*  53 */     this.TPS.setBounds(this.name.getX(), this.name.getY() + this.name.getHeight());
/*  54 */     add(this.TPS);
/*     */ 
/*  56 */     this.cost = new DLabel("Cost: " + getBuilding().cost);
/*  57 */     this.cost.setBounds(this.name.getX(), this.TPS.getY() + this.TPS.getHeight());
/*  58 */     add(this.cost);
/*     */ 
/*  60 */     this.TPB = new DLabel("TPB: " + getBuilding().totalTPB);
/*  61 */     this.TPB.setBounds(this.TPS.getX() + this.TPS.getWidth(), this.TPS.getY());
/*  62 */     add(this.TPB);
/*     */ 
/*  64 */     this.amount = new DLabel("Amount: " + getBuilding().numberOf);
/*  65 */     this.amount.setBounds(this.TPB.getX(), this.name.getY());
/*  66 */     add(this.amount);
/*     */ 
/*  68 */     DLabel buy = new DLabel("Buy: ");
/*  69 */     buy.setBounds(this.name.getX(), this.cost.getY() + this.cost.getHeight());
/*  70 */     add(buy);
/*     */ 
/*  72 */     this.buy1 = new JButton("x1");
/*  73 */     this.buy1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  75 */         BuildingPanel.this.getBuilding().buy(1);
/*     */       }
/*     */     });
/*  78 */     this.buy1.setBounds(buy.getX() + buy.getWidth() + 5, buy.getY(), this.buy1.getText().length() * getFont().getSize(), getFont().getSize());
/*  79 */     add(this.buy1);
/*     */ 
/*  81 */     this.buy10 = new JButton("x10");
/*  82 */     this.buy10.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  84 */         BuildingPanel.this.getBuilding().buy(10);
/*     */       }
/*     */     });
/*  87 */     this.buy10.setBounds(this.buy1.getX() + this.buy1.getWidth() + 2, buy.getY(), this.buy10.getText().length() * getFont().getSize(), getFont().getSize());
/*  88 */     add(this.buy10);
/*     */ 
/*  90 */     this.sell = new JButton("Sell");
/*  91 */     this.sell.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  93 */         BuildingPanel.this.getBuilding().sell();
/*     */       }
/*     */     });
/*  96 */     this.sell.setBounds(getWidth() - 100, this.name.getY(), this.sell.getText().length() * getFont().getSize() - 10, getFont().getSize());
/*  97 */     add(this.sell);
/*     */ 
/*  99 */     this.sellAll = new JButton("Sell All");
/* 100 */     this.sellAll.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 102 */         BuildingPanel.this.getBuilding().sellAll();
/*     */       }
/*     */     });
/* 105 */     this.sellAll.setBounds(getWidth() - 150, this.TPS.getY(), this.sellAll.getText().length() * getFont().getSize() - 10, getFont().getSize());
/* 106 */     add(this.sellAll);
/*     */   }
/*     */ 
/*     */   public void displayThings() {
/* 110 */     this.amount.setText("Amount: " + Player.displayNumber(getBuilding().numberOf));
/* 111 */     this.cost.setText("Cost: " + Player.displayNumber(getBuilding().cost));
/* 112 */     this.TPB.setText("TPB: " + Player.displayNumber(getBuilding().totalTPB));
/* 113 */     this.TPS.setText("TPS: " + Player.displayNumber(getBuilding().thingsPerBuilding));
/* 114 */     if (this.lastBuilding.numberOf != 0)
/* 115 */       setVisible(true);
/*     */   }
/*     */ 
/*     */   public Building getBuilding() {
/* 119 */     return this.building;
/*     */   }
/*     */ 
/*     */   public void setBuilding(Building building) {
/* 123 */     this.building = building;
/*     */   }
/*     */ }

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.gui.BuildingPanel
 * JD-Core Version:    0.6.2
 */