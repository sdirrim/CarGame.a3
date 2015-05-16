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
public class DeleteCommand extends AbstractAction {

    private static DeleteCommand deleteCommand;
    private IGameWorld gw;
    private Game shell;

    private DeleteCommand(Game in) {
        super();
        shell = in;
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static DeleteCommand getDeleteCommand(Game in) {
        if (deleteCommand == null) {
            deleteCommand = new DeleteCommand(in);
        }
        return deleteCommand;
    }

    public void actionPerformed(ActionEvent e) {
        IGameIterator iter = gw.getCollection().getIterator();
        while(iter.hasNext()){
            GameObject current = iter.getNext();
            if(current.isSelected)
                iter.remove();
        }
        shell.redrawMap();
    }
}

