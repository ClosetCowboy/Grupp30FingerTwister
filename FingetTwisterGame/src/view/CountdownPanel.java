package view;

import controller.Controller;
import controller.GameMode2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CountdownPanel extends JPanel implements ActionListener  {

    private Timer timer;
    private JLabel countdownLabel;
    private Controller controller;
    private View view;
    private GameMode2 gm2;

    private int count = 5;

    public CountdownPanel(View view, GameMode2 gm2) {
        this.view = view;
        this.gm2 = gm2;
        countdownLabel = new JLabel();
        countdownLabel.setText("Countdown");

        this.setSize(300, 200);


        // Create the countdown label
        countdownLabel = new JLabel(String.valueOf(count));
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(64.0f));
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(countdownLabel);
    }

    public void startShortTimer(){
        count = 5;
        timer = new Timer(1000, new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 1) {
                    countdownLabel.setText(String.valueOf(count));
                }//else if(Objects.equals(countdownLabel.getText(), "0")){
                    //gm2.timeIsUpLoserEvent();
                //}
                 else
                {
                    timer.stop();
                    // view.getController().startGame();

                    countdownLabel.setText("0");
                }

            }
        });
        timer.start();
        setVisible(true);
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

    public JLabel getCountdownLabel() {
        return countdownLabel;
    }

    public void setCountdownLabel(JLabel countdownLabel) {
        this.countdownLabel = countdownLabel;
    }
}
