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
public class SaveCommand extends AbstractAction {

    private static SaveCommand saveCommand;

    private SaveCommand() {
        super();
    }

    public static SaveCommand getSaveCommand() {
        if (saveCommand == null) {
            saveCommand = new SaveCommand();
        }
        return saveCommand;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("File -> Save pressed.");
    }
}
