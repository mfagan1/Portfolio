/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actions.gameactions;

import Inventory.Inventory;
import controller.GameStaticState;
import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class inventoryAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        GameStaticState.setInventory();
        if(GameStaticState.isInventory()){
        Inventory.isOpen = true;
        Main.gameData.addInventory();
        }
        else
        {
            Inventory.isOpen = false;
            Main.gameData.removeInventory();
      
        }
    }
    
}

