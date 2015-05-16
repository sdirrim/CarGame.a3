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
public class AddPylonCommand extends AbstractAction {

    private static AddPylonCommand addPylonCommand;
    private IGameWorld gw;
    private Game shell;

    private AddPylonCommand(Game in) {
        super();
        shell = in;
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static AddPylonCommand getAddPylonCommand(Game in) {
        if (addPylonCommand == null) {
            addPylonCommand = new AddPylonCommand(in);
        }
        return addPylonCommand;
    }

    public void actionPerformed(ActionEvent e) {
        shell.addPylonAtMouse();
    }
}
