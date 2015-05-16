/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Spencer
 */
public class Bird extends MovableObject{
    private double radius;
    
    public Bird() {
        x = 300;
        y = 300;
        radius = 5 + randomNum.nextInt(5);
        color = new Color(20,20,20);
        setRandMotion();
    }
    
    public String toString(){
        return "Bird: loc="+x+","+y+" color=["+color.getRed()+","+color.getGreen()+","+color.getBlue()+"] heading="+heading+" speed="+speed;
    }
    
    public double getRadius(){
        return radius;
    }
    
    public void updateColor(Color nullColor)
    {
        //Color cannot be changed
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval((int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius));
        if(isSelected){
            g.setColor(Color.BLUE);
            g.drawOval((int)(x-radius)-1,(int)(y-radius)-1,(int)(2*radius)+2,(int)(2*radius)+2);
            g.drawOval((int)(x-radius)-2,(int)(y-radius)-2,(int)(2*radius)+4,(int)(2*radius)+4);
        }
    }

    public boolean isCollidingWith(ICollider otherObject) {
        boolean xCollide = false, yCollide = false;
        Point[] myPoints = this.getBoundingBox();
        Point[] otherPoints = otherObject.getBoundingBox();
        //top-bottom overlap
        if(myPoints[0].x<=otherPoints[2].x && myPoints[2].x>=otherPoints[0].x)
            xCollide = true;
        if(myPoints[0].y<=otherPoints[2].y && myPoints[2].y>=otherPoints[0].y)
            yCollide = true;
        return (xCollide && yCollide);
    }
    
    public void handleCollision(ICollider otherObject) {
        //just keep swimming
        
    }
    
    public Point[] getBoundingBox() {
        Point[] pointArray = new Point[4];
        pointArray[0] = new Point((int)(x-radius),(int)(y-radius));
        pointArray[1] = new Point((int)(x+radius),(int)(y-radius));
        pointArray[2] = new Point((int)(x+radius),(int)(y+radius));
        pointArray[3] = new Point((int)(x-radius),(int)(y+radius));
        return pointArray;
    }
}
