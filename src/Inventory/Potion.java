/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import Tools.Loader;
/**
 *
 * @author matthew
 */
public class Potion extends Item{
    
    public Potion(){
        super("Potion",new Loader().loadResource("inventory/potion", "png") ,ItemType.POTION);
    }
}
