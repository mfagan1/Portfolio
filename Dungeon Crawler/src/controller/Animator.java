package controller;

import Inventory.ItemPotion;

import java.util.concurrent.TimeUnit;

import model.*;

public class Animator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 40;
    public static int counter;
    private CollisionManager collisionManager = new CollisionManager();
    @Override
    public void run() {
        counter = 0;
        while (running) {
            long startTime = System.currentTimeMillis();

            // If the game is in a paused state
            // we don't call update methods.
            if (!GameStaticState.isPaused()) {
                processCollisions();

                Main.gameData.update();
                Main.gamePanel.gameRender();
            }
            else
            {
                Main.gamePanel.renderPauseScreen();
            }

            Main.gamePanel.printScreen();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
        }
        //System.exit(0);
    }
    
    private void processCollisions(){
        collisionManager.processItemCollisions();
        collisionManager.processMeleeCollisions();
        collisionManager.processAllyWeaponCollisions();
        collisionManager.processEnemyAttackCollisions();
        collisionManager.processStairsCollision();
    }
    
    private void processCollisions2() {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method
        
        for(GameFigure friend : Main.gameData.friendFigures)
        {
            for(GameFigure enemy : Main.gameData.enemyFigures)
            {
//                if(enemy.getCollisionBox().intersects(friend.getCollisionBox()) && enemy.state == 1)
//                {
//                    enemy.state = GameFigureState.STATE_DYING;
//                    if(friend instanceof Missile)
//                    {
//                        friend.state = GameFigureState.STATE_DONE;
//                    }
//                }
                //Check to see if collision happens
                //probably need to add switch and cases inside for different states
                //depending on current state, update health and then determine if goNextState should be called
                
                if(enemy.getCollisionBox().intersects(friend.getCollisionBox()))
                {
                    if(enemy.state instanceof DieingFigureState)
                    { }
                    else if(friend.state instanceof DieingFigureState)
                    {
                        enemy.goNextState();
                    }
                
                    else
                    {
                        if(friend instanceof GameFigureWithHealth){
                            GameFigureWithHealth friendWithHealth = (GameFigureWithHealth) friend;
                            friendWithHealth.takeDamage(1);
                            if(!friendWithHealth.stillHasHealth()){
                                friendWithHealth.goNextState();
                            }
                            if(friend instanceof Shooter && enemy instanceof ItemPotion)
                            {
                                counter++;
                                enemy.goNextState();
                            }
                        }
                        else{
                            friend.goNextState();
                        }
                        if(enemy instanceof GameFigureWithHealth){
                            GameFigureWithHealth enemyWithHealth = (GameFigureWithHealth) enemy;
                            enemyWithHealth.takeDamage(1);
                            if(!enemyWithHealth.stillHasHealth()){
                                enemyWithHealth.goNextState();
                            }
                        }
                        else{
                            enemy.goNextState();
                        }
                    }
                    
                }
            }
        }
    }

  /*  
// Code to implement State design pattern
    
    for(GameFigure friend : Main.gameData.friendFigures)
        {
            for(GameFigure enemy : Main.gameData.enemyFigures)
            {
                if(enemy.getCollisionBox().intersects(friend.getCollisionBox()))
                {
                    //do nothing case, enemy is already dieing
                    if(enemy.state instanceof DyingFigureState)
                    { }
    //Need to add an updateHealth or adjustHealth method in each gamefigure
    //method will adjust health and call goNextState() if needed
    //health minus attack damage, adjust state and strategy if needed
    //for the larger enemies/main character each state should have range of health value
    //otherwise we will need too many states
                    else if(friend.state instanceof DyingFigureState)
                    {
                        enemy.goNextState();
                    }
                    else
                    {
                        enemy.goNextState();
                        friend.goNextState();
                    }
                }
            }
        }
*/
}
