/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import controller.Main;

public abstract class GameFigureWithHealth extends GameFigure{
    protected int currentHealth;
    protected int maxHealth;
    protected boolean isInvulnerable; // cannot take damage
    
    public GameFigureWithHealth(float x, float y, int currentH, int maxH) {
        super(x, y);
        currentHealth = currentH;
        maxHealth = maxH;
        isInvulnerable = false;
    }
    public GameFigureWithHealth(float x, float y) {
        super(x, y);
        currentHealth = 100;
        maxHealth = 100;
    }
    
    public int getCurrentHealth(){
        return currentHealth;
    }
    
    public void takeDamage(int damage)
    {
        if(!isInvulnerable)
            currentHealth -= damage;
    }
    
    public void heal(int health)
    {
        currentHealth = (currentHealth + health) % maxHealth;
    }
    
    public boolean stillHasHealth(){
        return currentHealth >= 1;
    }
    
    public void setInvulnerability(boolean isInvulnerable){
        this.isInvulnerable = isInvulnerable;
    }
    
}
