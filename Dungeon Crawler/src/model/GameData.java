package model;

import Inventory.Inventory;
import Inventory.ItemBluePotion;
import Inventory.ItemPotion;
import Inventory.ItemSlot;
import Inventory.Potion;
import controller.Main;
import view.GamePanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

public class GameData {

    private int currentLevel = 3;

    private final int RADIUS = 6;
    public final List<GameFigure> enemyFigures;
    public final List<GameFigureWithHealth> enemyFiguresWithHealth;
    public final List<GameFigureWithHealth> invulnerableEnemies;
    public final List<GameFigure> friendFigures;
    public final List<GameFigure> itemFigures;
    public final List<GameFigure> weaponAttackFigures;
    public final List<Border> borders;
    public final List<Border> immutableGameBorders;
    public final List<Inventory> inventory;
    public final HealthBar health;
    public final ShooterManaBar mana;
    public Pause pauseScreen;
    public static Shooter shooter;
    public BossHealthBar bossHealth;
    private IGameState state;
    public static Stairs stairs;

    public LevelDataManager levelManager;

    public GameData() {
        enemyFigures = new CopyOnWriteArrayList<>();
        friendFigures = new CopyOnWriteArrayList<>();
        borders = new CopyOnWriteArrayList<>();
        immutableGameBorders = new CopyOnWriteArrayList<>();
        health = new HealthBar();
        mana = new ShooterManaBar();
        pauseScreen = new Pause();
        inventory = new CopyOnWriteArrayList<>();
        itemFigures = new CopyOnWriteArrayList<>();
        weaponAttackFigures = new CopyOnWriteArrayList<>();
        enemyFiguresWithHealth = new CopyOnWriteArrayList<>();
        state = new GameActiveState();
        invulnerableEnemies = new CopyOnWriteArrayList<>();


        levelManager = new LevelDataManager(this);
        // GamePanel.width, height are known when rendered. 
        // Thus, at this moment,
        // we cannot use GamePanel.width and height.
        shooter = new Shooter(0, Main.WIN_HEIGHT - 150);
        stairs = new Stairs(Main.WIN_WIDTH - 50, Main.WIN_HEIGHT - 70);

        immutableGameBorders.add(new Border(-51, 0, 50, Main.WIN_HEIGHT)); // left border
        immutableGameBorders.add(new Border(0, -51, Main.WIN_WIDTH, 50)); // top border
        immutableGameBorders.add(new Border(Main.WIN_WIDTH - 5, 0, 50, Main.WIN_HEIGHT)); // right border
        immutableGameBorders.add(new Border(0, Main.WIN_HEIGHT - 28, Main.WIN_WIDTH, 50)); //bottom border

        levelManager.generateLevelThree();

        //itemFigures.add(new ItemPotion(600, 600));
    }

    public void setGameState(IGameState state)
    {
        this.state = state;
    }

    public State getGameState()
    {
        return this.state.notifyModel();
    }

