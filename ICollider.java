/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Point;

/**
 *
 * @author Spencer
 */
public interface ICollider {
    public boolean isCollidingWith(ICollider otherObject);
    public void handleCollision(ICollider otherObject);
    //Returns an array of points denoting the corners of the bounding box
    //Starts at top left and moves clockwise around to bottom left.
    public Point[] getBoundingBox();
}
