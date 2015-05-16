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
public class ChangeStrategyCommand extends AbstractAction {

    private static ChangeStrategyCommand changeStrategyCommand;
    private IGameWorld gw;

    private ChangeStrategyCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static ChangeStrategyCommand getChangeStrategyCommand() {
        if (changeStrategyCommand == null) {
            changeStrategyCommand = new ChangeStrategyCommand();
        }
        return changeStrategyCommand;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Changed strategy.");
        IGameIterator iter = gw.getCollection().getIterator();
        while (iter.hasNext()) {
            iter.getNext();
            if (iter.get() instanceof Car) {
                ((Car)iter.get()).changeStrategy();
            }
        }
    }
}
