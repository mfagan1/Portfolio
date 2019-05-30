/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

public class DamageStrategyWithDelay implements DamageStrategy{
    
    private int tickDelay;
    private int damage;
    private HashMap<GameFigureWithHealth, Integer> collidedFigures;
    
    public DamageStrategyWithDelay(int delay, int damage){
        tickDelay = delay;
        this.damage = damage;
        collidedFigures = new HashMap<>();
    }
    
    @Override
    public void doDamageTo(GameFigureWithHealth target) {
        // if we haven't collided with this yet, add to the list and do dmg
        if(!collidedFigures.containsKey(target)){
            collidedFigures.put(target, 0); // 0 ticks since damage
            target.takeDamage(damage);
        }
        else{
            // if the determined number of ticks has passed
            if(collidedFigures.get(target) >= tickDelay){
                target.takeDamage(damage);
                collidedFigures.put(target, 0);
                
            }
        }
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int newDamage) {
        damage = newDamage;
    }
    
    @Override
    public void update(){
        for(Map.Entry<GameFigureWithHealth, Integer> entry: collidedFigures.entrySet()){
            int entryTicks = entry.getValue();
            if(entryTicks < tickDelay){
                entry.setValue(entryTicks + 1);
            }
        }
    }
    
}
