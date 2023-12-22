package com.zyproject.Dao;

import com.zyproject.model.Admin;
import com.zyproject.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends BaseDao{

    PreparedStatement ps=null;
    ResultSet rs=null;

    public Admin login(String userName){
        Admin adminRst=new Admin();
        String sql="select * from b_admin where userName=?";
        try {
            ps=conn.prepareStatement(sql);   //访问数据库的对象，并初始化
            ps.setString(1,userName);
            rs=ps.executeQuery();            //实现查询
            if(rs.next()){
                adminRst.setId(rs.getInt("id"));
                adminRst.setUserName(rs.getString("userName"));
                adminRst.setPassword(rs.getString("password"));
                adminRst.setAge(rs.getInt("age"));
                adminRst.setQQ(rs.getString("QQ"));
                adminRst.setMail(rs.getString("mail"));
                adminRst.setPhoneNumber(rs.getString("phoneNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminRst;
    }


    public boolean addUser(User us){
        String sql="insert into b_admin(userName,age,QQ,mail,phoneNumber) values(?,?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, us.getUserName());
            ps.setInt(2,us.getAge());
            ps.setString(3,us.getQQ());
            ps.setString(4,us.getMail());
            ps.setString(5,us.getPhoneNumber());
            int i= ps.executeUpdate();
            if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Admin> getUserList() {
        List<Admin> adminList = new ArrayList<>();
        String sql = "select * from b_admin";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();                 //实现查询
            while (rs.next()) {
                Admin admin=new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUserName(rs.getString("userName"));
                admin.setPassword(rs.getString("password"));
                admin.setAge(rs.getInt("age"));
                admin.setQQ(rs.getString("QQ"));
                admin.setMail(rs.getString("mail"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
                adminList.add(admin);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return adminList;
    }

}
