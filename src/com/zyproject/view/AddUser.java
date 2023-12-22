//添加用户界面
package com.zyproject.view;

import com.zyproject.Dao.UserDao;
import com.zyproject.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddUser extends JInternalFrame implements ActionListener {
    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10;
    JTextField jTextField1,jTextField2,jTextField3,jTextField4,jTextField6,jTextField7,jTextField8,jTextField9;
    JTextArea jTextArea;
    JPanel p1,p3,p4;
    JScrollPane p2;
    JRadioButton manRadioButton=new JRadioButton();
    JRadioButton womanRadioButton=new JRadioButton();
    ButtonGroup buttonGroup=new ButtonGroup();
    JTextField txtCondition;
    public AddUser(){
        setTitle("用户添加");
        //setSize(500,480);
        setBounds(280,80, 500, 480);
        setClosable(true);
        setIconifiable(true);
        GridLayout gridLayout=new GridLayout(5,2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        getContentPane().setBackground(Color.pink);
        getContentPane().setLayout(null);


        p2=new JScrollPane();
        p2.setBackground(Color.pink);
        p1=new JPanel();
        p1.setBounds(0,40,476,175);
        p1.setLayout(gridLayout);
        p1.setBackground(Color.pink);

        p2.setBounds(40,220,434,175);
        p4=new JPanel();
        p4.setBackground(Color.pink);
        p4.setBounds(60,0,350,35);
        JLabel label1=new JLabel("按用户名查询");
        txtCondition=new JTextField(10);
        JButton btnSelect=new JButton("查询");
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowUser(txtCondition.getText());
            }
        });
        p4.add(label1);
        p4.add(txtCondition);
        p4.add(btnSelect);
        getContentPane().add(p4);


        //ID
        jLabel1=new JLabel("ID:");
        jTextField1=new JTextField(8);
        //用户名
        jLabel2=new JLabel("用户名:");
        jTextField2=new JTextField(8);
        //密码
        jLabel3=new JLabel("密码:");
        jTextField3=new JTextField(8);
        //年龄
        jLabel4=new JLabel("年龄:");
        jTextField4=new JTextField(8);
        //性别
        jLabel5=new JLabel("性别:");


        manRadioButton.setSelected(true);
        manRadioButton.setText("男");

        buttonGroup.add(manRadioButton);

        womanRadioButton.setText("女");
        buttonGroup.add(womanRadioButton);
        //QQ
        jLabel6=new JLabel("QQ:");
        jTextField6=new JTextField(8);
        //mail
        jLabel7=new JLabel("mail:");
        jTextField7=new JTextField(8);
        //电话号码
        jLabel8=new JLabel("电话:");
        jTextField8=new JTextField(8);
        //头衔
        jLabel9=new JLabel("头衔:");
        jTextField9=new JTextField(8);
        //简介
        jLabel10=new JLabel("简介:");
        jLabel10.setFont(new Font("宋体",Font.PLAIN,16));
        jLabel10.setBounds(0,215,45,30);
        jTextArea=new JTextArea();
        jTextArea.setBounds(40,215,434,175);

        JButton btnAdd=new JButton("添加");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    User user = getUser();
                    UserDao userDao = new UserDao();
                   // if ("管理员".equals(jTextField9.getText()))
                    boolean flag = userDao.addUser(user);
                        if (flag)
                        {
                            JOptionPane.showMessageDialog(null, "成员信息添加成功！");
                            CleanTextField();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "添加成员信息失败！ID已经存在！");
                }catch (Exception em){
                    JOptionPane.showMessageDialog(null, "请填写内容后再添加内容！");
                }
            }
        });

        JButton btnUpdate=new JButton("修改");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (new UserDao().UpdateUser(UpdateUser()))
                    {
                        JOptionPane.showMessageDialog(null, "成员信息修改成功");
                        CleanTextField();
                    }
                    else
                        JOptionPane.showMessageDialog(null,"成员信息修改失败");
                }catch (Exception em){
                       JOptionPane.showMessageDialog(null, "请先查询后再修改内容！");
                }
                }
        });
        JButton btnDelete=new JButton("删除");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(txtCondition.getText())){
                    JOptionPane.showMessageDialog(null, "请先查询后再删除");
                }else{
                    int result=JOptionPane.showConfirmDialog(null, "确定删除内容？","删除确认框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (result==JOptionPane.YES_OPTION){
                        User user=new User();
                        user.setUserName(txtCondition.getText());
                        UserDao userDao=new UserDao();
                        if (userDao.DeleteUser(user)){
                            JOptionPane.showMessageDialog(null, "成员信息删除成功·");
                            CleanTextField();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "成员信息删除失败");
                        }
                    }
                    else if(result==JOptionPane.CANCEL_OPTION);}

            }
        });
        JButton btnClear=new JButton("清除");
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CleanTextField();
            }
        });

        p3=new JPanel();
        p3.setBackground(Color.pink);
        p3.setBounds(55,398,400,30);
        p3.add(btnAdd);
        p3.add(btnUpdate);
        p3.add(btnDelete);
        p3.add(btnClear);
        getContentPane().add(p3);

        p1.add(jLabel1);p1.add(jTextField1);
        p1.add(jLabel2);p1.add(jTextField2);
        p1.add(jLabel3);p1.add(jTextField3);
        p1.add(jLabel4);p1.add(jTextField4);
        p1.add(jLabel6);p1.add(jTextField6);
        p1.add(jLabel7);p1.add(jTextField7);
        p1.add(jLabel8);p1.add(jTextField8);
        p1.add(jLabel9);p1.add(jTextField9);
        p1.add(jLabel5);p1.add(manRadioButton);p1.add(womanRadioButton);
        getContentPane().add(jLabel10);
        p2.setViewportView(jTextArea);

        getContentPane().add(p1);

        getContentPane().add(p2);
    }
    private User getUser(){
        User us=new User();
        us.setId(Integer.parseInt(jTextField1.getText()));
        us.setUserName(jTextField2.getText());
        us.setPassword(jTextField3.getText());
        us.setAge(Integer.parseInt(jTextField4.getText()));
        if(manRadioButton.isSelected())
            us.setSex("男");
        else us.setSex("女");
        us.setQQ(jTextField6.getText());
        us.setMail(jTextField7.getText());
        us.setPhoneNumber(jTextField8.getText());
        us.setTitle(jTextField9.getText());
        us.setIntroduction(jTextArea.getText());
        return us;
    }
    private void ShowUser(String username){
        User us;
        UserDao userDao=new UserDao();
        us=userDao.ShowUser(username);
        if (username.equals(us.getUserName())) {
            jTextField1.setText(String.valueOf(us.getId()));
            jTextField2.setText(us.getUserName());
            jTextField3.setText(us.getPassword());
            jTextField4.setText(String.valueOf(us.getAge()));
            if ("男".equals(us.getSex())) {
                manRadioButton.setSelected(true);
            } else {
                womanRadioButton.setSelected(true);
            }
            jTextField6.setText(us.getQQ());
            jTextField7.setText(us.getMail());
            jTextField8.setText(us.getPhoneNumber());
            jTextField9.setText(us.getTitle());
            jTextArea.setText(us.getIntroduction());
        }
        else
        {
            JOptionPane.showMessageDialog(null,"未查询到此用户，请检查用户名是否正确！");
        }
    }

    private User UpdateUser(){
        User user=new User();
        user.setId(Integer.parseInt(jTextField1.getText()));
        user.setUserName(jTextField2.getText());
        user.setPassword(jTextField3.getText());
        user.setAge(Integer.parseInt(jTextField4.getText()));
        if (manRadioButton.isSelected())
            user.setSex("男");
        else
            user.setSex("女");
        user.setQQ(jTextField6.getText());
        user.setMail(jTextField7.getText());
        user.setPhoneNumber(jTextField8.getText());
        user.setTitle(jTextField9.getText());
        user.setIntroduction(jTextArea.getText());
        return user;
    }

    private void CleanTextField(){
        txtCondition.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        buttonGroup.isSelected(null);
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextArea.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
//Tip:1.未完成用户名的重复比对 2.添加时输入已经存在的ID报错