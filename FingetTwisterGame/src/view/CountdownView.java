package view;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.*;


public class CountdownView extends JFrame implements ActionListener {
    private Timer timer;
    private JLabel countdownLabel;
    private Controller controller;

    public CountdownView(Controller controller) {
        super("Countdown Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create the countdown label
        countdownLabel = new JLabel("5");
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(64.0f));
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(countdownLabel);

        // Create the timer
        timer = new Timer(1000, new ActionListener() {
            int count = 1;
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 1) {
                    countdownLabel.setText(Integer.toString(count));
                } else {
                    countdownLabel.setText("0");
                    timer.stop();
                    controller.startGame();
                    dispose();
                }
            }
        });
        timer.start();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
