package com.zyproject.Dao;

import com.zyproject.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao {
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User login(String name) {
        User userRst = new User();
        String sql = "select * from b_user where userName=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                userRst.setId(rs.getInt("id"));
                userRst.setUserName(rs.getString("userName"));
                userRst.setPassword(rs.getString("password"));
                userRst.setAge(rs.getInt("age"));
                userRst.setSex(rs.getString("Sex"));
                userRst.setQQ(rs.getString("QQ"));
                userRst.setMail(rs.getString("mail"));
                userRst.setPhoneNumber(rs.getString("phoneNumber"));
                userRst.setTitle(rs.getString("title"));
                userRst.setIntroduction(rs.getString("introduction"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRst;
    }

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from b_user";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();                 //实现查询
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getInt("id"));
                us.setUserName(rs.getString("userName"));
                us.setPassword(rs.getString("password"));
                us.setAge(rs.getInt("age"));
                us.setSex(rs.getString("sex"));
                us.setQQ(rs.getString("QQ"));
                us.setMail(rs.getString("mail"));
                us.setPhoneNumber(rs.getString("phoneNumber"));
                us.setTitle(rs.getString("title"));
                us.setIntroduction(rs.getString("introduction"));
                userList.add(us);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public boolean addUser(User us) {
        String sql = "insert into b_user(id,userName,password,age,Sex,QQ,mail,phoneNumber,title,introduction) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, us.getId());
            ps.setString(2, us.getUserName());
            ps.setString(3, us.getPassword());
            ps.setInt(4, us.getAge());
            ps.setString(5, us.getSex());
            ps.setString(6, us.getQQ());
            ps.setString(7, us.getMail());
            ps.setString(8, us.getPhoneNumber());
            ps.setString(9, us.getTitle());
            ps.setString(10, us.getIntroduction());
            int i = ps.executeUpdate();
            if (i > 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User ShowUser(String name) {
        User userRst = new User();
        String sql = "select * from b_user where userName=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                userRst.setId(rs.getInt("id"));
                userRst.setUserName(rs.getString("userName"));
                userRst.setPassword(rs.getString("password"));
                userRst.setAge(rs.getInt("age"));
                userRst.setSex(rs.getString("Sex"));
                userRst.setQQ(rs.getString("QQ"));
                userRst.setMail(rs.getString("mail"));
                userRst.setPhoneNumber(rs.getString("phoneNumber"));
                userRst.setTitle(rs.getString("title"));
                userRst.setIntroduction(rs.getString("introduction"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRst;
    }

    public Boolean UpdateUser(User us){
        String sql = "update  b_user set userName=?,password=?,age=?,sex=?,QQ=?,mail=?,phoneNumber=?,title=?,introduction=? where id=?";
        try{
            ps= conn.prepareStatement(sql);
            ps.setInt(10, us.getId());
            ps.setString(1, us.getUserName());
            ps.setString(2,us.getPassword());
            ps.setInt(3,us.getAge());
            ps.setString(4,us.getSex());
            ps.setString(5, us.getQQ());
            ps.setString(6,us.getMail());
            ps.setString(7,us.getPhoneNumber());
            ps.setString(8, us.getTitle());
            ps.setString(9, us.getIntroduction());
            ps.executeUpdate();
            int i = ps.executeUpdate();
            if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean DeleteUser(User us){
        String sql="delete from b_user where userName=?";
        try {
            ps= conn.prepareStatement(sql);
            ps.setString(1, us.getUserName());
            int i = ps.executeUpdate();
            if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




    public List<User> SelectByTitle(String title) {
        List<User> userList=new ArrayList<>();
        String sql = "select * from b_user where title=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                User us=new User();
                us.setId(rs.getInt("id"));
                us.setUserName(rs.getString("userName"));
                us.setPassword(rs.getString("password"));
                us.setAge(rs.getInt("age"));
                us.setSex(rs.getString("Sex"));
                us.setQQ(rs.getString("QQ"));
                us.setMail(rs.getString("mail"));
                us.setPhoneNumber(rs.getString("phoneNumber"));
                us.setTitle(rs.getString("title"));
                us.setIntroduction(rs.getString("introduction"));
                userList.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }



    public Boolean UpdateTitle(User us){
        String sql = "update  b_user set title=? where userName=?";
        try{
           ps= conn.prepareStatement(sql);
           ps.setString(1,us.getTitle());
           ps.setString(2,us.getUserName());
           ps.executeUpdate();
           int i = ps.executeUpdate();
           if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
