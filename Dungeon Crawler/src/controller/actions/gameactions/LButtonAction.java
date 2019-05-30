/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actions.gameactions;

import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Border;
import model.BounceStraightOffBorderStrategy;
import model.Direction;
import model.RollOnBorderStrategy;

public class LButtonAction extends AbstractAction{
    public void actionPerformed(ActionEvent e) 
    {
        Main.gameData.debugResetArea();
        setupSpikeyEnemyDemo();
        setupSpikeyEnemyDemo2();
        setupSpikeyEnemyDemo3();
    }
    
    public void setupSpikeyEnemyDemo(){
        int startX = Main.WIN_WIDTH - 400;
        int startY = 10;
        int width = 300;
        int height = 10;
        Main.gameData.borders.add(new Border(startX, startY, width, height));
        Main.gameData.borders.add(new Border(startX, startY + width, width, height));
        Main.gameData.borders.add(new Border(startX, startY, height, width));
        Main.gameData.borders.add(new Border(startX + width - startY, startY, height, width));
        
        int x = startX + width/2 - height;
        int y = startY + width/2 - height;
        
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.EAST, false));
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.WEST, false));
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.NORTH, false));
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.SOUTH, false));
        
    }    
    public void setupSpikeyEnemyDemo2(){
        int startX = Main.WIN_WIDTH - 400;
        int startY = Main.WIN_HEIGHT/2;
        int width = 300;
        int height = 10;
        Main.gameData.borders.add(new Border(startX, startY, width, height));
        Main.gameData.borders.add(new Border(startX, startY + width, width, height));
        Main.gameData.borders.add(new Border(startX, startY, height, width));
        Main.gameData.borders.add(new Border(startX + width - height, startY, height, width));
        
        int x = startX + width/2 - height;
        int y = startY + width/2 - height;
        
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.EAST, true));
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.WEST, true));
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.NORTH, true));
        Main.gameData.addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.SOUTH, true));
        
    }    
    
    public void setupSpikeyEnemyDemo3(){
        int startX = Main.WIN_WIDTH/2 - 400;
        int startY = Main.WIN_HEIGHT/2 - 200;
        int width = 300;
        int height = 10;
        Main.gameData.borders.add(new Border(startX, startY, width, height));
        Main.gameData.borders.add(new Border(startX, startY + width, width, height));
        Main.gameData.borders.add(new Border(startX, startY, height, width));
        Main.gameData.borders.add(new Border(startX + width - height, startY, height, width));
        
        int x = startX + width/2 - height;
        int y = startY + width/2 - height;
        
        Main.gameData.addSpikeyEnemy(x - 30, y - 30, new BounceStraightOffBorderStrategy(Direction.NORTH));
        Main.gameData.addSpikeyEnemy(x + 30, y + 30, new BounceStraightOffBorderStrategy(Direction.SOUTH));
        Main.gameData.addSpikeyEnemy(x - 30, y + 30, new BounceStraightOffBorderStrategy(Direction.EAST));
        Main.gameData.addSpikeyEnemy(x + 30, y - 30, new BounceStraightOffBorderStrategy(Direction.WEST));
        
    }    

}
