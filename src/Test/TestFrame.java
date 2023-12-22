package Test;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    JTabbedPane jTabbedPane=new JTabbedPane();
    JPanel j1,j2;
    public TestFrame(){
        setSize(600,600);

        j1=new JPanel();
        j1.setBackground(Color.yellow);
        j2=new JPanel();
        j2.setBackground(Color.pink);
        jTabbedPane.addTab("面板1",j1);
        jTabbedPane.addTab("面板2",j2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setContentPane(jTabbedPane);
    }

    public static void main(String[] args) {
       TestFrame  testFrame=new TestFrame();
       testFrame.setVisible(true);
    }
}
