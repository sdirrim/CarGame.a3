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
public class TickCommand extends AbstractAction {

    private static TickCommand tickCommand;
    private IGameWorld gw;

    private TickCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static TickCommand getTickCommand() {
        if (tickCommand == null) {
            tickCommand = new TickCommand();
        }
        return tickCommand;
    }

    public void actionPerformed(ActionEvent e) {
        gw.tick();
    }
}
