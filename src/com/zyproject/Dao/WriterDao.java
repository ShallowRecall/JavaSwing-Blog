package com.zyproject.Dao;

import com.zyproject.model.User;
import com.zyproject.model.Writer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WriterDao extends BaseDao{

    PreparedStatement ps=null;
    ResultSet rs=null;

    public Writer login(String name){
        Writer writerRst=new Writer();
        String sql="select * from b_writer where userName=?";
        try {
            ps=conn.prepareStatement(sql);   //访问数据库的对象，并初始化
            ps.setString(1,name);
            rs=ps.executeQuery();            //实现查询
            if(rs.next()){
                writerRst.setId(rs.getInt("id"));
                writerRst.setUserName(rs.getString("userName"));
                writerRst.setPassword(rs.getString("password"));
                writerRst.setAge(rs.getInt("age"));
                writerRst.setQQ(rs.getString("QQ"));
                writerRst.setMail(rs.getString("mail"));
                writerRst.setPhoneNumber(rs.getString("phoneNumber"));
                writerRst.setSkill(rs.getString("skill"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writerRst;
    }
    public boolean addUser(User us){
        String sql="insert into b_writer (userName,age,QQ,mail,phoneNumber,password) values(?,?,?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, us.getUserName());
            ps.setInt(2,us.getAge());
            ps.setString(3,us.getQQ());
            ps.setString(4,us.getMail());
            ps.setString(5,us.getPhoneNumber());
            ps.setString(6,us.getPassword());
            int i= ps.executeUpdate();
            if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Writer> getUserList() {
        List<Writer> writerList = new ArrayList<>();
        String sql = "select * from b_writer";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();                 //实现查询
            while (rs.next()) {
                Writer writer=new Writer();
                writer.setId(rs.getInt("id"));
                writer.setUserName(rs.getString("userName"));
                writer.setPassword(rs.getString("password"));
                writer.setAge(rs.getInt("age"));
                writer.setQQ(rs.getString("QQ"));
                writer.setMail(rs.getString("mail"));
                writer.setPhoneNumber(rs.getString("phoneNumber"));
                writer.setSkill(rs.getString("skill"));
                writerList.add(writer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writerList;
    }
}
