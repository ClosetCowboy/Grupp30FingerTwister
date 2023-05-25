package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameModePanel extends JPanel implements ActionListener {
    private View view;
    private JComboBox<String> gameModeChoser;
    private String [] choices = new String[]{"FingerTwister", "TypingRace"};
    private JButton startButton;
    private int count = 5;
    private Timer secTimer;
    public GameModePanel(View view) {
        this.view = view;
        addGameModeChoser();
        addStartButton();

    }

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

    private void addStartButton() {
        startButton = new JButton("Start");
        startButton.setFont(new Font("Font.ITALIC",Font.ITALIC,54));
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(300,100));
        this.add(startButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            startButton.setText("5");
            secTimer = new Timer(1000,this);
            secTimer.start();
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

                //Detta borde fungera men bör kollas upp om keylisteners endast fungerar på panels och frames!
                view.getController().getGm2().run();
            }
        }

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
    }

    public JComboBox<String> getGameModeChoser() {
        return gameModeChoser;
    }

    public void setGameModeChoser(JComboBox<String> gameModeChoser) {
        this.gameModeChoser = gameModeChoser;
    }
}
