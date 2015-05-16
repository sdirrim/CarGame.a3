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
public class FuelCan extends FixedObject  {
    private int size;
    private final double width = 40;
    private final double height = 40;
    
    public FuelCan(){
        size = randomNum.nextInt(500);
        color = new Color(150,30,0);
        setRandPos();
    }
    
    public FuelCan(int makeSize, double startX, double startY){
        color = new Color(150,30,0);
        size = makeSize;
        x = startX;
        y = startY;
    }
    
    public int getSize(){
        return size;
    }
    
    public String toString(){
        return "FuelCan: loc="+x+","+y+" color=["+color.getRed()+","+color.getGreen()+","+color.getBlue()+"] size="+size;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        //g.drawRect((int)(x-20), (int)(y-20), 40, size);
        g.fillRect((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
        if(isSelected){
            g.setColor(Color.BLUE);
            g.drawRect((int)(x-width/2)-1, (int)(y-height/2)-1, (int)width+1, (int)height+1);
            g.drawRect((int)(x-width/2)-2, (int)(y-height/2)-2, (int)width+3, (int)height+3);
        }
        
    }
    
    public Point[] getBoundingBox() {
        Point[] pointArray = new Point[4];
        pointArray[0] = new Point((int)(x-width/2),(int)(y-height/2));
        pointArray[1] = new Point((int)(x+width/2),(int)(y-height/2));
        pointArray[2] = new Point((int)(x+width/2),(int)(y+height/2));
        pointArray[3] = new Point((int)(x-width/2),(int)(y+height/2));
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
    
    public void handleCollision(ICollider otherObject) {
        removeFromWorld();
        
    }
}
