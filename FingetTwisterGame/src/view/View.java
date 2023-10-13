package view;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import controller.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class View extends JFrame{
    private Controller controller;
    private JLabel space;
    private GamePanel gamePanel;
    private CountdownPanel countDownPanel;
    private ScoreboardPanel scoreBoardPanel;
    private GameModePanel startingPanel;
    private boolean timesUp = false;

    /**
     * Constructs a View object.
     * @param controller the game controller
     */
    public View(Controller controller) {
        this.controller = controller;
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        createPanels();
        addPanels();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createPanels() {
        startingPanel = new GameModePanel(this, controller, controller.isGamemode());
        gamePanel = new GamePanel(this, controller);
        scoreBoardPanel = new ScoreboardPanel(this);
        space = new JLabel();
        space.setPreferredSize(new Dimension(550,800));
    }

    private void addPanels() {
        this.add((startingPanel),BorderLayout.NORTH);
        this.add((gamePanel),BorderLayout.CENTER);
        this.add(space,BorderLayout.WEST);
        if (!controller.isGamemode()){
            this.add((scoreBoardPanel),BorderLayout.WEST);
        }
    }

    /**
     * Returns the countdown panel of the game.
     * @return the countdown panel
     */
    public CountdownPanel getCountDownPanel() {
        if(countDownPanel == null) {
            countDownPanel = new CountdownPanel(this, controller.getGm2());
        }
        return countDownPanel;
    }

    public boolean isTimesUp() {
        return this.timesUp;
    }

    public void setTimesUp(boolean timesUp) {
        this.timesUp = timesUp;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Controller getController() {
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public GameModePanel getStartingPanel() {
        return startingPanel;
    }
}
