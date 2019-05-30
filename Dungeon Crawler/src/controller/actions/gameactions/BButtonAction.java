/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actions.gameactions;

import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Shortcut to GolemBoss, removes all enemies adds Golem, used for dev of GolemBoss
 * @author j
 */
public class BButtonAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Main.gameData.enemyFigures.clear();
        Main.gameData.enemyFiguresWithHealth.clear();
        //Main.gameData.addGolem();
        Main.gameData.levelManager.generateBossLevel();
    }
    
}
