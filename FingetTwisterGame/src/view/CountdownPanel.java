package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownPanel extends JPanel implements ActionListener  {

    private Timer timer;
    private JLabel countdownLabel;
    private Controller controller;
    private View view;



    private int count = 5;

    public CountdownPanel(View view) {
        this.view = view;
        countdownLabel = new JLabel();
        countdownLabel.setText("Countdown");

        this.setSize(300, 200);


        // Create the countdown label
        countdownLabel = new JLabel(String.valueOf(count));
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(64.0f));
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(countdownLabel);
    }


    // Create the timer
    public void startGameTimer() {
        count = 5;
        timer = new Timer(1000, new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 1) {
                    countdownLabel.setText(String.valueOf(count));
                } else {
                    timer.stop();
                    // view.getController().startGame();

                    countdownLabel.setText("0");

                }
            }
        });
        timer.start();
        setVisible(true);
    }

    /*
    public void startCountDown() {
        this.timer.start();
        this.thread = new Thread(this);
    }
*/
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
