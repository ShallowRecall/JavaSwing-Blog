//所有用户界面
package com.zyproject.view;

import com.zyproject.Dao.UserDao;
import com.zyproject.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class ShowUser extends JInternalFrame {
    JTable jTableUser;
    public ShowUser(){
        setTitle("所有用户");
        setSize(998,400);
        setClosable(true);
        setIconifiable(true);

       jTableUser=new JTable();

       List<User> userList=new UserDao().getUserList();
       JScrollPane scrollPane=new JScrollPane();
       scrollPane.setViewportView(jTableUser);
       setJTableUser(userList,jTableUser);
       getContentPane().add(scrollPane);

    }

    public void  setJTableUser(List<User> userList,JTable jTableUser ){
        DefaultTableModel dft=(DefaultTableModel) jTableUser.getModel();
        dft.addColumn("ID");
        dft.addColumn("用户名");
        dft.addColumn("密码");
        dft.addColumn("年龄");
        dft.addColumn("性别");
        dft.addColumn("QQ");
        dft.addColumn("邮箱");
        dft.addColumn("电话");
        dft.addColumn("头衔");
        dft.addColumn("简介");

        UserDao userDao=new UserDao();
        userList=userDao.getUserList();
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
}
