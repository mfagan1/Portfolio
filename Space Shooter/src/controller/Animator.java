package controller;

import java.util.concurrent.TimeUnit;
import model.GameData;
import model.GameFigure;
import model.Missile;
import view.GamePanel;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import model.CollisionBox;

public class Animator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 40;

    @Override
    public void run() {

        while (running) {
            long startTime = System.currentTimeMillis();
            
            processCollisions();

            Main.gameData.update();
            Main.gamePanel.gameRender();
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
        System.exit(0);
    }
    
    private void processCollisions() {
    for (Iterator localIterator1 = Main.gameData.enemyFigures.iterator(); localIterator1.hasNext();)
    {
      GameFigure enemy;
      enemy = (GameFigure)localIterator1.next();
      for (GameFigure friend : Main.gameData.friendFigures) {
        if ((enemy.state == 10 || enemy.state ==20) && 
          (enemy.getCollisionBox().intersects(friend.getCollisionBox())))
        {
          enemy.state = 11;
          if ((friend instanceof Missile)) {
            friend.state = 2;
          }
        }
      }
    }
  
    }

}
