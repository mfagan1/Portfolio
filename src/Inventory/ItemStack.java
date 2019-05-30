/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

public class ItemStack {
    private int amount;
    private Item item;
    
    public ItemStack(Item item){
        this.item = item;
        this.amount = 1;
    }
    
    public ItemStack(Item item, int amount){
        this.item = item;
        this.amount = amount;
    }
    
    public Item getItem(){
        return item;
    }
    
    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
