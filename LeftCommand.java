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
public class LeftCommand extends AbstractAction {

    private static LeftCommand leftCommand;
    private IGameWorld gw;

    private LeftCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static LeftCommand getLeftCommand() {
        if (leftCommand == null) {
            leftCommand = new LeftCommand();
        }
        return leftCommand;

    }

    public void actionPerformed(ActionEvent e) {
        gw.steerLeft();
    }
}
