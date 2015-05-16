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
public class Pylon extends FixedObject{
    private int sequenceNumber;
    private double radius;
    
    public Pylon(int seq){
        sequenceNumber = seq;
        radius = 20;
        setRandPos();
        color = new Color(100,0,100);
    }
    public Pylon(int seq, double rad){
        sequenceNumber = seq;
        radius = rad;
        setRandPos();
        color = new Color(100,0,100);
    }
    public Pylon(int seq, double rad, double setX, double setY){
        sequenceNumber = seq;
        radius = rad;
        x = setX;
        y = setY;
        color = new Color(100,0,100);
    }
    
    public void updateColor(Color nullColor)
    {
        //Color cannot be changed
    }
    public String toString(){
        return "Pylon: loc="+x+","+y+" color=["+color.getRed()+","+color.getGreen()+","+color.getBlue()+"] radius="+radius+" seqNum="+sequenceNumber;
    }
    
    public int getNum(){
        return sequenceNumber;
    }
    
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius));
        g.setColor(new Color(255,255,0));
        g.drawString(Integer.toString(sequenceNumber), (int)x-3, (int)y+3);
        if(isSelected){
            g.setColor(Color.BLUE);
            g.drawOval((int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius));
            g.drawOval((int)(x-radius)-1,(int)(y-radius)-1,(int)(2*radius)+2,(int)(2*radius)+2);
        }
    }
    
    public Point[] getBoundingBox() {
        Point[] pointArray = new Point[4];
        pointArray[0] = new Point((int)(x-radius),(int)(y-radius));
        pointArray[1] = new Point((int)(x+radius),(int)(y-radius));
        pointArray[2] = new Point((int)(x+radius),(int)(y+radius));
        pointArray[3] = new Point((int)(x-radius),(int)(y+radius));
        return pointArray;
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

    @Override
    public void handleCollision(ICollider otherObject) {
        //You must construct additional pylons
        //also they don't do anything when you hit them
    }
}
