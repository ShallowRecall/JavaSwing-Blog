package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class B {

    JFrame frmB;
    private static A aFrame;

    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    B window = new B(aFrame);
                    window.frmB.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public B(A aFrame) {
        this.aFrame = aFrame;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmB = new JFrame();
        frmB.setTitle("B");
        frmB.setBounds(100, 100, 450, 300);
        frmB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmB.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
                    aFrame.getJLabel().setText(textField.getText().toString());
                }
            }
        });
        textField.setBounds(187, 92, 66, 21);
        frmB.getContentPane().add(textField);
        textField.setColumns(10);
    }

}


