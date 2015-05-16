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
public class AddFuelCanCommand extends AbstractAction {

    private static AddFuelCanCommand addFuelCanCommand;
    private IGameWorld gw;
    private Game shell;

    private AddFuelCanCommand(Game in) {
        super();
        shell = in;
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static AddFuelCanCommand getAddFuelCanCommand(Game in) {
        if (addFuelCanCommand == null) {
            addFuelCanCommand = new AddFuelCanCommand(in);
        }
        return addFuelCanCommand;
    }

    public void actionPerformed(ActionEvent e) {
        shell.addFuelCanAtMouse();
    }
}
