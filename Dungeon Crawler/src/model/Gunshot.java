package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Gunshot extends GameFigure implements Weapon{

    // missile size
    private static final int SIZE = 5;
    private static final int MAX_EXPLOSION_SIZE = 50;
    private float dx; // displacement at each frame
    private float dy; // displacement at each frame

    // public properties for quick access
    public Color color;
    public Point2D.Float target;
    public static final int DAMAGE = 1;

    private static final int UNIT_TRAVEL_DISTANCE = 4; // per frame move

    private int size = SIZE;
    
    private DamageStrategy damageStrategy;

    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     * @param tx target x of the missile
     * @param ty target y of the missile
     * @param color color of the missile
     */
    public Gunshot(float sx, float sy, float tx, float ty, Color color) {
        super(sx, sy);
        super.state = new ActiveFigureState();
       //super.state = GameFigureState.STATE_ACTIVE;
        this.target = new Point2D.Float(tx, ty);
        this.color = color;

        damageStrategy = new DamageStrategyOncePerTarget(1);

        double angle = Math.atan2(ty - sy, tx - sx);
        dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle));
        dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle));
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(2)); // thickness of the line
        g.drawOval((int) (super.x - size / 2),
                (int) (super.y - size / 2),
                size, size);
    }

    @Override
    public void update() {
        updateState();
        if (state instanceof ActiveFigureState) {
            updateLocation();
        } else if (state instanceof DieingFigureState) {
            updateSize();
        }

    }

    public void updateLocation() {
        
        super.x += dx;
        super.y += dy;
        updateState();
    }

    public void updateSize() {
        size += 2;
        if(size >= MAX_EXPLOSION_SIZE)
            state.goNext(this);
    }

    public void updateState() {
        if (state instanceof ActiveFigureState) {
        //if (state == GameFigureState.STATE_ACTIVE) {
            double distance = target.distance(super.x, super.y);
            boolean targetReached = distance <= 2.0;
            if (targetReached) {
                goNextState();
                //state = GameFigureState.STATE_DYING;
            }
        } else if (state instanceof DieingFigureState) {
        //else if (state == GameFiguretate.STATE_DYING) {    
            if (size >= MAX_EXPLOSION_SIZE) {
                    goNextState();
                   // state = GameFigureState.STATE_DONE;
            }
        }
    }
    
        @Override
    public Rectangle2D.Float getCollisionBox()
    {
        return new Rectangle2D.Float(x - size/2, y - size/2, size*.9F, size*.9F);
    }
    
    @Override
    public void setPosition(float x, float y)
    {    }
    
    @Override
    public void goNextState()
    {
        state.goNext(this);
    }
    
    @Override
    public void setState(GameFigureState state)
    {
        this.state = state;
    }
    
    @Override
    public void doDamageTo(GameFigureWithHealth target) {
        damageStrategy.doDamageTo(target);
    }

    @Override
    public void setDamage(int newDamage) {
        damageStrategy.setDamage(newDamage);
    }

}
