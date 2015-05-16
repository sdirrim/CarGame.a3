/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

/**
 *
 * @author Spencer
 */
public class Car extends MovableObject {

    private int wheelHeading;
    private double maximumSpeed;
    private double width;
    private double height;
    private double damageLevel;
    private int maxDamage;
    private boolean hasTraction;
    private int fuelLevel;
    private int latestPylon;
    private boolean isPlayer;
    private IGameStrategy strategy;
    private IGameWorld world;
    private Vector collisions;
    private int CAR_IMPACT_DMG = 20;

    public Car(double customWidth, double customHeight, double setX, double setY, boolean player, IGameWorld gw) {

        fuelLevel = 1000;
        wheelHeading = 0;
        hasTraction = true;
        damageLevel = 0;
        width = customWidth;
        height = customHeight;
        maximumSpeed = 4.0;
        heading = 0;
        speed = 0;
        x = setX;
        y = setY;
        latestPylon = 0;
        color = new Color(100, 100, 100);
        isPlayer = player;
        if (isPlayer) {
            maxDamage = 100;
            strategy = null;
        } else {
            maxDamage = 400;
        }
        world = gw;
        collisions = new Vector();
    }

    public void steer(int direction) {
        if (fuelLevel > 0) {
            if (isPlayer) {
                wheelHeading += direction;
            } else {
                wheelHeading = direction;
            }
            //change this to 20
            if (wheelHeading < -20) {
                wheelHeading = -20;
            }
            if (wheelHeading > 20) {
                wheelHeading = 20;
            }
        }
    }

    public void move() {
        if (!isPlayer) {
            strategy.apply();
        }
        if (fuelLevel > 0) {
            if (hasTraction) {
                heading += wheelHeading;
                if (heading > 180) {
                    heading -= 360;
                } else if (heading < -180) {
                    heading += 360;
                }

            }

            if (speed > (maximumSpeed * ((maxDamage - damageLevel) / maxDamage))) {
                speed = maximumSpeed * ((maxDamage - damageLevel) / maxDamage);
            } else if (speed < 0) {
                speed = 0;
            }
            x += Math.cos(Math.toRadians(heading)) * speed;
            y += Math.sin(Math.toRadians(heading)) * speed;
        }
        burnFuel();
    }

    public String toString() {
        if (isPlayer) {
            return "Car: loc=" + x + "," + y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "] heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height
                    + "\n maxSpeed=" + maximumSpeed + " steeringDirection=" + wheelHeading + " fuelLevel=" + fuelLevel + " damage=" + damageLevel + " isPlayer=" + isPlayer;
        } else {
            return "Car: loc=" + x + "," + y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "] heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height
                    + "\n maxSpeed=" + maximumSpeed + " steeringDirection=" + wheelHeading + " fuelLevel=" + fuelLevel + " damage=" + damageLevel + " isPlayer=" + isPlayer + " Strategy=" + strategy.getClass();
        }
    }

    public void addFuel(int fuel) {
        fuelLevel += fuel;

        if (fuelLevel > 1000) {
            fuelLevel = 1000;
        }
    }

    public void loseTraction() {
        hasTraction = false;
    }

    public void returnTraction() {
        hasTraction = true;
    }

    public void hitPylon(int pylonNum) {
        if (latestPylon == pylonNum - 1) {
            latestPylon = pylonNum;
        }
    }

    public void injure(int damageAmount) {
        damageLevel += damageAmount;
        if (damageLevel > maxDamage) {
            damageLevel = maxDamage;
        }
    }

    public void changeSpeed(double speedChange) {
        if (hasTraction) {
            speed += speedChange;
            if (isPlayer) {
                if (speed > (maximumSpeed * ((100 - damageLevel) / 100))) {
                    speed = maximumSpeed * ((100 - damageLevel) / 100);
                } else if (speed < 0) {
                    speed = 0;
                }
            } else {
                if (speed > (maximumSpeed * ((400 - damageLevel) / 400))) {
                    speed = maximumSpeed * ((400 - damageLevel) / 400);
                } else if (speed < 0) {
                    speed = 0;
                }
            }
        }
    }

    public int getLatestPylon() {
        return latestPylon;
    }

    public int getFuel() {
        return fuelLevel;
    }

    public double getDamage() {
        return damageLevel;
    }

    public double getHeading() {
        return heading;
    }

    public double getMaxSpeed() {
        return maximumSpeed;
    }

    public void burnFuel() {
        if (fuelLevel > 0) {
            fuelLevel--;
        }
    }

    public boolean tellIfPlayer() {
        return isPlayer;
    }

    public void changeStrategy() {
        if (isPlayer) {
            //do nothing
        } else if (strategy instanceof ChaseStrategy) {
            strategy = new RaceStrategy(this, world);
        } else {
            strategy = new ChaseStrategy(this, world);
        }
    }

    public void setStrategy(IGameStrategy gs) {
        strategy = gs;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        double tempX = x + (Math.cos(Math.toRadians(heading)) * 30);
        double tempY = y + (Math.sin(Math.toRadians(heading)) * 30);
        if (isPlayer) {
            g.fillRect((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
        } else {
            g.drawRect((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
        }
        g.fillOval((int) tempX, (int) tempY, 10, 10);
        if (this.strategy instanceof ChaseStrategy) {
            ((ChaseStrategy) this.strategy).draw(g);
        }
        if (isSelected) {
            int tempMod = 0; //anti-optimization
            if (isPlayer) {
                tempMod = 1;
            }
            g.setColor(Color.BLUE);
            g.drawRect((int) (x - width / 2) - 1, (int) (y - height / 2) - 1, (int) width + 2 - tempMod, (int) height + 2 - tempMod);
            g.drawRect((int) (x - width / 2) - 2, (int) (y - height / 2) - 2, (int) width + 4 - tempMod, (int) height + 4 - tempMod);
        }
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

        if (!(xCollide && yCollide)) {
            collisions.remove(otherObject);
            if (otherObject instanceof OilSlick) {
                boolean otherOil = false;
                for (int i = 0; i < collisions.size(); i++) {
                    //search for other colliding oilslicks
                    if (collisions.elementAt(i) instanceof OilSlick) {
                        otherOil = true;
                    }
                }
                if (otherOil == false) {
                    returnTraction();
                }
            }
        }

        return (xCollide && yCollide);
    }

    public void handleCollision(ICollider otherObject) {
        boolean isAlreadyColliding = false;
        if (collisions.contains(otherObject)) {
            isAlreadyColliding = true;
        }

        if (!isAlreadyColliding) {
            collisions.add(otherObject);
            if (otherObject instanceof Car) {
                this.injure(CAR_IMPACT_DMG);
                if (!isPlayer && ((Car) otherObject).tellIfPlayer()) {
                    if (randomNum.nextInt(100) < 20) {
                        world.getCollection().addItem(new OilSlick(x, y));
                    }
                } else if (isPlayer && world.isSoundOn()) {
                    world.getCarCrunchSound().play();
                }
            } else if (otherObject instanceof Bird) {
                this.injure(CAR_IMPACT_DMG / 2);
            } else if (otherObject instanceof FuelCan) {
                if (isPlayer) {
                    if (world.isSoundOn()) {
                        world.getFuelGetSound().play();
                    }
                    addFuel(((FuelCan) otherObject).getSize());
                    world.getCollection().addItem(new FuelCan());
                }
            } else if (otherObject instanceof Pylon) {
                hitPylon(((Pylon) otherObject).getNum());
            } else if (otherObject instanceof OilSlick) {
                loseTraction();
            }
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

    public double getWheelHeading() {
        return wheelHeading;
    }

}
