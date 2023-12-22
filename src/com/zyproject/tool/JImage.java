//自定义背景组件
package com.zyproject.tool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class JImage extends Component {

    private Image image;

    public JImage (String filename){
        try (FileInputStream inputStream =new FileInputStream(filename)){
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
    }
}
