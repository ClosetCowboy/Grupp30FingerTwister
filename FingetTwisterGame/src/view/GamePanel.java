package view;

import controller.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel implements KeyListener {
    private View view;
    //private String[] arr = new String [] {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a",
    // "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};


    public GamePanel(View view){
        this.view = view;
        this.setSize(new Dimension(960, 500));
        //setBounds(200,200,200,200);
        //this.setBorder(new LineBorder(Color.BLACK));
        this.setFocusable(true);
        view.add(this);
        createKeyboard();
        this.setVisible(true);

    }

    public void createKeyboard(){
        /*
        for (int i = 0; i < arr.length; ++i) {
            String s = arr[i];
            JButton button = new JButton(s);
            button.addKeyListener(this);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setVisible(true);
            this.add(button);
        }*/
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
                button.setPreferredSize(new Dimension(65, 50)); // set the preferred size to 50x50
                pRow.add(button);
                view.getController().getButtonArr().add(button);
            }

            this.add(pRow, c);
        }

    }
    /*
        private void addButtons() {
            String[][] arr = view.getController().getArr();

            for (int i = 0; i < arr.length; ++i) {
                String s = arr[i];
                JButton button = new JButton(s);
                button.addKeyListener(this);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setForeground(Color.BLACK);
                button.setBackground(Color.LIGHT_GRAY);
                button.setVisible(true);
                this.add(button);
                view.getController().getButtonArr().add(button);
            }
        }

     */
    public void makeLitButton(JButton lightUpButton) {
        lightUpButton.setBackground(Color.YELLOW);
        lightUpButton.setOpaque(true);
        lightUpButton.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("You typed: "+e.getExtendedKeyCode());
        // System.out.println(e.getSource().toString());

    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("You pressed: "+KeyEvent.getKeyText(e.getKeyCode()));



        if (view.isTimesUp()) {
            String name = JOptionPane.showInputDialog(null, "Times Up! Your score = " + view.getController().getKeyCount() + " Enter your name: ");
            try {
                view.getController().setNewScore(name, view.getController().getKeyCount());
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }

        }


        for (JButton button : view.getController().getButtonArr()) {
            if (button.getText().equalsIgnoreCase(String.valueOf(e.getKeyChar()))) {
                if (button.equals(view.getController().getLitButton1())) {
                    button.setBackground(Color.GREEN);
                    button.setOpaque(true);
                    button.setVisible(true);
                    view.getController().setKeyCount(view.getController().getKeyCount() + 1);
                    view.getCountDownPanel().setCount(5);
                    //  view.getCountDownPanel().startGameTimer();



                } else if (button.equals(view.getController().getLitButton2())) {
                    button.setBackground(Color.GREEN);
                    button.setOpaque(true);
                    button.setVisible(true);
                    view.getController().setKeyCount(view.getController().getKeyCount() + 1);
                    view.getCountDownPanel().setCount(5);
                    // view.getCountDownPanel().startGameTimer();
                } else {
                    button.setBackground(Color.RED);
                    button.setOpaque(true);
                    button.setVisible(true);
                }
                break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released "+e.getKeyChar());


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

                }else {
                    JOptionPane.showMessageDialog(null, "You missed the button, You lose!");
                    String name = JOptionPane.showInputDialog("Write your name");
                    //view.getScoreBoardPanel().addNewScore(name, view.getController().getKeyCount());
                    try {
                        view.getController().newScore(name, view.getController().getKeyCount());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    // TODO: Skicka tillbaka till en startskärm.
                    //   view.getController().startCountDown();
                }

                break;
            }
        }


    }
}
