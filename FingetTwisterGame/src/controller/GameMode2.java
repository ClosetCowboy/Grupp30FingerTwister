package controller;

import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.annotation.ElementType;
import java.util.*;
import java.util.List;

public class GameMode2 implements Runnable {

    private Controller controller;
    private View view;
    private char[] keyboardChars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '´',
                                    'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'Å', '¨',
                                    'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ö', 'Ä',
                                    'Z', 'X', 'C', 'V', 'B', 'N', 'M', ',', '.', '-'};
    private char[] randomChars1 = new char[4];
    private char[] randomChars2 = new char[4];
    private Random random = new Random();
    private int rounds;
    private int index = 0;
    private int count = 0;
    private boolean running = true;
    private List<JButton> player1ButtonArray = new ArrayList<>();
    private List<JButton> player2ButtonArray = new ArrayList<>();
    private boolean turn = true; // Player 1 = true && player 2 = false
    private int[] status = new int[4];
    private Thread fingerTwisterThread;
    public GameMode2(View view, Controller controller) {
        this.controller = controller;
        this.view = view;
        fingerTwisterThread = new Thread(this);
        fingerTwisterThread.start();
    }

    // Detta är algoritmen som fixar att det blir random tecken
    public void runRandomizer() {

        randomizeChar(randomChars1, randomChars2, 1);
        randomizeChar(randomChars2, randomChars1, 2);
        //Detta kommer behövas ändras!!!!!!!!
        index = (index + 1) % 4;

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
            System.out.println("Array " + arrayNumber + ": " + Arrays.toString(randomChar));
            count++;
        } else randomizeChar(randomChar, comparisonArray, arrayNumber);

        for (int i = 0 ; i < controller.getButtonArr().size() ; i++){

            if ((controller.getButtonArr().get(i).getText().charAt(0)) == newChar){
                if (arrayNumber == 1){
                    synchronized (player1ButtonArray){
                        player1ButtonArray.add(index, view.getController().getButtonArr().get(i));
                    }
                } else {
                    synchronized (player2ButtonArray){
                        player2ButtonArray.add(index, view.getController().getButtonArr().get(i));
                    }
                }
            }
        }
    }
//Här slutar den //TheodorB
    @Override
    public void run() {
        runRandomizer();
        while(running){
            lightUpKeys();
        }
    }

    public synchronized void lightUpKeys() {
        if (turn){
            view.getStartingPanel().getPlayer1Text().setForeground(Color.BLUE);
            view.getStartingPanel().getPlayer2Text().setForeground(null);
        }
        else {
            view.getStartingPanel().getPlayer2Text().setForeground(Color.GREEN);
            view.getStartingPanel().getPlayer1Text().setForeground(null);
        }

        synchronized (player1ButtonArray) {
            try {
                for (JButton button : player1ButtonArray) {
                    char c = button.getText().charAt(0);
                    if (containsChar(randomChars1, c)) {
                        button.setBackground(Color.BLUE);
                    } else button.setBackground(null);
                }
                view.getGamePanel().repaint();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        synchronized (player2ButtonArray) {
            try {
                for (JButton button : player2ButtonArray) {
                    char c = button.getText().charAt(0);
                    if (containsChar(randomChars2, c)) {
                        button.setBackground(Color.GREEN);
                    } else button.setBackground(null);
                }
                view.getGamePanel().repaint();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private boolean containsChar(char[] array, char c) {
        for (char ch : array) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public char[] getKeyboardChars() {
        return keyboardChars;
    }

    public void setKeyboardChars(char[] keyboardChars) {
        this.keyboardChars = keyboardChars;
    }

    public char[] getRandomChars1() {
        return randomChars1;
    }

    public void setRandomChars1(char[] randomChars1) {
        this.randomChars1 = randomChars1;
    }

    public char[] getRandomChars2() {
        return randomChars2;
    }

    public void setRandomChars2(char[] randomChars2) {
        this.randomChars2 = randomChars2;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int[] getStatus() {
        return status;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    public void timeIsUpLoserEvent(){
        if (turn){
            JOptionPane.showMessageDialog(null,"Player 1 lost, Player 2 Wins");
        }
        if (!turn){
            JOptionPane.showMessageDialog(null,"Player 2 lost, Player 1 Wins");
        }
    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<JButton> getPlayer1ButtonArray() {
        return player1ButtonArray;
    }

    public void setPlayer1ButtonArray(List<JButton> player1ButtonArray) {
        this.player1ButtonArray = player1ButtonArray;
    }

    public List<JButton> getPlayer2ButtonArray() {
        return player2ButtonArray;
    }

    public void setPlayer2ButtonArray(List<JButton> player2ButtonArray) {
        this.player2ButtonArray = player2ButtonArray;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}


