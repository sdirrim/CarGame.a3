/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *
 * @author Spencer
 */
public abstract class GameObject implements IDrawable, ICollider, ISelectable {

    protected double x;
    protected double y;
    protected Color color;
    protected Random randomNum = new Random();
    protected boolean markedForRemoval = false;
    protected boolean isSelected = false;

    public Color getColor() {
        return color;
    }

    public void updateColor(Color newColor) {
        color = newColor;
    }

    protected void setRandPos() {
        x = randomNum.nextInt(700);
        y = randomNum.nextInt(700);
    }

    public Point2D.Double getPosition() {
        return new Point2D.Double(x, y);
    }
    
    public void draw(Graphics g){
        //not used on its own
    }
    
    public boolean isToBeRemoved(){
        return markedForRemoval;
    }
    
    public void removeFromWorld(){
        markedForRemoval = true;
    }

    public void select() {
        isSelected = true;
    }

    public void deselect() {
       isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
