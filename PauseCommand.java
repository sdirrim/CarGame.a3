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
public class PauseCommand extends AbstractAction {

    private static PauseCommand pauseCommand;
    private IGameWorld gw;
    private Game shell;
    private GameButton parentButton;

    private PauseCommand(Game in) {
        super();
        shell = in;
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }
    
    public void setParent(GameButton button){
        parentButton = button;
    }

    public static PauseCommand getPauseCommand(Game in) {
        if (pauseCommand == null) {
            pauseCommand = new PauseCommand(in);
        }
        return pauseCommand;
    }

    public void actionPerformed(ActionEvent e) {
        if(shell.isPaused())
            parentButton.setText("Pause");
        else
            parentButton.setText("Play");
        shell.togglePause();
    }
}

