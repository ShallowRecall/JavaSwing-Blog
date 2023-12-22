package com.zyproject.tool;

import javax.swing.*;
import java.awt.*;

public class LunBoPanel extends JPanel {
    int index = 0;
    ImageIcon[] image = new ImageIcon[]{
            new ImageIcon("src/picture/1.png"),
            new ImageIcon("src/picture/2.png"),
            new ImageIcon("src/picture/3.png"),
            new ImageIcon("src/picture/4.png"),
            new ImageIcon("src/picture/5.png"),
            new ImageIcon("src/picture/6.png"),
            new ImageIcon("src/picture/7.png"),
            new ImageIcon("src/picture/8.png"),
    };

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image[index % image.length].getImage(), 0, 0, this);
        index++;
    }
}
