package com.zyproject.Dao;

import com.zyproject.util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {
    public Connection conn=new DbUtil().getCon();
    public void closeDao(){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
