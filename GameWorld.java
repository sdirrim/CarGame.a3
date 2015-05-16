/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Spencer
 */
public class GameWorld implements IObservable, IGameWorld {

    protected Random randomNum = new Random();
    protected GameCollection allObjects;
    protected int elapsedTime;
    protected int livesLeft;
    protected boolean soundOn;
    protected boolean isPaused;
    protected IGameIterator iter;
    protected Vector obs;

    //sounds
    Sound carCrunch;
    Sound fuelGet;
    Sound deathSplode;
    Sound bgMusic;

    public GameWorld() {
        livesLeft = 3;
        soundOn = false;
        obs = new Vector();
        carCrunch = new Sound("./Crunch.wav");
        fuelGet = new Sound("./Slurp.wav");
        bgMusic = new Sound("./Bach.wav");
        deathSplode = new Sound("./Death.wav");
    }

    //Factory Methods
    public Pylon makePylon(int i) {
        return new Pylon(i);
    }

    public Pylon makePylon(int i, double x, double y) {
        return new Pylon(i, 20, x, y);
    }

    public Bird makeBird() {
        return new Bird();
    }

    public FuelCan makeFuelCan() {
        return new FuelCan();
    }

    public FuelCan makeFuelCan(int size, double x, double y) {
        return new FuelCan(size, x, y);
    }

    public OilSlick makeOilSlick() {
        return new OilSlick();
    }

    public Car makePlayer(double x, double y) {
        return new Car(50, 100, x, y, true, new GameWorldProxy(this));
    }

    public Car makeNPC(double x, double y) {
        return new Car(50, 100, x, y, false, new GameWorldProxy(this));
    }

    public void initLayout(int pylons, int birds, int fuelcans, int oilslicks, int npcs) {
        elapsedTime = 0;
        isPaused = false;
        allObjects = new GameCollection();
        for (int i = 0; i < pylons; i++) {
            allObjects.addItem(makePylon(i));
        }
        for (int i = 0; i < birds; i++) {
            allObjects.addItem(makeBird());
        }
        for (int i = 0; i < fuelcans; i++) {
            allObjects.addItem(makeFuelCan());
        }
        for (int i = 0; i < oilslicks; i++) {
            allObjects.addItem(makeOilSlick());
        }
        iter = allObjects.getIterator();
        while (iter.hasNext()) {
            iter.getNext();
            if (iter.get() instanceof Pylon && ((Pylon) iter.get()).getNum() == 0) {
                double x = ((Pylon) iter.get()).getPosition().getX();
                double y = ((Pylon) iter.get()).getPosition().getY();
                allObjects.addItem(makePlayer(x, y));
                for (int i = 0; i < npcs; i++) {
                    int posOrNeg = 1;
                    if (randomNum.nextInt(2) == 0) {
                        posOrNeg = -1;
                    } else {
                        posOrNeg = 1; //decide positive or negative randomly
                    }
                    double tempX = x + (posOrNeg * (150 + randomNum.nextInt(200))); // appear 1.5 to 3.5 carlengths away
                    if (randomNum.nextInt(2) == 0) {
                        posOrNeg = -1;
                    } else {
                        posOrNeg = 1; //decide positive or negative randomly
                    }
                    double tempY = y + (posOrNeg * (150 + randomNum.nextInt(200))); // appear 1.5 to 3.5 carlengths away
                    Car c = makeNPC(tempX, tempY);
                    if (randomNum.nextBoolean()) { // randomly assign initial strategy
                        c.setStrategy(new ChaseStrategy(c, new GameWorldProxy(this)));
                    } else {
                        c.setStrategy(new RaceStrategy(c, new GameWorldProxy(this)));
                    }
                    allObjects.addItem(c);
                }
            }
        }

        notifyObservers();
    }

    public void printAll() {
        //System.out.println(player.toString());
        iter = allObjects.getIterator();
        while (iter.hasNext()) {
            System.out.println(iter.getNext().toString());
        }
    }

    public void addOilSlick() {
        allObjects.addItem(makeOilSlick());
        notifyObservers();
    }

    public void reColorAll() {
        /*for (int i = 0; i<allObjects.size(); i++)
         ((GameObject)allObjects.get(i)).updateColor(new Color(randomNum.nextInt(255),randomNum.nextInt(255),randomNum.nextInt(255)));
         player.updateColor(new Color(randomNum.nextInt(255),randomNum.nextInt(255),randomNum.nextInt(255)));*/
        iter = allObjects.getIterator();
        while (iter.hasNext()) {
            (iter.getNext()).updateColor(new Color(randomNum.nextInt(255), randomNum.nextInt(255), randomNum.nextInt(255)));
        }

        notifyObservers();
    }

