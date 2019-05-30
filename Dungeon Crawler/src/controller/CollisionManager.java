/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Inventory.ItemBluePotion;
import Inventory.ItemSlot;
import Inventory.Potion;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import model.GameFigure;
import model.GameData;
import model.GameFigureWithHealth;
import model.SpikeyEnemy;
import model.Weapon;
import Inventory.ItemPotion;

public class CollisionManager {
    public static ArrayList<Integer> itemTracker = new ArrayList<Integer>();
    // Manages enemy body attacks
    public void processMeleeCollisions() {
        Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
        for (GameFigureWithHealth enemyWithHealth : Main.gameData.enemyFiguresWithHealth) {
            if (enemyWithHealth.getCollisionBox().intersects(gamerCollisionBox)) {
                if (enemyWithHealth instanceof Weapon) {
                    ((Weapon) enemyWithHealth).doDamageTo(GameData.shooter);
                }
                else{
                    GameData.shooter.takeDamage(4);
                }
            }
        }
        for(GameFigureWithHealth invulnerableEnemy : Main.gameData.invulnerableEnemies){
            if (invulnerableEnemy.getCollisionBox().intersects(gamerCollisionBox)) {
                if (invulnerableEnemy instanceof Weapon) {
                    ((Weapon) invulnerableEnemy).doDamageTo(GameData.shooter);
                }
                else{
                    GameData.shooter.takeDamage(4);
                }
            }
        }
    }

    // Manages close-range or long-range enemy attacks
    public void processEnemyAttackCollisions() {
        Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
        for (GameFigure enemy : Main.gameData.enemyFigures) {
            if (enemy.getCollisionBox().intersects(gamerCollisionBox)) {
                if (enemy instanceof Weapon) {
                    ((Weapon) enemy).doDamageTo(GameData.shooter);
                }
                else{
                    GameData.shooter.takeDamage(2);
                }
                enemy.goNextState();
            }
        }
    }

    // Manages item collisions
    public void processItemCollisions() {
        Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
        for (GameFigure item : Main.gameData.itemFigures) {
            if (item.getCollisionBox().intersects(gamerCollisionBox)) {
                System.out.print(item.getCollisionBox());
                if(item instanceof ItemPotion)
                {
                    item.goNextState();
                    itemTracker.add(1);
                    Animator.counter++;
                }
                if(item instanceof ItemBluePotion)
                {
                    item.goNextState();
                    itemTracker.add(2);
                }
                
            }
        }
    }
    
    // Manages ally short-ranged and long-ranged weapon attack
    public void processAllyWeaponCollisions() {
        for (GameFigure weaponAttackAlly : Main.gameData.friendFigures) { // friend figures are the weapons atm (missiles)
            Rectangle2D weaponCollisionBox = weaponAttackAlly.getCollisionBox();

            // Will be removed once all health enemies are migrated to enemyFiguresWithHealth
            for (GameFigure enemy : Main.gameData.enemyFigures) {
                if (weaponCollisionBox.intersects(enemy.getCollisionBox())) {
                    enemy.goNextState();
                    weaponAttackAlly.goNextState();
                }
            }

            for (GameFigureWithHealth enemyWithHealth : Main.gameData.enemyFiguresWithHealth) {
                if (weaponCollisionBox.intersects(enemyWithHealth.getCollisionBox())) {
                    
                    if(weaponAttackAlly instanceof Weapon){
                        ((Weapon) weaponAttackAlly).doDamageTo(enemyWithHealth);
                        weaponAttackAlly.goNextState();
                        enemyWithHealth.goNextState();
                        continue;
                    }
                    else{
                        enemyWithHealth.takeDamage(1);
                    }
                    if (!enemyWithHealth.stillHasHealth()) {
                        enemyWithHealth.goNextState();
                    }
                    weaponAttackAlly.goNextState();
                }
            }
        }
    }
    
    public void processStairsCollision(){
        if(Main.gameData.shooter.getCollisionBox().intersects(Main.gameData.stairs.getCollisionBox())){
            Main.gameData.goNextLevel();
        }
    }

}
