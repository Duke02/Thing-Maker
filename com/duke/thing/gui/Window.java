/*     */ package com.duke.thing.gui;
/*     */ 
/*     */ import com.duke.thing.Player;
/*     */ import com.duke.thing.building.Building;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ public class Window extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 7958543725218831346L;
/*     */   public static final int WIDTH = 800;
/*     */   public static final int HEIGHT = 640;
/*     */   private JPanel contentPane;
/*     */   private JLabel lblThings;
/*     */   private JLabel lblThingsPerSec;
/*     */   private JPanel eastPanel;
/*     */   private JPanel upgradePanel;
/*  38 */   public static BuildingPanel[] buildingPanels = new BuildingPanel[Player.player.buildings.length];
/*     */   private JPanel buildings;
/*     */   private static JTextArea txtAlert;
/*     */ 
/*     */   public Window()
/*     */   {
/*  51 */     setResizable(false);
/*  52 */     setTitle("Thing Maker");
/*  53 */     setDefaultCloseOperation(3);
/*  54 */     setBounds(100, 100, 800, 640);
/*  55 */     this.contentPane = new JPanel();
/*  56 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  57 */     setContentPane(this.contentPane);
/*  58 */     this.contentPane.setLayout(null);
/*     */ 
/*  60 */     JPanel panel = new JPanel();
/*  61 */     panel.setBounds(0, 0, 400, 612);
/*  62 */     this.contentPane.add(panel);
/*  63 */     panel.setLayout(null);
/*     */ 
/*  65 */     JButton btnNewButton = new JButton("Make A Thing");
/*  66 */     btnNewButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/*     */         Player tmp3_0 = Player.player; tmp3_0.totalThings = ((int)(tmp3_0.totalThings + Player.player.thingsPerClick));
/*     */       }
/*     */     });
/*  72 */     btnNewButton.setBounds(138, 281, 130, 29);
/*  73 */     panel.add(btnNewButton);
/*     */ 
/*  75 */     this.lblThings = new JLabel("Things: " + Player.player.totalThings);
/*  76 */     this.lblThings.setBounds(200 - this.lblThings.getFont().getSize() * this.lblThings.getText().length() / 2, 160, 
/*  77 */       this.lblThings.getFont().getSize() * this.lblThings.getText().length(), 16);
/*  78 */     panel.add(this.lblThings);
/*     */ 
/*  80 */     this.lblThingsPerSec = new JLabel("Things Per Sec: " + Player.player.TPS);
/*  81 */     this.lblThingsPerSec.setBounds(
/*  82 */       200 - this.lblThingsPerSec.getFont().getSize() * this.lblThingsPerSec.getText().length() / 2, 
/*  83 */       180, this.lblThingsPerSec.getFont().getSize() * this.lblThingsPerSec.getText().length(), 16);
/*  84 */     panel.add(this.lblThingsPerSec);
/*     */ 
/*  86 */     JButton btnSaveGame = new JButton("Save Game");
/*  87 */     btnSaveGame.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*     */         try {
/*  91 */           Player.saveGame(Player.player, "Thing Maker V0.3.0 save.dat");
/*     */         } catch (IOException ex) {
/*  93 */           ex.printStackTrace();
/*     */         }
/*     */       }
/*     */     });
/*  97 */     btnSaveGame.setBounds(77, 526, 117, 29);
/*  98 */     panel.add(btnSaveGame);
/*     */ 
/* 100 */     JButton btnLoadGame = new JButton("Load Game");
/* 101 */     btnLoadGame.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 104 */         Player.loadGame();
/*     */       }
/*     */     });
/* 107 */     btnLoadGame.setBounds(207, 526, 117, 29);
/* 108 */     panel.add(btnLoadGame);
/*     */ 
/* 110 */     JButton btnNewGame = new JButton("New Game");
/* 111 */     btnNewGame.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 114 */         Player.player = new Player();
/*     */         try {
/* 116 */           Player.saveGame(Player.player, "Thing Maker V0.3.0 save.dat");
/* 117 */           Player.saveGame(Player.player, "Thing Maker V0.3.0 autosave.dat");
/*     */         } catch (IOException e1) {
/* 119 */           e1.printStackTrace();
/*     */         }
/* 121 */         Window.alert("Please close window and restart the game.");
/*     */       }
/*     */     });
/* 124 */     btnNewGame.setBounds(142, 567, 117, 29);
/* 125 */     panel.add(btnNewGame);
/*     */ 
/* 127 */     txtAlert = new JTextArea();
/* 128 */     txtAlert.setBounds(0, 0, 400, 93);
/* 129 */     panel.add(txtAlert);
/* 130 */     txtAlert.setText("ALERT BOX YAY\n");
/* 131 */     txtAlert.setRows(5);
/* 132 */     txtAlert.setEnabled(false);
/* 133 */     txtAlert.setEditable(false);
/*     */ 
/* 137 */     this.eastPanel = new JPanel();
/* 138 */     this.eastPanel.setBounds(400, 0, 400, 640);
/* 139 */     this.eastPanel.setLocation(400, 0);
/* 140 */     this.eastPanel.setLayout(null);
/* 141 */     this.contentPane.add(this.eastPanel);
/*     */ 
/* 144 */     this.buildings = new JPanel();
/* 145 */     this.buildings.setBounds(2, 2, 400, 612);
/* 146 */     GridLayout gl_buildings = new GridLayout(Player.player.buildings.length, 1);
/* 147 */     gl_buildings.setVgap(5);
/* 148 */     gl_buildings.setHgap(5);
/* 149 */     this.buildings.setLayout(gl_buildings);
/*     */ 
/* 151 */     buildingPanels[0] = new BuildingPanel(0, 0, 0);
/* 152 */     this.buildings.add(buildingPanels[0]);
/*     */ 
/* 154 */     for (int i = 1; i < buildingPanels.length; i++) {
/* 155 */       buildingPanels[i] = new BuildingPanel(i, buildingPanels[(i - 1)]);
/* 156 */       this.buildings.add(buildingPanels[i]);
/*     */     }
/*     */ 
/* 160 */     JScrollPane buildingsScroll = new JScrollPane(this.buildings, 22, 31);
/* 161 */     buildingsScroll.setBounds(0, 0, 400, 450);
/*     */ 
/* 163 */     buildingsScroll.getVerticalScrollBar().setSize(10, buildingsScroll.getHeight());
/* 164 */     buildingsScroll.setAutoscrolls(true);
/* 165 */     this.eastPanel.add(buildingsScroll);
/*     */ 
/* 167 */     this.upgradePanel = new JPanel();
/* 168 */     this.upgradePanel.setBounds(0, 440, 400, 165);
/* 169 */     this.upgradePanel.setLayout(new GridLayout(0, 8));
/* 170 */     this.upgradePanel.setBackground(Color.WHITE);
/* 171 */     this.eastPanel.add(this.upgradePanel);
/*     */ 
/* 173 */     for (int i = 0; i < Player.player.buildings.length; i++) {
/* 174 */       final Building b = Player.player.buildings[i];
/* 175 */       if (b == null) {
/* 176 */         System.out.println("Building not init'd properly. in upgrade Panel");
/*     */       }
/* 178 */       final UpgradeBox upgrade = new UpgradeBox(b.possibleUpgrades[b.lastUpgradeIndex], b);
/* 179 */       upgrade.setPreferredSize(new Dimension(50, 50));
/* 180 */       upgrade.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 182 */           upgrade.buy(b);
/*     */         }
/*     */       });
/* 185 */       this.upgradePanel.add(upgrade);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void alert(String text) {
/* 190 */     txtAlert.setText(text);
/*     */   }
/*     */ 
/*     */   public void displayThings() {
/* 194 */     this.lblThingsPerSec.setText("Things Per Second: " + Player.displayNumber(Player.player.TPS));
/* 195 */     this.lblThings.setText("Things: " + Player.displayNumber(Player.player.totalThings));
/* 196 */     for (BuildingPanel building : buildingPanels)
/* 197 */       building.displayThings();
/* 198 */     this.eastPanel.validate();
/* 199 */     this.eastPanel.repaint();
/*     */   }
/*     */ }

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.gui.Window
 * JD-Core Version:    0.6.2
 */