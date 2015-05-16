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
public class AccelerateCommand extends AbstractAction {

    private static AccelerateCommand accelerateCommand;
    private IGameWorld gw;

    private AccelerateCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static AccelerateCommand getAccelerateCommand() {
        if (accelerateCommand == null) {
            accelerateCommand = new AccelerateCommand();
        }
        return accelerateCommand;
    }

    public void actionPerformed(ActionEvent e) {
        gw.accelerate();
    }
}
