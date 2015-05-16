/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Spencer
 */
public class Game extends JFrame implements MouseListener {

    protected Random randomNum = new Random();
    private GameWorld gw;
    protected GameCollection allObjects;
    protected IGameIterator iter;
    Timer tickTimer;
    Timer stratTimer;
    private final int DELAY_IN_MSEC = 20;
    MapView mapPanel;
    boolean isPaused;
    boolean pylonMouseFlag;
    boolean fuelCanMouseFlag;
    GameButton addPylonButton;
    GameButton addFuelCanButton;

    AccelerateCommand accelerateCommand;
    BrakeCommand brakeCommand;
    LeftCommand leftCommand;
    RightCommand rightCommand;
    AddOilCommand addOilCommand;
    NewColorsCommand newColorsCommand;
    QuitCommand quitCommand;
    ChangeStrategyCommand changeStrategyCommand;
    SoundCommand soundCommand;
    PauseCommand pauseCommand;
    AddPylonCommand addPylonCommand;
    AddFuelCanCommand addFuelCanCommand;
    NewCommand newCommand;
    SaveCommand saveCommand;
    AboutCommand aboutCommand;
    DeleteCommand deleteCommand;
    TickCommand tickCommand;
    GameWorldProxy fakeWorld;


    
    public Game() {
        //create main window
        setTitle("Car Game");
        setSize(1000, 800);
        setLocation(300, 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        isPaused = false;
        pylonMouseFlag = false;
        //create panels and menus
        ScoreView scorePanel = new ScoreView();
        JPanel commandPanel = new JPanel();
        //MapView mapPanel = new MapView(new GameWorldProxy(gw));
        mapPanel = new MapView();
        mapPanel.addMouseListener(this);

        //create world with 4 pylons, 2 birds, 4 fuelcans, 2 oilslicks, and 3 npc cars
        gw = new GameWorld();
        gw.addObserver(mapPanel);
        gw.addObserver(scorePanel);
        gw.initLayout(4, 2, 4, 2, 3);

        fakeWorld = new GameWorldProxy(gw);

        //Create Commands
        accelerateCommand = AccelerateCommand.getAccelerateCommand();
        accelerateCommand.setTarget(fakeWorld);
        brakeCommand = BrakeCommand.getBrakeCommand();
        brakeCommand.setTarget(fakeWorld);
        leftCommand = LeftCommand.getLeftCommand();
        leftCommand.setTarget(fakeWorld);
        rightCommand = RightCommand.getRightCommand();
        rightCommand.setTarget(fakeWorld);
        addOilCommand = AddOilCommand.getAddOilCommand();
        addOilCommand.setTarget(fakeWorld);
        newColorsCommand = NewColorsCommand.getNewColorsCommand();
        newColorsCommand.setTarget(fakeWorld);
        quitCommand = QuitCommand.getQuitCommand();
        changeStrategyCommand = ChangeStrategyCommand.getChangeStrategyCommand();
        changeStrategyCommand.setTarget(fakeWorld);
        soundCommand = SoundCommand.getSoundCommand();
        soundCommand.setTarget(fakeWorld);
        pauseCommand = PauseCommand.getPauseCommand(this);
        pauseCommand.setTarget(fakeWorld);
        deleteCommand = DeleteCommand.getDeleteCommand(this);
        deleteCommand.setTarget(fakeWorld);
        addPylonCommand = AddPylonCommand.getAddPylonCommand(this);
        addFuelCanCommand = AddFuelCanCommand.getAddFuelCanCommand(this);
        newCommand = NewCommand.getNewCommand();
        saveCommand = SaveCommand.getSaveCommand();
        aboutCommand = AboutCommand.getAboutCommand();
        tickCommand = TickCommand.getTickCommand();
        tickCommand.setTarget(fakeWorld);

        //Set Up Buttons Panel
        commandPanel.setBorder(new TitledBorder("Commands"));
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));

        //Create Buttons
        GameButton quitButton = new GameButton("Quit");
        quitButton.setAction(quitCommand);
        quitButton.setText("Quit");
        quitButton.setMnemonic('q');
        quitButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

