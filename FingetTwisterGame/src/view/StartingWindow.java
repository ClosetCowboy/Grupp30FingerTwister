/*package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.*;

public class StartingWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem startItem;
    private JPanel scorePanel;
    private JLabel scoreLabel;
    private Controller controller;
    public StartingWindow(Controller controller) {
        super("My Game");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create the menu bar
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        startItem = new JMenuItem("Start");
        startItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              //  controller.startCountDown();
            }
        });
        gameMenu.add(startItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        // Create the score panel
        scorePanel = new JPanel();
        scoreLabel = new JLabel("Score: ");
        scorePanel.add(scoreLabel);
        getContentPane().add(scorePanel);

        setVisible(true);
    }

    public void closeWindow(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
*/