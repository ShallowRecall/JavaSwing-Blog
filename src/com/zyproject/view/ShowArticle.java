//所有文章界面
package com.zyproject.view;

import com.zyproject.Dao.ArticleDao;
import com.zyproject.Dao.UserDao;
import com.zyproject.model.Article;
import com.zyproject.model.User;
import com.zyproject.tool.LunBoPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ShowArticle extends JInternalFrame{
    JPanel p1,p2,p3,p4,p5,p6;
    JTable jTableArticle;
    JTextField jtUid,jtAuthor,jtTitle,jtType,jtName,jtStatus,jtDate;
    JTextArea jTextArea, jtIntroduction;
    JLabel j3,jName,jAge,jSex,jIntroduction;
    JTextField jtSelect;
    DefaultTableModel df;
     public ShowArticle(){
         setTitle("文章管理");
         setSize(998,640);
         setClosable(true);
         setIconifiable(true);
         JTabbedPane tabbedPane=new JTabbedPane();
         JScrollPane jScrollPane=new JScrollPane();
         jTableArticle=new JTable();
         jTableArticle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
         p1 =new JPanel();
         p2 =new JPanel();

         JLabel j1=new JLabel("文章信息：");
         j1.setBounds(0,4, 100, 30);
         j1.setFont(new Font("微软雅黑", Font.PLAIN,16 ));



         JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
         separator.setForeground(Color.BLACK); // 设置分割线颜色
         separator.setBounds(0,30, 500, 20);

        JPanel p7=new JPanel();
        p7.setOpaque(false);
        FlowLayout flowLayout=new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        p7.setLayout(flowLayout);

         JLabel jSelect=new JLabel("文章查询:");
         p7.setBounds(100, 0,500,30);
         jSelect.setFont(new Font("微软雅黑", Font.PLAIN,16 ));

         jtSelect=new JTextField("输入要搜索的内容", 10);

         JButton jbSelect=new JButton("查询");

         //String[] select={"按作者","按UID","模糊查询","按标签","按类型","按状态","按时间"};
         //JComboBox jcSelect=new JComboBox(select);
         //jcSelect.setModel(new DefaultComboBoxModel(select)c);

             JComboBox<String> jcSelect = new JComboBox<>();
             jcSelect.addItem("按内容");
             jcSelect.addItem("按作者");
             jcSelect.addItem("按UID");
             jcSelect.addItem("按标签");
             jcSelect.addItem("按类型");
             jcSelect.addItem("按状态");
             jcSelect.addItem("按时间");
             jcSelect.addItem("所有文章");




             jcSelect.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String item = (String) jcSelect.getSelectedItem();
                     if ("所有文章".equals(item))
                         JOptionPane.showMessageDialog(null,"无需添加搜索条件，单击搜索按钮即可" );
                     if("按时间".equals(item))
                         JOptionPane.showMessageDialog(null,"时间查询格式例如：1970-01-01" );
                     if ("按内容".equals(item))
                         jtSelect.setText("支持模糊查找");
                     if ("按作者".equals(item))
                         jtSelect.setText("支持模糊查找");
                 }
             });



             jbSelect.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     ArticleDao articleDao=new ArticleDao();
                     String item = (String) jcSelect.getSelectedItem();
                     if ("按作者".equals(item))
                         setUpdateTable(articleDao.SelectByAuthor(jtSelect.getText()));
                     if ("按UID".equals(item))
                         setUpdateTable(articleDao.SelectByUid(jtSelect.getText()));
                     if ("按标签".equals(item))
                         setUpdateTable(articleDao.SelectByTitle(jtSelect.getText()));
                     if ("按类型".equals(item))
                         setUpdateTable(articleDao.SelectByType(jtSelect.getText()));
                     if ("按状态".equals(item))
                         setUpdateTable(articleDao.SelectByStatus(jtSelect.getText()));
                     if ("按时间".equals(item))
                         setUpdateTable(articleDao.SelectByDate(jtSelect.getText()));
                     if("按内容".equals(item))
                         setUpdateTable(articleDao.SelectByContent(jtSelect.getText()));
                     if("所有文章".equals(item))
                         setUpdateTable(articleDao.getArticleList());
                 }
             });










         p7.add(jSelect);
         p7.add(jtSelect);
         p7.add(jbSelect);
         p7.add(jcSelect);

         p3=new JPanel();
         p3.setLayout(null);
         p3.setBounds(0, 320, 499, 320);


         p3.setBackground(new Color(193,210,240));

         p3.add(j1);
         p3.add(p7);
         p3.add(separator);

         p4=new JPanel();

         JLabel jlUid=new JLabel("Uid:");
         jtUid=new JTextField();
         jlUid.setBounds(0, 45, 55, 30);
         jtUid.setBounds(55, 45, 100, 30);
         p3.add(jlUid);
         p3.add(jtUid);
         JLabel jlAuthor=new JLabel("作者:");
         jtAuthor=new JTextField();
         jlAuthor.setBounds(165, 45, 55, 30);
         jtAuthor.setBounds(220,45,100, 30);
         p3.add(jlAuthor);
         p3.add(jtAuthor);

         JLabel jlTitle=new JLabel("文章标签:");
         jtTitle=new JTextField();
         jlTitle.setBounds(0, 85, 60, 30);
         jtTitle.setBounds(55, 85, 100, 30);
         p3.add(jlTitle);
         p3.add(jtTitle);

         JLabel jlType=new JLabel("文章类型:");
         jtType=new JTextField();
         jlType.setBounds(165, 85, 60, 30);
         jtType.setBounds(220, 85, 100, 30);
         p3.add(jlType);
         p3.add(jtType);

         JLabel jlName=new JLabel("文章标题:");
         jtName=new JTextField();
         jlName.setBounds(0, 125, 60, 30);
         jtName.setBounds(55, 125, 100, 30);
         p3.add(jlName);
         p3.add(jtName);

         JLabel jlStatus=new JLabel("文章状态:");
         jtStatus=new JTextField();
         jlStatus.setBounds(165,125, 60, 30);
         jtStatus.setBounds(220, 125, 100, 30);
         p3.add(jlStatus);
         p3.add(jtStatus);

         JLabel jlDate=new JLabel("创建时间:");
         jtDate=new JTextField();
         jlDate.setBounds(0, 165, 60, 30);
         jtDate.setBounds(55, 165, 100, 30);
         p3.add(jlDate);
         p3.add(jtDate);

         JSeparator separator1 = new JSeparator(SwingConstants.VERTICAL);
         separator1.setForeground(Color.BLACK);
         separator1.setBounds(335, 34, 20,198 );
         p3.add(separator1);


         JButton jbtUpdate=new JButton("修改");
         jbtUpdate.setBounds(60,205,65,24);
         jbtUpdate.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Article article=UpdateArticle();
                 article.setUid(Integer.parseInt(jtUid.getText()));
                 try {
                     if (new ArticleDao().UpdateArticle(article))
                     {
                         JOptionPane.showMessageDialog(null, "文章信息修改成功");
                         CleanTextField();
                         setUpdateTable(new ArticleDao().getArticleList());
                     }
                     else
                         JOptionPane.showMessageDialog(null,"文章信息修改失败");
                 }catch (Exception em){
                     JOptionPane.showMessageDialog(null, "请先查询后再修改内容！");
                     em.printStackTrace();
                 }
             }

         });

         p3.add(jbtUpdate);


         JButton jbtDelete=new JButton("删除");
         jbtDelete.setBounds(200,205, 65,  24);
         p3.add(jbtDelete);
         jbtDelete.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if ("".equals(jtName.getText())){
                     JOptionPane.showMessageDialog(null, "请先查询后再删除");
                 }else{
                     int result=JOptionPane.showConfirmDialog(null, "确定删除内容？","删除确认框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                     if (result==JOptionPane.YES_OPTION){
                         Article article=new Article();
                         article.setPost_name(jtSelect.getText());
                         ArticleDao articleDao=new ArticleDao();
                         if (articleDao.DeleteArticle(article)){
                             JOptionPane.showMessageDialog(null, "文章删除成功·");
                             CleanTextField();
                         }
                         else{
                             JOptionPane.showMessageDialog(null, "文章删除失败");
                         }
                     }
                     else if(result==JOptionPane.CANCEL_OPTION);}
             }
         });




         DefaultListModel<String> articles = new DefaultListModel<>();
         articles.addElement("推荐文章1");
         articles.addElement("推荐文章2");
         articles.addElement("推荐文章3");
         articles.addElement("推荐文章4");
         articles.addElement("推荐文章5");
         articles.addElement("推荐文章6");
         articles.addElement("推荐文章7");
         articles.addElement("推荐文章8");
         articles.addElement("推荐文章9");
         articles.addElement("推荐文章10");
         articles.addElement("推荐文章11");
         articles.addElement("推荐文章12");
         articles.addElement("推荐文章13");
         articles.addElement("推荐文章14");
         articles.addElement("推荐文章15");

         //文件标题数组
         JList<String> list=new JList<>(articles);
         list.setBounds(340, 36, 160, 190);
         JScrollPane jScrollPane1=new JScrollPane();
         jScrollPane1.setBounds(340, 36, 160, 190);
         jScrollPane1.setViewportView(list);
         p3.add(jScrollPane1);
         p1.add(p3);

         LunBoPanel lunBoPanel=new LunBoPanel();
         lunBoPanel.setBounds(500, 320, 455, 229);

         Timer timer=new Timer(2500, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 lunBoPanel.repaint();
             }
         });
         timer.start();
         p1.add(lunBoPanel);

         p4=new JPanel();

         //p1.setBackground(Color.pink);
         p1.setLayout(null);
         jScrollPane.setBounds(0,0,998,320);
         jTableArticle.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e);
                 selectedTableRow(e);
             }
         });

         jScrollPane.setViewportView(jTableArticle);
         setJTableArticle();
         p1.add(jScrollPane);

         tabbedPane.addTab("所有文章",p1);

         p2=new JPanel();
         p2.setBackground(new Color(217, 210, 210));
         p2.setLayout(null);




         JLabel j2=new JLabel("关于我");
         p5=new JPanel();
         p5.setLayout(null);
         p5.setBounds(0,0,190,30);
         j2.setBounds(10,0,190, 30);
         j2.setFont(new Font("微软简体", Font.PLAIN,20 ));
         j2.setForeground(Color.white);
         p5.setBackground(Color.black);
         p5.add(j2);
         p2.add(p5);

         j3=new JLabel("《文章标题》节选");
         p6=new JPanel();
         p6.setLayout(null);
         p6.setBounds(230, 0, 728, 30);
         j3.setBounds(10, 0, 728, 30);
         j3.setFont(new Font("微软简体", Font.PLAIN,20 ));
         j3.setForeground(Color.white);
         p6.setBackground(Color.black);
         p6.add(j3);
         p2.add(p6);

         JLabel j4=new JLabel();
         j4.setBounds(0,30 , 190, 107);
         j4.setIcon(new ImageIcon("src/imags/5.jpeg"));
         p2.add(j4);


         jName=new JLabel("姓名："+"浅忆");
         jName.setBounds(5, 137, 190, 30);
         jAge=new JLabel("年龄："+"19");
         jAge.setBounds(5,172, 190, 30);
         jSex=new JLabel("性别："+"男");
         jSex.setBounds(5, 207, 190, 30);
         jIntroduction=new JLabel("简介：");
         jIntroduction.setBounds(5, 242, 45, 30);

         jtIntroduction=new JTextArea();
         jtIntroduction.setBounds(45, 242, 145, 185);
         jtIntroduction.setEnabled(false);

         p2.add(jName);
         p2.add(jAge);
         p2.add(jSex);
         p2.add(jIntroduction);
         p2.add(jtIntroduction);