    public void tick() {
        if (!isPaused) {
            elapsedTime += 20;
            iter = allObjects.getIterator();
            while (iter.hasNext()) {
                GameObject current = iter.getNext();
                IGameIterator iter2 = allObjects.getIterator();
                while (iter2.hasNext()) {
                    if (current.isCollidingWith(iter2.getNext()) && !(current.equals(iter2.get()))) {
                        current.handleCollision(iter2.get());

                    }
                }
                if (current instanceof Car) {
                    ((Car) current).move();
                    if (((Car) current).tellIfPlayer()) {
                        double wheelHeading = ((Car) current).getWheelHeading();
                        if (wheelHeading < 0) {
                            ((Car) current).steer(1);
                        }
                        if (wheelHeading > 0) {
                            ((Car) current).steer(-1);
                        }
                    }
                } else if (current instanceof Bird) {
                    ((Bird) current).move();
                }
            }
            if (retrievePlayer().getDamage() >= 100 || retrievePlayer().getFuel() <= 0) {
                if (retrievePlayer().getDamage() >= 100) {
                    livesLeft--;
                    if (soundOn) {
                        deathSplode.play();
                    }
                    JOptionPane.showMessageDialog(null, "You got killed!");
                } else if (retrievePlayer().getFuel() <= 0) {
                    livesLeft--;
                    JOptionPane.showMessageDialog(null, "You ran out of fuel!");
                }
                if (livesLeft > 0) {
                    initLayout(4, 2, 4, 2, 3);
                } else {
                    JOptionPane.showMessageDialog(null, "Game Over!");
                    System.exit(0);
                }

            }
            IGameIterator iter3 = allObjects.getIterator();

            notifyObservers();
            while (iter3.hasNext()) {
                iter3.getNext();
                if (iter3.get().isToBeRemoved()) {
                    iter3.remove();
                }
            }
        }
    }

    public void accelerate() {
        retrievePlayer().changeSpeed(.5);

        notifyObservers();
    }

    public void brake() {
        retrievePlayer().changeSpeed(-.5);

        notifyObservers();
    }

    public void steerLeft() {
        retrievePlayer().steer(-5);

        notifyObservers();
    }

    public void steerRight() {
        retrievePlayer().steer(5);
        notifyObservers();
    }

    public void displayInfo() {
        System.out.println("# of lives left: " + livesLeft);
        System.out.println("Elapsed time: " + elapsedTime);
        System.out.println("Highest pylon: " + retrievePlayer().getLatestPylon());
        System.out.println("Fuel and Damage (out of 100) - Fuel: " + retrievePlayer().getFuel() + " Damage: " + retrievePlayer().getDamage());
    }

    public int getTime() {
        return elapsedTime;
    }

    public int getLives() {
        return livesLeft;
    }

    public int getPlayerLatestPylon() {
        int latestPylon = 0;
        iter = allObjects.getIterator();
        while (iter.hasNext()) {
            iter.getNext();
            if (iter.get() instanceof Car && ((Car) iter.get()).tellIfPlayer()) {
                latestPylon = ((Car) iter.get()).getLatestPylon();
            }
        }
        return latestPylon;
    }

    public int getPlayerFuel() {
        return retrievePlayer().getFuel();
    }

    public double getPlayerDamage() {
        return retrievePlayer().getDamage();
    }

    public GameCollection getCollection() {
        return allObjects;
    }

    public Car retrievePlayer() {
        Car player = null;
        IGameIterator iter = allObjects.getIterator();
        while (iter.hasNext()) {
            iter.getNext();
            if (iter.get() instanceof Car && ((Car) iter.get()).tellIfPlayer()) {
                player = (Car) iter.get();
            }
        }
        return player;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public boolean toggleSound() {
        soundOn = !soundOn;
        if (!isPaused && isSoundOn()) {
            bgMusic.loop();
        } else {
            bgMusic.stop();
        }
        notifyObservers();
        return soundOn;
    }

    public void addObserver(IObserver o) {
        obs.add(o);
    }

    public void notifyObservers() {
        Object[] obsArray = obs.toArray();
        for (int i = 0; i <= obsArray.length - 1; i++) {
            ((IObserver) obsArray[i]).update((new GameWorldProxy(this)));
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void togglePause() {
        isPaused = !(isPaused);
        if (!isPaused && isSoundOn()) {
            bgMusic.loop();
        } else {
            bgMusic.stop();
        }
    }

    public Sound getCarCrunchSound() {
        return carCrunch;
    }

    public Sound getFuelGetSound() {
        return fuelGet;
    }
}
