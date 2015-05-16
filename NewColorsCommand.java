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
public class NewColorsCommand extends AbstractAction {

    private static NewColorsCommand newColorsCommand;
    private IGameWorld gw;

    private NewColorsCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static NewColorsCommand getNewColorsCommand() {
        if (newColorsCommand == null) {
            newColorsCommand = new NewColorsCommand();
        }
        return newColorsCommand;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Coloooor time!");
        gw.reColorAll();
    }
}
