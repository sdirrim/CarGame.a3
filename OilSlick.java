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
public class OilSlick extends FixedObject {

    private double width;
    private double height;

    public OilSlick() {
        color = new Color(100, 100, 100);
        width = 20 + randomNum.nextInt(30);
        height = 20 + randomNum.nextInt(30);
        setRandPos();
    }

    public OilSlick(double inX, double inY) {
        color = new Color(100, 100, 100);
        width = 20 + randomNum.nextInt(30);
        height = 20 + randomNum.nextInt(30);
        x = inX;
        y = inY;
    }

    public String toString() {
        return "Oilslick: loc=" + x + "," + y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "] width=" + width + " height=" + height;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        //g.drawOval((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
        g.fillOval((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
        if(isSelected){
            g.setColor(Color.BLUE);
            g.drawOval((int)(x-width/2), (int)(y-height/2), (int)width, (int)height);
            g.drawOval((int)(x-width/2)-1, (int)(y-height/2)-1, (int)width+2, (int)height+2);
        }

    }

    public Point[] getBoundingBox() {
        Point[] pointArray = new Point[4];
        pointArray[0] = new Point((int) (x - width / 2), (int) (y - height / 2));
        pointArray[1] = new Point((int) (x + width / 2), (int) (y - height / 2));
        pointArray[2] = new Point((int) (x + width / 2), (int) (y + height / 2));
        pointArray[3] = new Point((int) (x - width / 2), (int) (y + height / 2));
        return pointArray;
    }

    public boolean isCollidingWith(ICollider otherObject) {
        boolean xCollide = false, yCollide = false;
        Point[] myPoints = this.getBoundingBox();
        Point[] otherPoints = otherObject.getBoundingBox();
        //top-bottom overlap
        if (myPoints[0].x <= otherPoints[2].x && myPoints[2].x >= otherPoints[0].x) {
            xCollide = true;
        }
        if (myPoints[0].y <= otherPoints[2].y && myPoints[2].y >= otherPoints[0].y) {
            yCollide = true;
        }
        return (xCollide && yCollide);
    }

    @Override
    public void handleCollision(ICollider otherObject) {
        //just keep... being oily

    }
}
