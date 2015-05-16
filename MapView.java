/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Spencer
 */
public class MapView extends JPanel implements IObserver {

    IObservable world;
    int count;
    public MapView() {
        setBackground(Color.white);
        setBorder(new EtchedBorder());
        //world = o;
        count = 0;
        
    }

    public void update(IObservable o) {
        count++;
        world = o;
        //((IGameWorld) o).printAll();
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        IGameIterator iter = ((IGameWorld)world).getCollection().getIterator();
        while(iter.hasNext()){
            iter.getNext().draw(g);
        }
        //g.drawString(Integer.toString(count), 20, 20);
    }
    
    
}
