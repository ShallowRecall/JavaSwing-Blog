package com.zyproject.Dao;

import com.zyproject.model.Article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleDao extends BaseDao{
    PreparedStatement ps=null;
    ResultSet rs=null;

    public List<Article> getArticleList(){
        List<Article> articleList=new ArrayList<>();
        String sql="select *from b_article";
        try{
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Article ar=new Article();
                ar.setUid(rs.getInt("uid"));
                ar.setPost_author(rs.getString("author"));
                ar.setPost_title(rs.getString("title"));
                ar.setPost_type(rs.getString("type"));
                ar.setPost_name(rs.getString("name"));
                ar.setPost_content(rs.getString("content"));
                ar.setPost_status(rs.getString("status"));
                ar.setPost_date(rs.getDate("date"));
                articleList.add(ar);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return  articleList;
    }

    public Boolean UpdateArticle(Article ar){
          String sql="update b_article set author=?,title=?,type=?,name=?,status=?,date=? where uid=?";
        try {

          Date date=new Date();
          //Timestamp timestamp=new Timestamp(date.getTime());



            // boolean rs = ps.execute(sql);
            ps= conn.prepareStatement(sql);
            ps.setInt(7,ar.getUid());
            ps.setString(1, ar.getPost_author());
            ps.setString(2, ar.getPost_title());
            ps.setString(3, ar.getPost_type());
            ps.setString(4, ar.getPost_name());
            //ps.setString(5, ar.getPost_content());
            ps.setString(5, ar.getPost_status());
            ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
            ps.executeUpdate();
            //boolean rs = ps.execute(sql);
            int i=ps.executeUpdate();
            if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addArticle(Article ar){
        String sql="insert into b_Article(name,author,title,type,content,status,date) values(?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,ar.getPost_name());
            ps.setString(2,ar.getPost_author());
            ps.setString(3, ar.getPost_title());
            ps.setString(4, ar.getPost_type());
            ps.setString(5, ar.getPost_content());
            ps.setString(6,ar.getPost_status());
            ps.setDate(7,  new java.sql.Date(new java.util.Date().getTime()));
            int i = ps.executeUpdate();
            if (i > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Article SelectNameAuthor(String name,String author){
        Article article=new Article();
        String sql="select * from b_article where name=? and author=? ";
        try {
            ps= conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, author);
            rs=ps.executeQuery();
            while (rs.next()) {
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public Boolean UpdateOtherIdAuthor(Article ar){
        String sql="update b_article set name=?,title=?,type=?,status=?,content=?,date=? where author=?";
        try {
            ps=conn.prepareStatement(sql);

            ps.setString(1, ar.getPost_name());
            ps.setString(2,ar.getPost_title());
            ps.setString(3,ar.getPost_type());
            ps.setString(4,ar.getPost_status());
            ps.setString(5,ar.getPost_content());
            ps.setDate(6,new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(7, ar.getPost_author());
            ps.executeUpdate();
            int i=ps.executeUpdate();
            if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    public Boolean DeleteArticle(Article ar){
        String sql="delete from b_article where name=?";
        try {
            ps= conn.prepareStatement(sql);
            ps.setString(1, ar.getPost_name());
            int i = ps.executeUpdate();
            if (i>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



//按作者查询
    public List<Article> SelectByAuthor(String author){
        List<Article> articleList=new ArrayList<>();
        String sql="select * from b_article where author LIKE ? ";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, "%"+author+"%");
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setUid(rs.getInt("uid"));
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }


    //按UID查询
    public List<Article> SelectByUid(String uid){
        List<Article> articleList=new ArrayList<>();
        String sql="select * from b_article where uid=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, uid);
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setUid(rs.getInt("uid"));
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    //按标签
    public List<Article> SelectByTitle(String title){
        List<Article> articleList=new ArrayList<>();
        String sql="select * from b_article where title=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, title);
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setUid(rs.getInt("uid"));
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    //按类型
    public List<Article> SelectByType(String type){
        List<Article> articleList=new ArrayList<>();
        String sql="select * from b_article where type=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, type);
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setUid(rs.getInt("uid"));
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    //按状态
    public List<Article> SelectByStatus(String status){
        List<Article> articleList=new ArrayList<>();
        String sql="select * from b_article where status=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, status);
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setUid(rs.getInt("uid"));
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    //按时间
    public List<Article> SelectByDate(String date){
        List<Article> articleList=new ArrayList<>();
        String sql="select * from b_article where date=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, date);
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setUid(rs.getInt("uid"));
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    //按内容
    public List<Article> SelectByContent(String content){
        List<Article> articleList=new ArrayList<>();
        String sql="select * from b_article where content LIKE ?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, "%"+content+"%");
            rs=ps.executeQuery();
            while (rs.next()){
                Article article=new Article();
                article.setUid(rs.getInt("uid"));
                article.setPost_name(rs.getString("name"));
                article.setPost_author(rs.getString("author"));
                article.setPost_title(rs.getString("title"));
                article.setPost_type(rs.getString("type"));
                article.setPost_status(rs.getString("status"));
                article.setPost_date(rs.getDate("date"));
                article.setPost_content(rs.getString("content"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }


}
