/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author Spencer
 */
public class AboutCommand extends AbstractAction {

    private static AboutCommand aboutCommand;

    private AboutCommand() {
        super();
    }

    public static AboutCommand getAboutCommand() {
        if (aboutCommand == null) {
            aboutCommand = new AboutCommand();
        }
        return aboutCommand;
    }

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Created by: Spencer Dirrim\nFor CSC133"
                + "\nVersion 0.3");
    }
}
