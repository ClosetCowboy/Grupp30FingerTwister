package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
/**
 * The panel that displays the game interface and handles keyboard input.
 */
public class GamePanel extends JPanel implements KeyListener {
    private final View view;
    private final Controller controller;

    /**
     * Constructs a GamePanel object.
     * @param view The View object.
     * @param controller The Controller object.
     */
    public GamePanel(View view, Controller controller){
        this.view = view;
        this.controller = controller;
        this.setSize(new Dimension(960, 500));
        this.setFocusable(true);
        view.add(this);
        createKeyboard();
        this.setVisible(true);
    }

    /**
     * Creates the keyboard layout with buttons for the game.
     */
    public void createKeyboard(){

        this.setLayout(new GridBagLayout());

        JPanel pRow = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1d;

        for (int row = 0; row < view.getController().getArr().length; ++row) {
            pRow = new JPanel(new GridBagLayout());

            c.gridy = row;

            for (int col = 0; col < view.getController().getArr()[row].length; ++col){
                JButton button = new JButton(view.getController().getArr()[row][col]);
                button.addKeyListener(this);
                button.setBorderPainted(true);
                button.setPreferredSize(new Dimension(120 , 90)); // set the preferred size to 50x50
                button.setFont(new Font("Italic",Font.BOLD,34));
                pRow.add(button);
                view.getController().getButtonArr().add(button);
            }
            this.add(pRow, c);
        }
    }

    /**
     * Highlights a button to indicate it has been pressed.
     * @param lightUpButton The button to be highlighted.
     */
    public void makeLitButton(JButton lightUpButton) {
        lightUpButton.setBackground(Color.YELLOW);
        lightUpButton.setOpaque(true);
        lightUpButton.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (controller.isGamemode()) {
            for (int i = 0; i < controller.getGm2().getRandomChars1().length; i++) {
                if ((String.valueOf(e.getKeyChar()).equalsIgnoreCase(String.valueOf(controller.getGm2().getRandomChars1()[i]))) && controller.getGm2().isTurn()) {
                    //Skickas meddelande till GUI om någon förändring
                    controller.getGm2().setTurn(false);
                    controller.getGm2().runRandomizer2();
                }
            }
            for (int i = 0; i < controller.getGm2().getRandomChars2().length; i++) {
                if ((String.valueOf(e.getKeyChar()).equalsIgnoreCase(String.valueOf(controller.getGm2().getRandomChars2()[i]))) && !controller.getGm2().isTurn()) {
                    //Skickas meddelande till GUI om någon förändring
                    controller.getGm2().setTurn(true);
                    controller.getGm2().runRandomizer();
                }
            }
        }
    }

    /**
     * Invoked when a key has been pressed.
     * @param e The KeyEvent object representing the key press event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("You pressed: "+KeyEvent.getKeyText(e.getKeyCode()));

        if (!controller.isGamemode()) {

            if (view.isTimesUp()) {
                JOptionPane.showMessageDialog(null, "Times up!");
                String name = JOptionPane.showInputDialog("Write your name");
                try {
                    view.getController().newScore(name, view.getController().getKeyCount());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                new StartMenu();
                this.setVisible(false);

            }


            for (JButton button : view.getController().getButtonArr()) {
                if (button.getText().equalsIgnoreCase(String.valueOf(e.getKeyChar()))) {
                    if (button.equals(view.getController().getLitButton1())) {
                        button.setBackground(Color.GREEN);
                        button.setOpaque(true);
                        button.setVisible(true);
                        view.getController().setKeyCount(view.getController().getKeyCount() + 1);
                        view.getCountDownPanel().setCount();

                    }
                    else if (button.equals(view.getController().getLitButton2())) {
                        button.setBackground(Color.BLUE);
                        button.setOpaque(true);
                        button.setVisible(true);
                        view.getController().setKeyCount(view.getController().getKeyCount() + 1);
                        view.getCountDownPanel().setCount();
                    }
                    else {
                        button.setBackground(Color.RED);
                        button.setOpaque(true);
                        button.setVisible(true);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Invoked when a key has been released.
     * @param e The KeyEvent object representing the key release event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released "+e.getKeyChar());

        if (!controller.isGamemode()) {

            for (JButton button : view.getController().getButtonArr()) {
                if (button.getText().equalsIgnoreCase(String.valueOf(e.getKeyChar()))) {
                    if (button == view.getController().getLitButton1()) {
                        button.setBackground(null);
                        button.setOpaque(true);
                        button.setVisible(true);
                        view.getController().newButton(button);

                    } else if (button == view.getController().getLitButton2()) {
                        button.setBackground(null);
                        button.setOpaque(true);
                        button.setVisible(true);
                        view.getController().newButton(button);

                    } else {
                        JOptionPane.showMessageDialog(null, "You missed the button, You lose!");
                        String name = JOptionPane.showInputDialog("Write your name");
                        try {
                            view.getController().newScore(name, view.getController().getKeyCount());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        new StartMenu();
                        this.setVisible(false);
                    }

                    break;
                }
            }
        }

        else{
            for (char c: controller.getGm2().getRandomChars1()) {
                if (String.valueOf(e.getKeyChar()).equalsIgnoreCase(String.valueOf(c))){
                    JOptionPane.showMessageDialog(null, "Player 1 lost");
                }
            }
            for (char c: controller.getGm2().getRandomChars2()) {
                if (String.valueOf(e.getKeyChar()).equalsIgnoreCase(String.valueOf(c))){
                    JOptionPane.showMessageDialog(null, "Player 2 lost");
                }
            }
        }
    }
}
