/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;

public class DamageStrategyOncePerTarget implements DamageStrategy {

    private int damage;
    private HashSet<GameFigureWithHealth> collidedFigures;

    public DamageStrategyOncePerTarget(int damage) {
        this.damage = damage;
        collidedFigures = new HashSet<>();
    }

    @Override
    public void doDamageTo(GameFigureWithHealth target) {
        if (!collidedFigures.contains(target)) { // O(1) with HashSet
            collidedFigures.add(target);
            target.takeDamage(damage);
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
    public void update() {
        System.out.println("idk");
    }

}
