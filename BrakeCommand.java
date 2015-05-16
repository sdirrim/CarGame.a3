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
public class BrakeCommand extends AbstractAction {

    private static BrakeCommand brakeCommand;
    private IGameWorld gw;

    private BrakeCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static BrakeCommand getBrakeCommand() {
        if (brakeCommand == null) {
            brakeCommand = new BrakeCommand();
        }
        return brakeCommand;

    }

    public void actionPerformed(ActionEvent e) {
        gw.brake();

    }
}
