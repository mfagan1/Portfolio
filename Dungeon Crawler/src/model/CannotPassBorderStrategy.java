/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.geom.Rectangle2D;
import view.GamePanel;

public class CannotPassBorderStrategy implements Strategy {

    // TODO: Add this to GameFigure instead of a class-by-class basis
    private final int HEIGHT = 75;
    private final int WIDTH = 50;

    public CannotPassBorderStrategy() {

    }

    // NOTE ---------------------- RTFM -----------------------------
    // This strategy takes the future positional increment
    // then determines which direction it object is going
    // (THIS DOESN'T WORK WITH DIAGONAL DIRECTIONS!!!!!).
    // After determining the direction, it will check if any borders
    // are in the way. If the GameFigure is moving towards a border, it will
    // only let it move right up against the border. It will be stopped right
    // before merging with the border.
    @Override
    public void move(float dx, float dy, GameFigure context) {
        Direction direction;
        if (dx > 0 && dy == 0) {
            direction = Direction.EAST;
        } else if (dx < 0 && dy == 0) {
            direction = Direction.WEST;
        } else if (dx == 0 && dy < 0) {
            direction = Direction.NORTH;
        } else if (dx == 0 && dy > 0) {
            direction = Direction.SOUTH;
        } else {
            return;
        }
        if (GamePanel.width <= 0) {
            return;
        }

        float futureX = dx + context.x;
        float futureY = dy + context.y;

        for(Border border: Main.gameData.immutableGameBorders){
            if(moveDir(direction, border, futureX, futureY, context)){
                // need to stop this method if attempted border move is found
                return;
            }
        }
        for (Border border : Main.gameData.borders) {
            if(moveDir(direction, border, futureX, futureY, context)){
                // need to stop this method if attempted border move is found
                return;
            }

        }
    }

    private boolean moveDir(Direction direction, Border border, float futureX, float futureY, GameFigure context) {
        Rectangle2D borderBox = border.getCollisionBox();
        switch (direction) {
            case EAST:
                System.out.println();
                if (borderBox.intersects(futureX, context.y, WIDTH, HEIGHT)) {
                    context.x = (float) (borderBox.getX() - WIDTH);
                    return true;
                } else {
                    context.x = futureX;
                }
                break;
            case NORTH:
                if (borderBox.intersects(context.x, futureY, WIDTH, HEIGHT)) {
                    context.y = (float) (borderBox.getY() + borderBox.getHeight());
                    return true;
                } else {
                    context.y = futureY;
                }
                break;
            case SOUTH:
                if (borderBox.intersects(context.x, futureY, WIDTH, HEIGHT)) {
                    context.y = (float) (borderBox.getY() - HEIGHT);
                    return true;
                } else {
                    context.y = futureY;
                }
                break;
            case WEST:
                if (borderBox.intersects(futureX, context.y, WIDTH, HEIGHT)) {
                    context.x = (float) (borderBox.getX() + borderBox.getWidth());
                    return true;
                } else {
                    context.x = futureX;
                }
                break;
            default:
                break;
        }
        return false;
    }

}
