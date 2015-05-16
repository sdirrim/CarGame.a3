/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;


import java.util.*;

/**
 *
 * @author Spencer
 */
public class GameCollection implements IGameCollection {

    private Vector gameObjects;
    private int numObjects;

    public GameCollection() {
        gameObjects = new Vector();
    }

    public void addItem(GameObject o) {
        gameObjects.addElement(o);
    }

    public IGameIterator getIterator() {
        return new GameIterator();
    }

    public int size() {
        return numObjects;
    }

    private class GameIterator implements IGameIterator {

        private int index;

        public GameIterator() {
            index = -1;
        }

        public boolean hasNext() {
            if (gameObjects.size() <= 0) {
                return false;
            } else if (index == gameObjects.size() - 1) {
                return false;
            } else {
                return true;
            }
        }

        public GameObject getNext() {
            index++;
            return (GameObject) gameObjects.elementAt(index);
        }
        
        public GameObject get() {
            return (GameObject) gameObjects.elementAt(index);
        }
        
        public void remove(){
            gameObjects.removeElementAt(index);
            index--;
        }
    }
}
