/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Spencer
 */
public class GameWorldProxy implements IObservable, IGameWorld {

    protected Random randomNum = new Random();
    protected GameCollection allObjects;
    protected int timer;
    protected int livesLeft;
    protected MapView map;
    protected ScoreView score;
    protected boolean soundOn;
    protected IGameIterator iter;

    GameWorld world;

    public GameWorldProxy(GameWorld input) {
        world = input;
    }
    
    public Pylon makePylon(int i) {
        return world.makePylon(i);
    }
    
    public Pylon makePylon(int i, double x, double y) {
        return world.makePylon(i, x, y);
    }

    public Bird makeBird() {
        return world.makeBird();
    }

    public FuelCan makeFuelCan() {
        return world.makeFuelCan();
    }

    public OilSlick makeOilSlick() {
        return world.makeOilSlick();
    }

    public Car makePlayer(double x, double y) {
        return world.makePlayer(x, y);
    }

    public Car makeNPC(double x, double y) {
        return world.makeNPC(x, y);
    }

    public void printAll() {
        world.printAll();
    }

    public void addOilSlick() {
        world.addOilSlick();
    }

    public void reColorAll() {
        world.reColorAll();
    }

    public void tick() {
        world.tick();
    }

    public void accelerate() {
        world.accelerate();
    }

    public void brake() {
        world.brake();
    }

    public void steerLeft() {
        world.steerLeft();
    }

    public void steerRight() {
        world.steerRight();
    }

    public void displayInfo() {
        world.displayInfo();
    }

    public int getTime() {
        return world.getTime();
    }

    public int getLives() {
        return world.getLives();
    }

    public int getPlayerLatestPylon() {
        return world.getPlayerLatestPylon();
    }

    public int getPlayerFuel() {
        return world.retrievePlayer().getFuel();
    }

    public double getPlayerDamage() {
        return world.retrievePlayer().getDamage();
    }

    public GameCollection getCollection() {
        return world.getCollection();
    }

    public Car retrievePlayer() {
        return world.retrievePlayer();
    }

    public boolean isSoundOn() {
        return world.isSoundOn();
    }

    public boolean toggleSound() {
        return world.toggleSound();
    }

    public void addObserver(IObserver o) {

    }

    public void notifyObservers() {

    }

    public boolean isPaused() {
        return world.isPaused();
    }

    public void togglePause() {
        world.togglePause();
    }
    
    public Sound getCarCrunchSound(){
        return world.getCarCrunchSound();
    }
    
    public Sound getFuelGetSound(){
        return world.getFuelGetSound();
    }
}
