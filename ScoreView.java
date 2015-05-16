/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Spencer
 */
public class ScoreView extends JPanel implements IObserver, ActionListener {

    GameLabel timeLabel;
    GameLabel timeValue;
    GameLabel livesLabel;
    GameLabel livesValue;
    GameLabel pylonLabel;
    GameLabel pylonValue;
    GameLabel fuelLabel;
    GameLabel fuelValue;
    GameLabel damageLabel;
    GameLabel damageValue;
    GameLabel soundLabel;
    GameLabel soundValue;
    Timer timer;
    private final int DELAY_IN_MSEC = 1000;
    int currentTime;
    IGameWorld world;

    public ScoreView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        timeLabel = new GameLabel("Seconds Elapsed: ");
        timeValue = new GameLabel("0");
        livesLabel = new GameLabel("Lives Left: ");
        livesValue = new GameLabel("0");
        pylonLabel = new GameLabel("Most Recent Pylon: ");
        pylonValue = new GameLabel("0");
        fuelLabel = new GameLabel("Fuel Left: ");
        fuelValue = new GameLabel("0");
        damageLabel = new GameLabel("Damage Taken (out of 100): ");
        damageValue = new GameLabel("0");
        soundLabel = new GameLabel("Sound: ");
        soundValue = new GameLabel("OFF");
        add(Box.createHorizontalGlue());
        add(timeLabel);
        add(timeValue);
        add(Box.createHorizontalGlue());
        add(livesLabel);
        add(livesValue);
        add(Box.createHorizontalGlue());
        add(pylonLabel);
        add(pylonValue);
        add(Box.createHorizontalGlue());
        add(fuelLabel);
        add(fuelValue);
        add(Box.createHorizontalGlue());
        add(damageLabel);
        add(damageValue);
        add(Box.createHorizontalGlue());
        add(soundLabel);
        add(soundValue);
        add(Box.createHorizontalGlue());
        setBorder(new TitledBorder("Score View:"));
        currentTime = 0;
        timer = new Timer(DELAY_IN_MSEC, this);
        timer.start();
    }

    public void update(IObservable o) {
        world = (IGameWorld) o;
        livesValue.setText(Integer.toString(((IGameWorld) o).getLives()));
        pylonValue.setText(Integer.toString(((IGameWorld) o).getPlayerLatestPylon()));
        fuelValue.setText(Integer.toString(((IGameWorld) o).getPlayerFuel()));
        damageValue.setText(Double.toString(((IGameWorld) o).getPlayerDamage()));
        if (world.isSoundOn()) {
            soundValue.setText("ON");
        } else {
            soundValue.setText("OFF");
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (!world.isPaused()) {
            currentTime += 1;
            timeValue.setText(Integer.toString(currentTime));
        }
    }

}
