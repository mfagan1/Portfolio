package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Missile extends GameFigure {

    // missile size
    private static final int SIZE = 5;
    private static final int MAX_EXPLOSION_SIZE = 50;
    private float dx; // displacement at each frame
    private float dy; // displacement at each frame

    // public properties for quick access
    public Color color;
    public Point2D.Float target;

    private static final int UNIT_TRAVEL_DISTANCE = 4; // per frame move

    private int size = SIZE;

    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     * @param tx target x of the missile
     * @param ty target y of the missile
     * @param color color of the missile
     */
    public Missile(float sx, float sy, float tx, float ty, Color color) {
        super(sx, sy);
        super.state = GameFigureState.MISSILE_STATE_LAUNCHED;
        this.target = new Point2D.Float(tx, ty);
        this.color = color;

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
        if (state == GameFigureState.MISSILE_STATE_LAUNCHED) {
            updateLocation();
        } else if (state == GameFigureState.MISSILE_STATE_EXPLODED) {
            updateSize();
        }
    }

    public void updateLocation() {
        
        super.x += dx;
        super.y += dy;
    }

    public void updateSize() {
        size += 2;
    }

    public void updateState() {
        if (state == GameFigureState.MISSILE_STATE_LAUNCHED) {
            double distance = target.distance(super.x, super.y);
            boolean targetReached = distance <= 2.0;
            if (targetReached) {
                state = GameFigureState.MISSILE_STATE_EXPLODED;
            }
        } else if (state == GameFigureState.MISSILE_STATE_EXPLODED) {
            if (size >= MAX_EXPLOSION_SIZE) {
                state = GameFigureState.STATE_DONE;
            }
        }
    }

    //@Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x - size / 2, y - size / 2, size, size);
    }

}