        GameButton pauseButton = new GameButton("Pause");
        pauseButton.setAction(pauseCommand);
        pauseCommand.setParent(pauseButton);
        pauseButton.setText("Pause");
        pauseButton.setMnemonic('p');
        pauseButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

        GameButton deleteButton = new GameButton("Delete");
        deleteButton.setAction(deleteCommand);
        deleteButton.setText("Delete");
        deleteButton.setMnemonic('D');
        deleteButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

        addPylonButton = new GameButton("Add Pylon");
        addPylonButton.setAction(addPylonCommand);
        addPylonButton.setText("Add Pylon");
        addPylonButton.setMnemonic('y');
        addPylonButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

        addFuelCanButton = new GameButton("Add FuelCan");
        addFuelCanButton.setAction(addFuelCanCommand);
        addFuelCanButton.setText("Add FuelCan");
        addFuelCanButton.setMnemonic('y');
        addFuelCanButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        
        //add buttons to side panel
        commandPanel.add(quitButton);
        commandPanel.add(pauseButton);
        commandPanel.add(addPylonButton);
        commandPanel.add(addFuelCanButton);
        commandPanel.add(deleteButton);

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu commandMenu = new JMenu("Commands");
        menuBar.add(fileMenu);
        menuBar.add(commandMenu);

        //Create File Menu Items
        JMenuItem newEntry = new JMenuItem("New");
        newEntry.setAction(newCommand);
        newEntry.setText("New");

        JMenuItem saveEntry = new JMenuItem("Save");
        saveEntry.setAction(saveCommand);
        saveEntry.setText("Save");

        JMenuItem soundEntry = new JCheckBoxMenuItem("Sound");
        soundEntry.setAction(soundCommand);
        soundEntry.setText("Sound");

        JMenuItem aboutEntry = new JMenuItem("About");
        aboutEntry.setAction(aboutCommand);
        aboutEntry.setText("About");

        JMenuItem quitEntry = new JMenuItem("Quit");
        quitEntry.setAction(quitCommand);
        quitEntry.setText("Quit");

        //assemble file menu
        fileMenu.add(newEntry);
        fileMenu.add(saveEntry);
        fileMenu.add(soundEntry);
        fileMenu.add(aboutEntry);
        fileMenu.add(quitEntry);

        //Create Command Menu Items
        JMenuItem addOilEntry = new JMenuItem("Add Oil Slick");
        addOilEntry.setAction(addOilCommand);
        addOilEntry.setText("Add Oil Slick");

        JMenuItem newColorsEntry = new JMenuItem("Recolor Objects");
        newColorsEntry.setAction(newColorsCommand);
        newColorsEntry.setText("Recolor Objects");

        //assemble command menu
        commandMenu.add(addOilEntry);
        commandMenu.add(newColorsEntry);

        //add components to main window
        add(scorePanel, BorderLayout.NORTH);
        add(commandPanel, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
        setJMenuBar(menuBar);

        //create keybindings
        InputMap imap = mapPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        imap.put(KeyStroke.getKeyStroke('a'), "accelerate");
        imap.put(KeyStroke.getKeyStroke("UP"), "accelerate");
        imap.put(KeyStroke.getKeyStroke('b'), "brake");
        imap.put(KeyStroke.getKeyStroke("DOWN"), "brake");
        imap.put(KeyStroke.getKeyStroke('l'), "left");
        imap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        imap.put(KeyStroke.getKeyStroke('r'), "right");
        imap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        imap.put(KeyStroke.getKeyStroke('o'), "addoil");
        imap.put(KeyStroke.getKeyStroke('t'), "tick");
        imap.put(KeyStroke.getKeyStroke('q'), "quit");
        imap.put(KeyStroke.getKeyStroke("DELETE"), "delete");
        imap.put(KeyStroke.getKeyStroke('p'), "pause");

        mapPanel.getActionMap().put("accelerate", accelerateCommand);
        mapPanel.getActionMap().put("brake", brakeCommand);
        mapPanel.getActionMap().put("left", leftCommand);
        mapPanel.getActionMap().put("right", rightCommand);
        mapPanel.getActionMap().put("addoil", addOilCommand);
        mapPanel.getActionMap().put("quit", quitCommand);
        mapPanel.getActionMap().put("pause", pauseCommand);
        mapPanel.getActionMap().put("delete", deleteCommand);

        //disable certain commands because game starts in play mode
        addOilCommand.setEnabled(false);
        addPylonCommand.setEnabled(false);
        addFuelCanCommand.setEnabled(false);
        deleteCommand.setEnabled(false);

        //tick every 20 ms
        tickTimer = new Timer(DELAY_IN_MSEC, tickCommand);
        tickTimer.start();

        //change strategy
        stratTimer = new Timer(10000, changeStrategyCommand);
        stratTimer.start();

        //make the whole mess visible
        setVisible(true);
    }

