package view;

import controller.Controller;
import controller.GameMode2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameModePanel extends JPanel implements ActionListener {
    private View view;
    private JComboBox<String> gameModeChoser;
    //private String [] choices = new String[]{"FingerTwister", "TypingRace"};
    private JButton startButton;
    private int count = 5;
    private Timer secTimer;
    private Controller controller;
    private JLabel scoreboardText;
    private JLabel spaceInBetween;
    private JLabel player1Text;
    private JLabel player2Text;
    public GameModePanel(View view, Controller controller) {
        this.controller = controller;
        this.view = view;
        addAllLabels();
    }

    private void addAllLabels() {
        addSpaceInbetween();
        addPlayer1Text();
        addSpaceInbetween();
        addStartButton();
        addSpaceInbetween();
        addPlayer2Text();
        addSpaceInbetween();
    }


    private void addSpaceInbetween() {
        spaceInBetween = new JLabel();
        spaceInBetween.setPreferredSize(new Dimension(150,100));
        this.add(spaceInBetween);
    }

    private void addPlayer2Text() {
        player2Text = new JLabel("Player 2");
        player2Text.setPreferredSize(new Dimension(250,100));
        player2Text.setFont(new Font("Italc", Font.BOLD,60));
        this.add(player2Text);
    }
    private void addPlayer1Text() {
        player1Text = new JLabel("Player 1");
        player1Text.setPreferredSize(new Dimension(250,100));
        player1Text.setFont(new Font("Italc", Font.BOLD,60));
        this.add(player1Text);
    }
/*
    private void addGameModeChoser() {
        gameModeChoser = new JComboBox<>(choices);
        gameModeChoser.addActionListener(this);
        gameModeChoser.setPreferredSize(new Dimension(300,100));
        gameModeChoser.setFont(new Font("Font.ITALIC",Font.ITALIC,30));

        JLabel gameModeText = new JLabel("Gamemode:");
        gameModeText.setFont(new Font("Font.ITALIC",Font.ITALIC,30));

        this.add(gameModeText);
        this.add(gameModeChoser);
    }

 */

    private void addStartButton() {
        startButton = new JButton("Start");
        startButton.setFont(new Font("Font.ITALIC",Font.ITALIC,54));
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(300,100));
        this.add(startButton);
    }
    private void addScoreboardText(){
        scoreboardText = new JLabel("Scoreboard");
        scoreboardText.setFont(new Font("Font.ITALIC",Font.ITALIC,54));
        scoreboardText.setPreferredSize(new Dimension(300,100));
        this.add(scoreboardText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton){
            if (Objects.equals(startButton.getText(), "STOP")){
                view.setVisible(false);
                new StartMenu();
            }else {
                startButton.setText("5");
                secTimer = new Timer(1000, this);
                secTimer.start();
            }
        }


        if (e.getSource() instanceof Timer){
            count--;
            startButton.setText(String.valueOf(count));
            if (count == 0 || count < 0){
                secTimer.stop();
                startButton.setText("STOP");
                // TODO: Istället för att starta ett nytt spel ska man skickas tillbaka till GameModePanel
                // transferFocus används för att ge fokus till GamePanel i fönstret för att den ska lyssna efter keystrokes.
                view.getGamePanel().transferFocus();
                //view.getGamePanel().createKeyboard();
                //view.setGamePanel(new GamePanel(view));
                //view.getController().startGame();
                if (view.getController().isGamemode()){
                    view.getController().setGm2(new GameMode2(view, this.controller));
                }else view.getController().startGame();

            }
        }

/*
        if (e.getSource() == getGameModeChoser()){
            for ( int i = 0; i < choices.length;i++ ){
                if (e.getSource() == "FingerTwister"){
                    JOptionPane.showMessageDialog(null,"You are now playing FingerTwister!");
                }
                if (e.getSource() == "TypingRace"){
                    JOptionPane.showMessageDialog(null,"You are now playing TypingRace!");
                }
            }
        }

 */
    }

    public JComboBox<String> getGameModeChoser() {
        return gameModeChoser;
    }

    public void setGameModeChoser(JComboBox<String> gameModeChoser) {
        this.gameModeChoser = gameModeChoser;
    }

    public JLabel getPlayer1Text() {
        return player1Text;
    }

    public JLabel getPlayer2Text() {
        return player2Text;
    }
}
