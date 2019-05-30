/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//Use to implement strategy design pattern
//create concrete strategy for desired behavior of GameFigure

public interface Strategy {
    
    void move(float x, float y, GameFigure context);
    
}