    public void mouseClicked(MouseEvent e) { }

    public void mousePressed(MouseEvent e) {
        if (isPaused) {
            if (pylonMouseFlag) { //are we in "Add Pylon Mode"?
                int pylonNum = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the pylon to be added!"));
                gw.getCollection().addItem(gw.makePylon(pylonNum, e.getX(), e.getY()));
                pylonMouseFlag = false;
                addPylonButton.setText("Add Pylon");
                redrawMap();
            } else if (fuelCanMouseFlag) { //are we in "Add FuelCan Mode"?
                int fuelCanSize = Integer.parseInt(JOptionPane.showInputDialog("Enter the size of the fuel can to be added!\nRecommended 400-600"));
                gw.getCollection().addItem(gw.makeFuelCan(fuelCanSize, e.getX(), e.getY()));
                fuelCanMouseFlag = false;
                addFuelCanButton.setText("Add FuelCan");
                redrawMap();
            } else {
                if (e.isControlDown()) {
                    //multi select
                    IGameIterator iter2 = (gw.getCollection()).getIterator();
                    while (iter2.hasNext()) {
                        iter2.getNext();
                        ICollider current = iter2.get();
                        if (current.isCollidingWith(new Pylon(0, 2, e.getX(), e.getY()))) {
                            iter2.get().select();
                            redrawMap();
                        }
                    }
                } else {
                    //single select
                    IGameIterator iter2 = (gw.getCollection()).getIterator();
                    while (iter2.hasNext()) {
                        iter2.getNext();
                        ICollider current = iter2.get();
                        if (current.isCollidingWith(new Pylon(0, 2, e.getX(), e.getY()))) {
                            iter2.get().select();
                        } else {
                            iter2.get().deselect();
                        }
                        redrawMap();
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

    public void togglePause() {
        gw.togglePause();
        if (!isPaused) {
            isPaused = true;
            accelerateCommand.setEnabled(false);
            brakeCommand.setEnabled(false);
            leftCommand.setEnabled(false);
            rightCommand.setEnabled(false);
            addOilCommand.setEnabled(true);
            addPylonCommand.setEnabled(true);
            addFuelCanCommand.setEnabled(true);
            deleteCommand.setEnabled(true);
            tickTimer.stop();
            stratTimer.stop();
        } else {
            isPaused = false;
            accelerateCommand.setEnabled(true);
            brakeCommand.setEnabled(true);
            leftCommand.setEnabled(true);
            rightCommand.setEnabled(true);
            addOilCommand.setEnabled(false);
            addPylonCommand.setEnabled(false);
            addFuelCanCommand.setEnabled(false);
            deleteCommand.setEnabled(false);
            tickTimer.start();
            stratTimer.start();
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void addPylonAtMouse() {
        //allows clicking the button again to cancel
        
        
            fuelCanMouseFlag = false;
            

        if (!pylonMouseFlag) {
            pylonMouseFlag = true;
            addPylonButton.setText("Cancel Pylon Addition");
        } else {
            pylonMouseFlag = false;
            addPylonButton.setText("Add Pylon");
        }
    }

    public void addFuelCanAtMouse() {
        //allows clicking the button again to cancel

        if (!fuelCanMouseFlag) {
            fuelCanMouseFlag = true;
            //addPylonButton.setText("Cancel Pylon Addition");
        } else {
            fuelCanMouseFlag = false;
            //addPylonButton.setText("Add Pylon");
        }
    }

    public void redrawMap() {
        gw.notifyObservers();
    }
}
