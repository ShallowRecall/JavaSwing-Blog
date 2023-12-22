package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class A {

    private JFrame frmA;
    private JLabel aLabel;

    // 自定义set方法，供于子窗口调用赋值
    public void setJLabel(JLabel aLabel){
        this.aLabel = aLabel;
    }

    public JLabel getJLabel(){
        return aLabel;
    }

    public void setFrame(JFrame frmA){
        this.frmA = frmA;
    }

    public Frame getFrame(){
        return frmA;
    }

    // 启动时将窗口A的引用赋予窗口B
    public void openSubFrame(){
        B window = new B(this);
        window.frmB.setVisible(true);
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    A window = new A();
                    window.frmA.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public A() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmA = new JFrame();
        frmA.setTitle("A");
        frmA.setBounds(100, 100, 450, 300);
        frmA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmA.getContentPane().setLayout(null);

        aLabel = new JLabel("New label");
        aLabel.setBounds(140, 24, 154, 30);
        frmA.getContentPane().add(aLabel);

        JButton btnNewButton = new JButton("Click");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSubFrame();
            }
        });
        btnNewButton.setBounds(165, 101, 93, 23);
        frmA.getContentPane().add(btnNewButton);
    }
}


