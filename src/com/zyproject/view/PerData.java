package com.zyproject.view;

import com.zyproject.Dao.UserDao;
import com.zyproject.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerData extends JInternalFrame implements ActionListener {
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    JTextArea jTextArea=new JTextArea();
    public PerData(){
        setTitle("个人资料");
       // setSize(500,480);
        setBounds(280,80, 500, 480);
        setClosable(true);
        setIconifiable(true);
        setLayout(null);
        setBackground(Color.green);
        JPanel p1,p2,p3;


        p1 =new JPanel();
        p1.setBackground(Color.green);
        p1.setBounds(60,0,350,35);
        JLabel label1=new JLabel("按用户名查询");
        JTextField txtCondition=new JTextField(10);
        JButton btnSelect=new JButton("查询");
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowUser(txtCondition.getText());
            }
        });
        p1.add(label1);
        p1.add(txtCondition);
        p1.add(btnSelect);
        getContentPane().add(p1);

        p2 =new JPanel();
        p2.setBackground(Color.green);
        p2.setLayout(new GridLayout(5,2,10,0));
        p2.setBounds(60,35,350,210);


        JLabel jLabel=new JLabel("简介:");
        jLabel.setFont(new Font("宋体",Font.PLAIN,16));
        jLabel.setBounds(0,0,45,30);
        jTextArea.setBounds(45,2,275,150);
        jTextArea.setEnabled(false);
        p3=new JPanel();
        p3.setBackground(Color.green);
        p3.setLayout(null);
        p3.setBounds(60,245,350,210);
        p3.add(jLabel);
        p3.add(jTextArea);
        getContentPane().add(p3);



        //ID
        jl1=new JLabel("ID："+"null");
        //用户名
        jl2=new JLabel("用户名："+"null");
        //密码
        jl3=new JLabel("密码："+"null");
        //年龄
        jl4=new JLabel("年龄："+"null");
        //性别
        jl5=new JLabel("性别："+"null");
        //QQ
        jl6=new JLabel("QQ："+"null");
        //mail
        jl7=new JLabel("mail："+"null");
        //电话号码
        jl8=new JLabel("电话："+"null");
        //头衔
        jl9=new JLabel("头衔："+"null");


        p2.add(jl1);p2.add(jl2);
        p2.add(jl3);p2.add(jl4);
        p2.add(jl5);p2.add(jl6);
        p2.add(jl7);p2.add(jl8);
        p2.add(jl9);
        getContentPane().add(p2);


    }

    private void ShowUser (String name){
        UserDao userDao=new UserDao();
        User us=userDao.ShowUser(name);
        if (name.equals(us.getUserName())) {
            jl1.setText("ID:" + us.getId());
            jl2.setText("用户名:" + us.getUserName());
            jl3.setText("密码:" + us.getPassword());
            jl4.setText("年龄:" + us.getAge());
            jl5.setText("性别:" + us.getSex());
            jl6.setText("QQ:" + us.getQQ());
            jl7.setText("邮箱:" + us.getMail());
            jl8.setText("电话:" + us.getPhoneNumber());
            jl9.setText("头衔:" + us.getTitle());
            jTextArea.setText(us.getIntroduction());
        }
        else
            JOptionPane.showMessageDialog(null,"未查询到此用户，请检查用户名是否正确！");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
