/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.image.BufferedImage;

/**
 *
 * @author matthew
 */
public class Item {
    public String name;
    public BufferedImage texture;
    private ItemType itemType;
    
    public Item(String name, BufferedImage texture, ItemType itemType){
        this.name = name;
        this.texture = texture;
        this.itemType = itemType;
    }
    
    public ItemType getItemType(){
        return itemType;
    }
}
