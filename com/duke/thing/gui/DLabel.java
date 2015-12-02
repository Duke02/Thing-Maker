/*    */ package com.duke.thing.gui;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ public class DLabel extends JLabel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public DLabel(String text)
/*    */   {
/* 13 */     super(text);
/*    */   }
/*    */ 
/*    */   public void setBounds(int x, int y)
/*    */   {
/* 18 */     setBounds(x, y, getText().length() * getFont().getSize(), getFont().getSize());
/*    */   }
/*    */ }

/* Location:           /Users/trystan/Downloads/Thing Maker.jar
 * Qualified Name:     com.duke.thing.gui.DLabel
 * JD-Core Version:    0.6.2
 */