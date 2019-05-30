/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author matthew
 */
public class ItemSlot {
    
    public static final int SLOTSIZE = 100;
    public int x;
    public int y;
    
    private ItemStack itemStack;
    
    public ItemSlot(int x, int y, ItemStack itemstack){
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
    }
    
    public void update(){
        
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.GRAY);
        g.fillRect(x,y,SLOTSIZE,SLOTSIZE);
        
        g.setColor(Color.BLACK);
        g.drawRect(x,y,SLOTSIZE,SLOTSIZE);
        
        if(itemStack != null){
            g.drawImage(itemStack.getItem().texture, x, y, SLOTSIZE, SLOTSIZE, null);
        }
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
    
    public void setItem(ItemStack item){
        this.itemStack = item;
    }
    public boolean addItem(Item item, int amount){
        if(itemStack == null){
            this.itemStack = new ItemStack(item,amount);
            return true;
        }
        return false;
    }
        
    public int getX(){
            return x;
        }
        
    public int getY(){
            return x;
        }
    }

