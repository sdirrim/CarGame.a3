/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;


import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Spencer
 */
public class NewCommand extends AbstractAction {

    private static NewCommand newCommand;

    private NewCommand() {
        super();
    }

    public static NewCommand getNewCommand() {
        if (newCommand == null) {
            newCommand = new NewCommand();
        }
        return newCommand;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("File -> New pressed.");
    }
}
