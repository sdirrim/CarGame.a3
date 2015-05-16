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
public class AddOilCommand extends AbstractAction {

    private static AddOilCommand addOilCommand;
    private IGameWorld gw;

    private AddOilCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static AddOilCommand getAddOilCommand() {
        if (addOilCommand == null) {
            addOilCommand = new AddOilCommand();
        }
        return addOilCommand;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Splorp.");
        gw.addOilSlick();
    }
}
