package controller;

import Inventory.Inventory;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Gunshot;
import model.Missile;
import model.Shooter;

public class MouseController extends MouseAdapter {

    public int x;
    public int y;

    @Override
    public void mousePressed(MouseEvent me) {

        int px = me.getX();
        int py = me.getY();
        Shooter shooter = Main.gameData.shooter;
        
        if(me.getButton() == MouseEvent.BUTTON1 && Inventory.isOpen){
            Inventory.a = px;
            Inventory.b = py;
            Inventory.update();
        }
        
        if(me.getButton() == MouseEvent.BUTTON1){ 
            shooter.shoot(px, py);
        }
        
        if(me.getButton() == MouseEvent.BUTTON3){
            shooter.strike();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

}
