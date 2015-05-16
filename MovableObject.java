/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;



/**
 *
 * @author Spencer
 */
public abstract class MovableObject extends GameObject {
    protected double heading;
    protected double speed;
    
    protected void setRandMotion(){
        heading = randomNum.nextInt(360);
        speed = randomNum.nextInt(10);
    }
    
    public void move(){
        x += Math.cos(heading)*speed;
        y += Math.sin(heading)*speed;
    }
}
