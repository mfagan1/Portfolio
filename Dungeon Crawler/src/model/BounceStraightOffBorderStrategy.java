/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.geom.Rectangle2D;

public class BounceStraightOffBorderStrategy implements Strategy {

    private final int UNIT_TRAVEL = 10;

    // starting direction
    private Direction direction;

    // TODO: Add this to GameFigure instead of a class-by-class basis
    private final int HEIGHT = 34;
    private final int WIDTH = 35;

    public BounceStraightOffBorderStrategy(Direction startingDirection) {
        direction = startingDirection;
    }

    @Override
    public void move(float x, float y, GameFigure context) {
        for (Border border : Main.gameData.immutableGameBorders) {
            if (moveDir(border, x, y, context)) {
                return;
            }
        }
        for (Border border : Main.gameData.borders) {
            if (moveDir(border, x, y, context)) {
                return;
            }
        }
    }

    private boolean moveDir(Border border, float x, float y, GameFigure context) {
        Rectangle2D borderBox = border.getCollisionBox();
        switch (direction) {
            case EAST:
                float futureXEast = x + UNIT_TRAVEL;
                System.out.println();
                if (borderBox.intersects(futureXEast, context.y, WIDTH, HEIGHT)) {
                    context.x = (float) (borderBox.getX() - WIDTH);
                    direction = Direction.WEST;
                    return true;
                } else {
                    context.x = futureXEast;
                }
                break;
            case NORTH:
                float futureYNorth = y - UNIT_TRAVEL;
                if (borderBox.intersects(context.x, futureYNorth, WIDTH, HEIGHT)) {
                    context.y = (float) (borderBox.getY() + borderBox.getHeight());
                    direction = Direction.SOUTH;
                    return true;
                } else {
                    context.y = futureYNorth;
                }
                break;
            case SOUTH:
                float futureYSouth = y + UNIT_TRAVEL;
                if (borderBox.intersects(context.x, futureYSouth, WIDTH, HEIGHT)) {
                    context.y = (float) (borderBox.getY() - HEIGHT);
                    direction = Direction.NORTH;
                    return true;
                } else {
                    context.y = futureYSouth;
                }
                break;
            case WEST:
                float futureXWest = x - UNIT_TRAVEL;
                if (borderBox.intersects(futureXWest, context.y, WIDTH, HEIGHT)) {
                    context.x = (float) (borderBox.getX() + borderBox.getWidth());
                    direction = Direction.EAST;
                    return true;
                } else {
                    context.x = futureXWest;
                }
                break;
            default:
                break;
        }
        return false;
    }

}
