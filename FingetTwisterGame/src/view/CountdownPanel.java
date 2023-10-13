package view;

import controller.Controller;
import controller.GameMode2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CountdownPanel extends JPanel implements ActionListener  {

    private View view;
    private Controller controller;
    private GameMode2 gm2;
    private Timer timer;
    private JLabel countdownLabel;
    private int count = 5;

    /**
     * Constructs a CountdownPanel object with the specified View and GameMode2.
     *
     * @param view the View object
     * @param gm2 the GameMode2 object
     */
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
        timer = new Timer(600, new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 1) {
                    countdownLabel.setText(String.valueOf(count));
                } else {
                    timer.stop();
                    view.setTimesUp(true);
                    // view.getController().startGame();

                    countdownLabel.setText("0");

                }
            }
        });
        timer.start();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public int getCount() {
        return count;
    }
    public void setCount() {
        this.count = count + 2;
    }

    public JLabel getCountdownLabel() {
        return countdownLabel;
    }
    public void setCountdownLabel(JLabel countdownLabel) {
        this.countdownLabel = countdownLabel;
    }
}
