package controller;

import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class GameMode2 implements Runnable, KeyListener {

    private Controller controller;
    private View view;
    private char[] keyboardChars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '´',
            'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'Å', '¨',
            'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ö', 'Ä',
            'Z', 'X', 'C', 'V', 'B', 'N', 'M', ',', '.', '-'};
    private char[] randomChars1 = new char[5];
    private char[] randomChars2 = new char[5];
    private Random random = new Random();
    private int index = 0;
    private int count = 0;
    private boolean running = true;
    private List<JButton> player1 = new ArrayList<>();
    private List<JButton> player2 = new ArrayList<>();
    private boolean turn = true; // Player 1 = true && player 2 = false
    private int[] status = new int[4];


    public GameMode2(View view) {
        this.view = view;
    }

    // Detta ÄR algoritmen som fixar att det blir random tecken


    public void runRandomizer() {
        //Detta kommer behövas ändras!!!!!!!!
        randomizeChar(randomChars1, randomChars2, 1);
        randomizeChar(randomChars2, randomChars1, 2);

        index = (index + 1) % 5;
    }

    public void randomizeChar(char[] randomChar, char[] comparisonArray, int arrayNumber) {
        char newChar = keyboardChars[random.nextInt(keyboardChars.length)];
        boolean charExists = false;
        for (int i = 0; i < randomChar.length; i++) {
            if (newChar == randomChar[i] || newChar == comparisonArray[i]) {
                charExists = true;
                break;
            }
        }

        if (!charExists) {
            randomChar[index] = newChar;
            System.out.println("From Array " + arrayNumber + ": " + Arrays.toString(randomChar));
            count++;
        } else randomizeChar(randomChar, comparisonArray, arrayNumber);
    }

//Här slutar den //TheodorB
    @Override
    public void run() {
        this.startGameMode2();
    }

    private void startGameMode2() {
        runRandomizer();

    }
    public void lightUpGreen() {
        if (turn) {
            for (char c : randomChars1) {
                if (c != '\u0000') {
                    for (JButton button : controller.getButtonArr()) {
                        if (Objects.equals(button.getText(), String.valueOf(c))) {
                            button.setBackground(Color.BLUE);
                            break;
                        }
                    }
                }
            }
        } else {
            for (char c : randomChars2) {
                if (c != '\u0000') {
                    for (JButton button : controller.getButtonArr()) {
                        if (Objects.equals(button.getText(), String.valueOf(c))) {
                            button.setBackground(Color.RED);
                        }
                    }
                }
            }
        }
    }
    private void nextTurn() {
        //Indikera vems tur det är och vilken tangent från randomCharArray1 || randomCharArray2.
        lightUpGreen();
        if (turn){
            runRandomizer();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());

        if (randomChars1[index] == e.getKeyChar() && turn){
            //Skickas meddelande till GUI om någon förändring

            turn = true;
            nextTurn();
        }

        else if (randomChars2[index] == e.getKeyChar() && !turn){
            //Skickas meddelande till GUI om någon förändring

            turn = false;
            nextTurn();
        }
        else{
            if (turn){
                JOptionPane.showMessageDialog(null,"player1 lost");
            }else
                JOptionPane.showMessageDialog(null,"player2 lost");
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (int i = 0; i < 5; i++){
            if (e.getKeyChar() == randomChars1[i]){
                JOptionPane.showMessageDialog(null, "Player1 lost");
            }
            if (e.getKeyChar() == randomChars2[i]){
                JOptionPane.showMessageDialog(null, "Player2 lost");
            }
        }
    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<JButton> getPlayer1() {
        return player1;
    }

    public void setPlayer1(List<JButton> player1) {
        this.player1 = player1;
    }

    public List<JButton> getPlayer2() {
        return player2;
    }

    public void setPlayer2(List<JButton> player2) {
        this.player2 = player2;
    }
}


