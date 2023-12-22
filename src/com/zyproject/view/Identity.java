package com.zyproject.view;

import com.zyproject.Dao.AdminDao;
import com.zyproject.Dao.UserDao;
import com.zyproject.Dao.WriterDao;
import com.zyproject.model.Admin;
import com.zyproject.model.User;
import com.zyproject.model.Writer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class Identity extends JInternalFrame {

    JPanel p1,p2,p3;
    JTable jTableUser,jTableAdmin,jTableWrite;
    JScrollPane scrollPane1,scrollPane2,scrollPane3;
    JLabel jlSelect,jlTitle;
    JTextField jtSelect,jtTitle;
    JButton jbSelect,jbTitle;
    DefaultTableModel df;
    public Identity(){
        setTitle("身份认证");
        setClosable(true);
        setIconifiable(true);
        setSize(998,640);
        JTabbedPane tabbedPane=new JTabbedPane();






        JPanel p4=new JPanel();
        p4.setLayout(new FlowLayout());
        jlSelect=new JLabel("按头衔查询:");
        jtSelect=new JTextField(16);
        jbSelect=new JButton("查询");
        jbSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ("".equals(jtSelect.getText()))
                {
                    JOptionPane.showMessageDialog(null, "空查询，请重新打开！");
                    dispose();
                }else
                UpdateJTableUser(jtSelect.getText());
            }
        });





        p4.add(jlSelect);
        p4.add(jtSelect);
        p4.add(jbSelect);

        JPanel p5=new JPanel();
        p5.setLayout(new FlowLayout());
        jlTitle=new JLabel("修改头衔");
        jtTitle=new JTextField(16);
        jbTitle=new JButton("添加身份");







        p5.add(jlTitle);
        p5.add(jtTitle);
        p5.add(jbTitle);


        p1=new JPanel();
        p1.setLayout(new BorderLayout());
        jTableUser=new JTable();
        scrollPane1=new JScrollPane();
        ShowUser showUser=new ShowUser();
        scrollPane1.setBounds(0,0,998,320);
        scrollPane1.setViewportView(jTableUser);
        showUser.setJTableUser(new UserDao().getUserList(),jTableUser);
        jTableUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedTableRow(e);
            }
        });



        jbTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("管理员".equals(jtTitle.getText())){
                    User user=UpdateTitle();
                    Admin admin=new Admin();
                    admin.setUserName(user.getUserName());
                    admin.setPassword(user.getPassword());
                    admin.setAge(user.getAge());
                    admin.setQQ(user.getQQ());
                    admin.setMail(user.getMail());
                    admin.setPhoneNumber(user.getPhoneNumber());

                    if (new AdminDao().addUser(user)){
                        JOptionPane.showMessageDialog(null, "修改成功");
                    }else {
                        JOptionPane.showMessageDialog(null, "修改失败");
                    }
                }
                if ("作者".equals(jtTitle.getText())){
                    User user=UpdateTitle();
                    Writer writer=new Writer();
                    writer.setUserName(user.getUserName());
                    writer.setPassword(user.getPassword());
                    writer.setAge(user.getAge());
                    writer.setQQ(user.getQQ());
                    writer.setMail(user.getMail());
                    writer.setPhoneNumber(user.getPhoneNumber());
                    if (new WriterDao().addUser(user)){
                        JOptionPane.showMessageDialog(null, "修改成功");
                    }else {
                        JOptionPane.showMessageDialog(null, "修改失败");
                    }
                }

            }
        });

        p1.add(p4,BorderLayout.NORTH);
        p1.add(scrollPane1,BorderLayout.CENTER);
        p1.add(p5,BorderLayout.SOUTH);
        tabbedPane.addTab("用户表", p1);



        p2=new JPanel();
        jTableAdmin=new JTable();
        scrollPane2=new JScrollPane();
        scrollPane2.setBounds(0,0,998,320);
        scrollPane2.setViewportView(jTableAdmin);
        setJTableAdmin();
        p2.add(scrollPane2);

        tabbedPane.addTab("管理员表", p2);




        p3=new JPanel();
        jTableWrite=new JTable();
        scrollPane3=new JScrollPane();
        scrollPane2.setBounds(0,0,998,320);
        scrollPane3.setViewportView(jTableWrite);
        setJTableWrite();
        p3.add(scrollPane3);


        tabbedPane.addTab("作者表", p3);





        add(tabbedPane);
    }


    public void selectedTableRow(MouseEvent e){
        df=(DefaultTableModel)jTableUser.getModel();
        jtTitle.setText(df.getValueAt(jTableUser.getSelectedRow(),8).toString());
    }

    public void  UpdateJTableUser(String title){
        DefaultTableModel dft=(DefaultTableModel) jTableUser.getModel();
        dft.setRowCount(0);

        UserDao userDao=new UserDao();
        List<User> userList=userDao.SelectByTitle(title);
        for (User us:userList){
            Vector v=new Vector<>();
            v.add(us.getId());
            v.add(us.getUserName());
            v.add(us.getPassword());
            v.add(us.getAge());
            v.add(us.getSex());
            v.add(us.getQQ());
            v.add(us.getMail());
            v.add(us.getPhoneNumber());
            v.add(us.getTitle());
            v.add(us.getIntroduction());
            dft.addRow(v);
        }
        userDao.closeDao();
    }



    public User UpdateTitle(){
        df=(DefaultTableModel)jTableUser.getModel();
        User user=new User();
        user.setUserName(df.getValueAt(jTableUser.getSelectedRow(), 1).toString());
        user.setPassword(df.getValueAt(jTableUser.getSelectedRow(), 2).toString());
        user.setAge((Integer) df.getValueAt(jTableUser.getSelectedRow(),3));
        user.setQQ(df.getValueAt(jTableUser.getSelectedRow(), 5).toString());
        user.setMail(df.getValueAt(jTableUser.getSelectedRow(), 6).toString());
        user.setPhoneNumber(df.getValueAt(jTableUser.getSelectedRow(),7).toString());
        user.setTitle(jtTitle.getText());
        new UserDao().UpdateTitle(user);
        return user;
    }


    public void setJTableAdmin(){
        DefaultTableModel dft=(DefaultTableModel) jTableAdmin.getModel();
        dft.addColumn("ID");
        dft.addColumn("用户名");
        dft.addColumn("密码");
        dft.addColumn("年龄");
        dft.addColumn("QQ");
        dft.addColumn("邮箱");
        dft.addColumn("电话");

        AdminDao adminDao=new AdminDao();
        List<Admin> adminList=adminDao.getUserList();
        for (Admin admin:adminList){
            Vector v=new Vector();
            v.add(admin.getId());
            v.add(admin.getUserName());
            v.add(admin.getPassword());
            v.add(admin.getAge());
            v.add(admin.getQQ());
            v.add(admin.getMail());
            v.add(admin.getPhoneNumber());
            dft.addRow(v);
        }
        adminDao.closeDao();
    }


    public void setJTableWrite(){
        DefaultTableModel dft=(DefaultTableModel) jTableWrite.getModel();
        dft.addColumn("ID");
        dft.addColumn("用户名");
        dft.addColumn("密码");
        dft.addColumn("年龄");
        dft.addColumn("QQ");
        dft.addColumn("邮箱");
        dft.addColumn("电话");
        dft.addColumn("擅长");

        WriterDao writerDao=new WriterDao();
        List<Writer> writerList=writerDao.getUserList();
        for (Writer writer:writerList){
            Vector v=new Vector();
            v.add(writer.getId());
            v.add(writer.getUserName());
            v.add(writer.getPassword());
            v.add(writer.getAge());
            v.add(writer.getQQ());
            v.add(writer.getMail());
            v.add(writer.getPhoneNumber());
            v.add(writer.getSkill());
            v.add(v);
            dft.addRow(v);
        }
        writerDao.closeDao();
    }





}