    // do not use
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

        }
    }

    // do not use
    public void addUFO() {
        enemyFigures.add(new FlyingSaucer((int) (Math.random() * GamePanel.width), (int) (Math.random() * GamePanel.height)));
    }
    
    //
    public void addGolem()
    {
        bossHealth = new BossHealthBar();
        enemyFiguresWithHealth.add(new GolemBoss(350, 150, 30, 30));
    }



    public void addSpikeyEnemy(int x, int y, Strategy movement) {
        SpikeyEnemy spikeyEnemy = new SpikeyEnemy(x, y);
        spikeyEnemy.movement = movement;
        enemyFiguresWithHealth.add(spikeyEnemy);
    }

    // Default
    // Moves East -> North -> West -> South
    // AKA Counter-Clockwise
    public void addSpikeyEnemy() {
        Random rand = new Random();
        int x = rand.nextInt(500) + 100;
        int y = rand.nextInt(500) + 100;
        addSpikeyEnemy(x, y, new RollOnBorderStrategy(Direction.EAST, false));
    }

    public void addMonsterEnemy(int x, int y) {
        enemyFigures.add(new MonsterEnemy(x, y));
    }

    public void addMonsterEnemy() {
        Random rand = new Random();
        int x = rand.nextInt(500) + 100;
        int y = rand.nextInt(500) + 100;
        addMonsterEnemy(x, y);
    }
    
    public void addGoblinEnemy()
    {
        Random rand = new Random();
        int x = rand.nextInt(500) + 100;
        int y = rand.nextInt(500) + 100;
        enemyFigures.add(new GoblinEnemy(x, y));
    }
    
    public void addPotion(float x, float y){
        itemFigures.add(new ItemPotion(x,y));
    }    
    
    public void addBluePotion(float x, float y){
        itemFigures.add(new ItemBluePotion(x,y));
    } 
    
    public void addInventory()
    {
        inventory.add(new Inventory(100,100));
    }

    public void removeInventory() {
        inventory.removeAll(inventory);
    }

    public void update() {
        updateFriends();
        updateEnemies();
        updateEnemiesWithHealth();
        updateInvulnerableEnemies();
        updateItems();
        shooter.update();
        updateStairs();
    }

    private void updateStairs() {
        if (enemyFiguresWithHealth.isEmpty() && enemyFigures.isEmpty()) {
            stairs.setVisibility(true);
        } else {
            stairs.setVisibility(false);
        }
    }

    private void updateItems() {
        ArrayList<GameFigure> removeItems = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < itemFigures.size(); i++) {
            f = itemFigures.get(i);
            if (f.state instanceof DoneFigureState) {
                removeItems.add(f);
            }
        }

        itemFigures.removeAll(removeItems);

        for (GameFigure g : itemFigures) {
            g.update();
        }
    }

    private void updateFriends() {
        GameFigure f;
        ArrayList<GameFigure> removeFriends = new ArrayList<>();
        for (int i = 0; i < friendFigures.size(); i++) {
            f = friendFigures.get(i);
            if (f.state instanceof DoneFigureState) {
                //if (f.state == GameFigureState.STATE_DONE) {
                removeFriends.add(f);
            }
        }
        friendFigures.removeAll(removeFriends);

        for (GameFigure g : friendFigures) {
            g.update();
        }
    }

    private void updateEnemiesWithHealth() {
        ArrayList<GameFigure> removeEnemiesWithHealth = new ArrayList<>();
        GameFigure f;
        Random rand = new Random();
        int n = rand.nextInt(10) + 1;
        for (int i = 0; i < enemyFiguresWithHealth.size(); i++) {
            f = enemyFiguresWithHealth.get(i);
            if (f.state instanceof DoneFigureState) {
                if (f instanceof ItemPotion) {
                } else {
                    if(n <=5)
                        addPotion(f.getX(), f.getY());
                    else
                       addBluePotion(f.getX(), f.getY()); 
                }
                removeEnemiesWithHealth.add(f);
            }
        }

        enemyFiguresWithHealth.removeAll(removeEnemiesWithHealth);

        for (GameFigure g : enemyFiguresWithHealth) {
            g.update();
        }
    }

    private void updateEnemies() {
        ArrayList<GameFigure> removeEnemies = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < enemyFigures.size(); i++) {
            f = enemyFigures.get(i);
            if (f.state instanceof DoneFigureState) {
                if (f instanceof ItemPotion) {
                } else {
                    //addPotion(f.getX(),f.getY());
                }
                removeEnemies.add(f);
            }
        }

        enemyFigures.removeAll(removeEnemies);

        for (GameFigure g : enemyFigures) {
            g.update();
        }
    }

    private void updateInvulnerableEnemies() {
        ArrayList<GameFigure> removeEnemies = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < invulnerableEnemies.size(); i++) {
            f = invulnerableEnemies.get(i);
            if (f.state instanceof DoneFigureState) {
                if (f instanceof ItemPotion) {
                } else {
                    //addPotion(f.getX(),f.getY());
                }
                removeEnemies.add(f);
            }
        }

        invulnerableEnemies.removeAll(removeEnemies);

        for (GameFigure g : invulnerableEnemies) {
            g.update();
        }
    }

    public void goNextLevel() {
        levelManager.goNextLevel();
    }

    public void resetGameArea() {
        enemyFigures.clear();
        friendFigures.clear();
        borders.clear();
        itemFigures.clear();
        enemyFiguresWithHealth.clear();
        invulnerableEnemies.clear();
    }
    
    // Clears all enemies, borders, and puts shooter in the bottom middle of the screen
    // Also moves the stairs out of the way
    // Used for debugging and demos
    public void debugResetArea(){
        enemyFigures.clear();
        friendFigures.clear();
        borders.clear();
        itemFigures.clear();
        enemyFiguresWithHealth.clear();
        invulnerableEnemies.clear();
        shooter.setPosition(Main.WIN_WIDTH/2, Main.WIN_HEIGHT/2 + 100);
        stairs.setPosition(-100, -100);
    }

}
