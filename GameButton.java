/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;


import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author Spencer
 */
public class GameButton extends JButton {

    public GameButton(String s) {
        setMaximumSize(new Dimension(150, 30));
        setPreferredSize(new Dimension(150, 30));
        setMinimumSize(new Dimension(150, 30));
        setText(s);
    }

}
