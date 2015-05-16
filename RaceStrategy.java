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
public class RaceStrategy implements IGameStrategy {

    Car self;
    IGameWorld target;
    double newHeading;

    public RaceStrategy(Car c, IGameWorld gw) {
        self = c;
        target = gw;

    }

    public void apply() {

        //change heading to next pylon
        double currentHeading = self.getHeading();
        IGameIterator iter = target.getCollection().getIterator();
        Pylon nextPylon = null;
        while (iter.hasNext()) {
            iter.getNext();
            if (iter.get() instanceof Pylon) {
                if (((Pylon) iter.get()).getNum() == self.getLatestPylon() + 1) { //if it is the next pylon
                    nextPylon = (Pylon) iter.get();
                }
            }
        }
        if (nextPylon == null) { //if there are no more pylons
            self.changeSpeed(0 - ((int) self.getMaxSpeed())); //stop at the finish line
            return;
        }

        double deltaX = nextPylon.getPosition().getX() - self.getPosition().getX();
        double deltaY = nextPylon.getPosition().getY() - self.getPosition().getY();
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
}
