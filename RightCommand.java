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
public class RightCommand extends AbstractAction {

    private static RightCommand rightCommand;
    private IGameWorld gw;

    private RightCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static RightCommand getRightCommand() {
        if (rightCommand == null) {
            rightCommand = new RightCommand();
        }
        return rightCommand;
    }

    public void actionPerformed(ActionEvent e) {
        gw.steerRight();
    }
}
