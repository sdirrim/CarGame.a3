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
public class QuitCommand extends AbstractAction {

    private static QuitCommand quitCommand;

    private QuitCommand() {
        super();
    }

    public static QuitCommand getQuitCommand() {
        if (quitCommand == null) {
            quitCommand = new QuitCommand();
        }
        return quitCommand;
    }

    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null,
                "Sure you want to exit ?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
