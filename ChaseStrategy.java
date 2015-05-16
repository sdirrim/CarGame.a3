/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Spencer
 */
public class ChaseStrategy implements IGameStrategy {

    private Car self;
    private IGameWorld target;
    //debugging purposes
    double newHeading;

    public ChaseStrategy(Car c, IGameWorld gw) {
        self = c;
        target = gw;

    }

    public void apply() {

        //change heading to player's car
        double currentHeading = self.getHeading();
        double deltaX = target.retrievePlayer().getPosition().getX() - self.getPosition().getX();
        double deltaY = target.retrievePlayer().getPosition().getY() - self.getPosition().getY();
        newHeading = Math.toDegrees(Math.atan2(deltaY, deltaX));
        double rightTurn;
        double leftTurn;

        rightTurn = (newHeading - currentHeading);
        if (rightTurn < 0) 
            rightTurn += 360;
        leftTurn = 360 - rightTurn;

        if (leftTurn <= rightTurn) {
            //turn left
            self.steer(-15);
        } else {
            // turn right
            self.steer(15);
        }
        //self.steer((int) (newHeading - currentHeading));
        currentHeading = self.getHeading();

        //move at max speed towards it
        self.changeSpeed((int) self.getMaxSpeed());
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        double tempX = self.x + (Math.cos(Math.toRadians(newHeading)) * 30);
        double tempY = self.y + (Math.sin(Math.toRadians(newHeading)) * 30);
        g.fillOval((int) tempX, (int) tempY, 10, 10);
        g.drawString(Integer.toString((int) newHeading), (int) self.x, (int) self.y);
    }
}
