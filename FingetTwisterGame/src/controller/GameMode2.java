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
    private char[] randomChars1 = new char[4];
    private char[] randomChars2 = new char[4];
    private Random random = new Random();
    private int index = 0;
    private int count = 0;
    private boolean running = true;
    private List<JButton> player1 = new ArrayList<>();
    private List<JButton> player2 = new ArrayList<>();
    private boolean turn = true; // Player 1 = true && player 2 = false
    private int[] status = new int[4];


    public GameMode2(View view, Controller controller) {
        this.controller = controller;
        this.view = view;
        Thread thread = new Thread(this);
        thread.start();
    }

    // Detta ÄR algoritmen som fixar att det blir random tecken


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
            System.out.println("From Array " + arrayNumber + ": " + Arrays.toString(randomChar));
            count++;
        } else randomizeChar(randomChar, comparisonArray, arrayNumber);

        for (int i = 0 ; i < controller.getButtonArr().size() ; i++){
            if ((controller.getButtonArr().get(i).getText().charAt(0)) == newChar){
                if (turn){
                    player1.add(view.getController().getButtonArr().get(i));
                    turn = false;
                }else{
                    player2.add(view.getController().getButtonArr().get(i));
                    turn = true;
                }
            }
        }
    }

//Här slutar den //TheodorB
    @Override
    public void run() {
        runRandomizer();
        lightUpGreen();
    }
    public void lightUpGreen() {
        if (turn) {
            for (char c : randomChars1) {
                if (c != '\u0000') {
                    for (int i = 0 ; i < randomChars1.length; i++ ) {
                        if (Objects.equals(player1.get(i).getText(), String.valueOf(randomChars1[i]))) {
                            player1.get(i).setBackground(Color.BLUE);
                            view.getGamePanel().repaint();
                            break;
                        }
                    }
                }
            }
        } else {
            for (char c : randomChars2) {
                if (c != '\u0000') {
                    for (int i = 0 ; i < randomChars2.length ; i ++) {
                        if (Objects.equals(player2.get(i).getText(), String.valueOf(randomChars2[i]))) {
                            player2.get(i).setBackground(Color.GREEN);
                            view.getGamePanel().repaint();
                        }
                    }
                }
            }
        }
    }
    public void nextTurn() {
        //Indikera vems tur det är och vilken tangent från randomCharArray1 || randomCharArray2.
        lightUpGreen();
        runRandomizer();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());

        if (randomChars1[index] == e.getKeyChar() && turn){
            //Skickas meddelande till GUI om någon förändring
            turn = false;
            nextTurn();
        }

        else if (randomChars2[index] == e.getKeyChar() && !turn){
            //Skickas meddelande till GUI om någon förändring

            turn = true;
            nextTurn();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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


