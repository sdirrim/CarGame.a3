/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;


import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JLabel;

/**
 *
 * @author Spencer
 */
public class GameLabel extends JLabel {
    
    public GameLabel(String s) {
        setText(s);
        this.setFont(Font.decode("Comic Sans MS-14"));
//        String[] arr = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//        for(int i = 0; i<arr.length; i++)
//            System.out.println(arr[i]);
    }
}
