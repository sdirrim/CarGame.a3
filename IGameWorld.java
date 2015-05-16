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
public interface IGameWorld {
    
    public Pylon makePylon(int i);
    
    public Pylon makePylon(int i, double x, double y);

    public Bird makeBird();

    public FuelCan makeFuelCan();

    public OilSlick makeOilSlick();

    public Car makePlayer(double x, double y);

    public Car makeNPC(double x, double y);

    public void printAll();

    public void addOilSlick();

    public void reColorAll();

    public void tick();

    public void accelerate();

    public void brake();

    public void steerLeft();

    public void steerRight();

    public void displayInfo();

    public int getTime();

    public int getLives();

    public int getPlayerLatestPylon();

    public int getPlayerFuel();

    public double getPlayerDamage();

    public GameCollection getCollection();

    public Car retrievePlayer();

    public boolean isSoundOn();

    public boolean toggleSound();

    public boolean isPaused();

    public void togglePause();
    
    public Sound getCarCrunchSound();
    
    public Sound getFuelGetSound();
}
