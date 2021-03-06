package model;

import controller.Main;
import view.GamePanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private final int RADIUS = 6;
    public final List<GameFigure> enemyFigures;
    public final List<GameFigure> friendFigures;
    public static Shooter shooter;

    public GameData() {
        enemyFigures = new CopyOnWriteArrayList<>();
        friendFigures = new CopyOnWriteArrayList<>();

        // GamePanel.width, height are known when rendered. 
        // Thus, at this moment,
        // we cannot use GamePanel.width and height.
        shooter = new Shooter(Main.WIN_WIDTH / 2, Main.WIN_HEIGHT - 80);

        friendFigures.add(shooter);

        enemyFigures.add(new Bomber(50, 60));
        enemyFigures.add(new Bomber(400, 20));
    }

    public void add(int n) {
        for (int i = 0; i < n; i++) {
            
            float red = (float) Math.random();
            float green = (float) Math.random();
            float blue = (float) Math.random();
            // adjust if too dark since the background is black
            if (red < 0.5) {
                red += 0.2;
            }
            if (green < 0.5) {
                green += 0.2;
            }
            if (blue < 0.5) {
                blue += 0.2;
            }
            //enemyFigures.add(new Bomb(
           //         (int) (Math.random() * GamePanel.width),
           //         (int) (Math.random() * GamePanel.height),
           //         RADIUS,
            //        new Color(red, green, blue)));
        }
    }

    
    public void addUFO()
  {
    enemyFigures.add(new Bomber((int)(Math.random() * GamePanel.width), 
      (int)(Math.random() * GamePanel.height)));
  }

    public void update() {
        Random r = new Random();
        int lastAmount = 0;
        int i2 = r.nextInt(500 - 1) + 1;
        float red = (float) Math.random();

     if(i2 % 499 == 0)
    {
        Main.gameData.addUFO();
    }
    // spawn boss
    
    
    
    ArrayList<GameFigure> shootBombs = new ArrayList<>();
    GameFigure a;
    int arraySize = enemyFigures.size();
    for(int i = 0; i < arraySize; i++){
        a = enemyFigures.get(i);
        int i1 = r.nextInt(50 - 1) + 1;
        if(i1 % 49 == 0 && a.state == 10) {
                   enemyFigures.add(new Bomb(
                   (int) (a.getX()),
                   (int) (a.getY()),
                    RADIUS,
                    new Color((int) red)));
    }
    }
        // no enemy is removed in the program
        // since collision detection is not implemented yet.
        // However, if collision detected, simply set
        // f.state = GameFigure.STATE_DONE
        ArrayList<GameFigure> removeEnemies = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < enemyFigures.size(); i++) {
            f = enemyFigures.get(i);
            if (f.state == GameFigureState.STATE_DONE) {
                removeEnemies.add(f);
            }
         
        }
        enemyFigures.removeAll(removeEnemies);
        
        for (GameFigure g : enemyFigures) {
            g.update();
        }

        // missiles are removed if explosion is done
        ArrayList<GameFigure> removeFriends = new ArrayList<>();
        for (int i = 0; i < friendFigures.size(); i++) {
            f = friendFigures.get(i);
            if (f.state == GameFigureState.STATE_DONE) {
                removeFriends.add(f);
            }
        }
        friendFigures.removeAll(removeFriends);

        for (GameFigure g : friendFigures) {
            g.update();
        }
    }
}
