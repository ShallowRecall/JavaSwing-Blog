package com.zyproject.Dao;
//每日金句
import com.zyproject.model.Sentence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class SentenceDao extends BaseDao{
    PreparedStatement ps=null;
    ResultSet rs=null;

    public Sentence getSentence(){
        Sentence sentence=new Sentence();
        String sql="select * from b_sentence where id=?";
        Random random=new Random();
        try{
            int index=random.nextInt( getLastRow());
            ps=conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs=ps.executeQuery();
            while (rs.next()){
                sentence.setId(rs.getInt("id"));
                sentence.setContent(rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sentence;
    }

//获取数据库中的最后一行索引
    public int getLastRow(){
        int last = 0;
        String sql="select * from b_sentence";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            rs.last();
            last=rs.getRow();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return last;
    }
}
