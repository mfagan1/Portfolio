/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actions.gameactions;

import controller.Main;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import model.Border;
import model.Direction;
import model.RollOnBorderStrategy;
import model.SpikeyEnemy;

public class KButtonAction extends AbstractAction{
    public void actionPerformed(ActionEvent e) 
    {
        Main.gameData.debugResetArea();
        setupSpikeyEnemyDemo();
    }
    
    public void setupSpikeyEnemyDemo(){
        int xMin = Main.WIN_WIDTH/2 - 100;
        int xMax = Main.WIN_WIDTH/2 + 100;
        
        int yMin = 200;
        int yMax = 350;
        
        Random rand = new Random();
        int x1 = rand.nextInt((xMax-xMin) + 1) + xMin;
        int y1 = rand.nextInt((yMax-yMin) + 1) + yMin;
        
        int x2 = rand.nextInt((xMax-xMin) + 1) + xMin;
        int y2 = rand.nextInt((yMax-yMin) + 1) + yMin;

        SpikeyEnemy spikeyEnemy = new SpikeyEnemy(x1, y1);
        spikeyEnemy.setInvulnerability(true);
        spikeyEnemy.setDamage(20);
        
        Main.gameData.invulnerableEnemies.add(spikeyEnemy);
        Main.gameData.addSpikeyEnemy(x2, y2, new RollOnBorderStrategy(Direction.EAST, false));
        // top border
        Main.gameData.borders.add(new Border(Main.WIN_WIDTH/2 - 100 + 50, 100, 200, 10));
        // bottom border
        Main.gameData.borders.add(new Border(Main.WIN_WIDTH/2 - 100 - 50, 300+100, 200, 10));
        // left border
        Main.gameData.borders.add(new Border(Main.WIN_WIDTH/2 - 100 - 50, 100, 10, 200));
        // right border
        Main.gameData.borders.add(new Border(Main.WIN_WIDTH/2 - 100 + 200 + 50 - 10, 100 + 100 + 10, 10, 200));
    }
}