//165，400
         DefaultListModel<String> title= new DefaultListModel<>();
         title.addElement("标签1");
         title.addElement("标签2");
         title.addElement("标签3");
         title.addElement("标签4");
         title.addElement("标签5");
         title.addElement("标签6");
         title.addElement("标签7");
         title.addElement("标签8");
         title.addElement("标签9");
         title.addElement("标签10");
         title.addElement("标签11");
         title.addElement("标签12");
         title.addElement("标签13");
         title.addElement("标签14");
         title.addElement("标签15");

         //文章标签数组
         JList<String> listTitle=new JList<>(title);
         listTitle.setBounds(830, 30, 120, 200);
         JScrollPane jScrollPane2=new JScrollPane();
         jScrollPane2.setBounds(830, 30, 120, 200);
         jScrollPane2.setViewportView(listTitle);
         p2.add(jScrollPane2);

         DefaultListModel<String> comment= new DefaultListModel<>();
         comment.addElement("评论1");

         //文章评论数组
         JList<String> listComment=new JList<>(comment);
         listTitle.setBounds(830, 30, 120, 200);
         JScrollPane jScrollPane3=new JScrollPane();
         jScrollPane3.setBounds(830, 240, 120, 200);
         jScrollPane3.setViewportView(listComment);
         p2.add(jScrollPane3);


         JScrollPane jScrollPane4=new JScrollPane();
         jTextArea=new JTextArea("",50,20);
         jScrollPane4.add(jTextArea);
         jScrollPane4.setBounds(235, 50, 570, 510);
         jScrollPane4.setViewportView(jTextArea);
         p2.add(jScrollPane4);









          tabbedPane.addTab("预览文章", p2);
         add(tabbedPane);


     }
    public void selectedTableRow(MouseEvent e){
        df=(DefaultTableModel)jTableArticle.getModel();
        try{
            jtUid.setText(df.getValueAt(jTableArticle.getSelectedRow(),0).toString());
            jtAuthor.setText(df.getValueAt(jTableArticle.getSelectedRow(),1).toString());
            jtName.setText(df.getValueAt(jTableArticle.getSelectedRow(),4).toString());
            jtDate.setText(df.getValueAt(jTableArticle.getSelectedRow(),7).toString());
        }catch (Exception e1){
            e1.printStackTrace();
        }


        try {
            jtTitle.setText(df.getValueAt(jTableArticle.getSelectedRow(),2).toString());
            jtType.setText(df.getValueAt(jTableArticle.getSelectedRow(),3).toString());
            jTextArea.setText(df.getValueAt(jTableArticle.getSelectedRow(),5).toString());
            jtStatus.setText(df.getValueAt(jTableArticle.getSelectedRow(),6).toString());
            if (jTextArea.getText()==null||jtType.getText()==null||jtTitle.getText()==null||jtStatus.getText()==null){
                //jTextArea.setText("文章已经删除");jtTitle.setText(null);jtType.setText(null);jtTitle.setText(null);jtStatus.setText(null);
            }
        }
        catch (NullPointerException e1){
            JOptionPane.showMessageDialog(null, "文章内容已经删除，换一篇文章吧！");
            jTextArea.setText("文章已经删除");jtTitle.setText(null);jtType.setText(null);jtTitle.setText(null);jtStatus.setText(null);
        }



        j3.setText(df.getValueAt(jTableArticle.getSelectedRow(),4).toString());
        jName.setText("姓名："+df.getValueAt(jTableArticle.getSelectedRow(),1).toString());
        User user;
        UserDao userDao=new UserDao();
        user=userDao.login(df.getValueAt(jTableArticle.getSelectedRow(),1).toString());
        jAge.setText("年龄："+user.getAge());
        jSex.setText("性别："+user.getSex());
        jtIntroduction.setText(user.getIntroduction());
    }



     private void setJTableArticle(){
         DefaultTableModel dft=(DefaultTableModel) jTableArticle.getModel();
         dft.addColumn("uid");
         dft.addColumn("作者");
         dft.addColumn("文章标签");
         dft.addColumn("文章类型");
         dft.addColumn("文章标题");
         dft.addColumn("文章内容");
         dft.addColumn("文章状态");
         dft.addColumn("发布时间");

         ArticleDao articleDao=new ArticleDao();
         List<Article> articleList=articleDao.getArticleList();
         for (Article ar:articleList){
             Vector v=new Vector<>();
             v.add(ar.getUid());
             v.add(ar.getPost_author());
             v.add(ar.getPost_title());
             v.add(ar.getPost_type());
             v.add(ar.getPost_name());
             v.add(ar.getPost_content());
             v.add(ar.getPost_status());
             v.add(ar.getPost_date());
             dft.addRow(v);
         }
         articleDao.closeDao();
     }


     private Article UpdateArticle(){
         Article article=new Article();
         Date nowDate=new Date();
         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String format =dateFormat.format(nowDate);
         article.setUid(Integer.parseInt(jtUid.getText()));
         article.setPost_author(jtAuthor.getText());
         article.setPost_title(jtTitle.getText());
         article.setPost_type(jtType.getText());
         article.setPost_name(jtName.getText());
         article.setPost_status(jtStatus.getText());
         article.setPost_date(nowDate);
         System.out.println(nowDate);
         System.out.println(format);
         return article;
     }

     private void CleanTextField(){
        jtUid.setText(null);
        jtAuthor.setText(null);
        jtTitle.setText(null);
        jtType.setText(null);
        jtName.setText(null);
        jtStatus.setText(null);
        jtDate.setText(null);
     }

    //更新表
    private void setUpdateTable(List<Article> articleList){
        DefaultTableModel dft=(DefaultTableModel) jTableArticle.getModel();
        dft.setRowCount(0);
        ArticleDao articleDao=new ArticleDao();
        for (Article ar:articleList){
            Vector v=new Vector<>();
            v.add(ar.getUid());
            v.add(ar.getPost_author());
            v.add(ar.getPost_title());
            v.add(ar.getPost_type());
            v.add(ar.getPost_name());
            v.add(ar.getPost_content());
            v.add(ar.getPost_status());
            v.add(ar.getPost_date());
            dft.addRow(v);
        }
        articleDao.closeDao();
    }
}
