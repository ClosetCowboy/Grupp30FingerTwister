package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TwisterPanel extends JPanel implements KeyListener {
    private View view;
    //private String[] arr = new String [] {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a",
    // "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};


    public TwisterPanel(View view){
        this.view = view;
        this.setSize(new Dimension(480, 250));
        setBounds(200,200,200,200);

        view.add(this);
        createKeyboard();
        this.setVisible(true);
    }

    public void createKeyboard(){
        this.setLayout(new GridBagLayout());

        JPanel pRow;
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1d;

        for (int row = 0; row < view.getController().getArr().length; ++row) {
            pRow = new JPanel(new GridBagLayout());

            c.gridy = row;

            for (int col = 0; col < view.getController().getArr()[row].length; ++col){
                JButton button = new JButton(view.getController().getArr()[row][col]);
                pRow.add(button);
                view.getController().getButtonArr().add(button);
                button.addKeyListener(this);
            }

            this.add(pRow, c);
        }
    }
    public void makeLitButton(JButton lightUpButton) {
        lightUpButton.setBackground(Color.YELLOW);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("You typed: "+e.getKeyChar());

    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("You pressed: " + e.getKeyChar());
/*
        for (JButton b : view.getController().getGm2().getPlayer1()) {
            if (e.getSource() instanceof JButton) {
                if (String.valueOf(e.getKeyChar()).equals(b.getText())) {
                    view.getController().getGm2().nextButtonP1();
                }
            }
            else {
                view.getController().getGm2().setRunning(false);
                view.getController().getGm2().setPlayer1Lost(true);
            }
        }
        */

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You pressed "+e.getKeyChar());

        for (JButton b: view.getController().getGm2().getPlayer1ButtonArray()) {
            if (e.getSource() instanceof JButton) {
                if (String.valueOf(e.getKeyChar()).equals(b.getText())) {
                    view.getController().getGm2().setRunning(false);
                }
            }
        }
    }
}
