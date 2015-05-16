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
public class SoundCommand extends AbstractAction {

    private static SoundCommand soundCommand;
    private IGameWorld gw;

    private SoundCommand() {
        super();
    }

    public void setTarget(IGameWorld target) {
        gw = target;
    }

    public static SoundCommand getSoundCommand() {
        if (soundCommand == null) {
            soundCommand = new SoundCommand();
        }
        return soundCommand;
    }

    public void actionPerformed(ActionEvent e) {
        if(gw.isSoundOn())
            System.out.println("Muted.");
        else
            System.out.println("Unmuted.");
        gw.toggleSound();
    }
}
