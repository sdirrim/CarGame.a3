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
public interface IObservable {
    public void addObserver(IObserver o);
    public void notifyObservers();
}
