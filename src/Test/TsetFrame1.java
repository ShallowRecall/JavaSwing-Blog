package Test;

import javax.swing.*;
import java.awt.*;

public class TsetFrame1 extends JFrame {
    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10;
    JTextField jTextField1,jTextField2,jTextField3,jTextField4,jTextField5,jTextField6,jTextField7,jTextField8,jTextField9;
    JTextArea jTextArea;
    JPanel p1,p2;
    public TsetFrame1(){
        setTitle("添加用户");
        setSize(990,640);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        p1.setBounds(0,0,600,400);
        p1.setOpaque(false);
        p1.setLayout(new FlowLayout());
        add(p1);
        p2.setLayout(new GridLayout(5,2));
        p2.add(p1);
        p2.setOpaque(false);
        //ID
        jLabel1=new JLabel("ID:");
        jLabel1.setSize(60,40);
        jTextField1=new JTextField(16);
        jLabel1.setSize(60,40);
        //用户名
        jLabel2=new JLabel("用户名:");
        jLabel2.setSize(60,40);
        jTextField2=new JTextField(16);
        jTextField2.setSize(60,40);
        //密码
        jLabel3=new JLabel("密码:");
        jLabel3.setSize(60,40);
        jTextField3=new JTextField(16);
        jTextField3.setSize(60,40);
        //年龄
        jLabel4=new JLabel("年龄:");
        jLabel4.setSize(60,40);
        jTextField4=new JTextField(16);
        jTextField4.setSize(60,40);
        //性别
        jLabel5=new JLabel("性别:");
        jLabel5.setSize(60,40);
        jTextField5=new JTextField(16);
        jTextField5.setSize(60,40);
        //QQ
        jLabel6=new JLabel("QQ:");
        jLabel6.setSize(60,40);
        jTextField6=new JTextField(16);
        jTextField6.setSize(60,40);
        //mail
        jLabel7=new JLabel("mail:");
        jLabel7.setSize(60,40);
        jTextField7=new JTextField(16);
        jTextField7.setSize(60,40);
        //电话号码
        jLabel8=new JLabel("电话:");
        jLabel8.setSize(60,40);
        jTextField8=new JTextField(16);
        jTextField8.setSize(60,40);
        //头衔
        jLabel9=new JLabel("头衔:");
        jLabel9.setSize(60,40);
        jTextField9=new JTextField(16);
        jTextField9.setSize(60,40);
        //简介
        jLabel10=new JLabel("简介:");
        jLabel10.setSize(60,40);
        jTextArea=new JTextArea();
        //jTextField2.setSize(60,40);



        p1.add(jLabel1);p1.add(jTextField1);
        p1.add(jLabel2);p1.add(jTextField2);
        p1.add(jLabel3);p1.add(jTextField3);
        p1.add(jLabel4);p1.add(jTextField4);
        p1.add(jLabel5);p1.add(jTextField5);
        p1.add(jLabel6);p1.add(jTextField6);
        p1.add(jLabel7);p1.add(jTextField7);
        p1.add(jLabel8);p1.add(jTextField8);
        p1.add(jLabel9);p1.add(jTextField9);
        //p1.add(jLabel10);p1.add(jTextArea);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TsetFrame1();
    }
}
