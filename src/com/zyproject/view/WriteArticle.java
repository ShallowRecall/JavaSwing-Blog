package com.zyproject.view;

import com.zyproject.Dao.ArticleDao;
import com.zyproject.Dao.SentenceDao;
import com.zyproject.model.Admin;
import com.zyproject.model.Article;
import com.zyproject.model.Sentence;
import com.zyproject.tool.GetRandomQuote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteArticle extends JInternalFrame {
   //JTextArea jTextPane=new JTextArea();
   JTextPane jTextPane;
   Admin admin;
 JTextField jtName,jtAuthor,jtTitle,jtType,jtStatus,jtDate;
 Notepad editor = new Notepad();

 public WriteArticle() {
  setTitle("文章编辑");
  setSize(998, 640);
  setClosable(true);
  setIconifiable(true);
  setLayout(null);



  JPanel p1 = new JPanel();
  // GridLayout gridLayout=new GridLayout(6,2);
  p1.setLayout(new FlowLayout());


  JLabel jlName = new JLabel("文章标题:");
  jtName = new JTextField(16);
  jtName.setPreferredSize(new Dimension(16, 30));

  JLabel jlAuthor = new JLabel("文章作者:");
  jtAuthor = new JTextField(16);
 // jtAuthor.setText(admin.getUserName());
  jtAuthor.setPreferredSize(new Dimension(16, 30));

  JLabel jlTitle = new JLabel("文章标签:");
  jtTitle = new JTextField(16);
  jtTitle.setPreferredSize(new Dimension(16, 30));

  JLabel jlType = new JLabel("文章类型:");
  jtType = new JTextField(16);
  jtType.setPreferredSize(new Dimension(16, 30));

  JLabel jlStatus = new JLabel("文章状态:");
  jtStatus = new JTextField(16);
  jtStatus.setPreferredSize(new Dimension(16, 30));

  JLabel jlDate = new JLabel("创建时间:");
  jtDate = new JTextField(16);
  jtDate.setPreferredSize(new Dimension(16, 30));

  p1.setBounds(0, 0, 300, 220);
  p1.setBackground(new Color(0x0CDEBB));
  p1.add(jlName);
  p1.add(jtName);
  p1.add(jlAuthor);
  p1.add(jtAuthor);
  p1.add(jlTitle);
  p1.add(jtTitle);
  p1.add(jlType);
  p1.add(jtType);
  p1.add(jlStatus);
  p1.add(jtStatus);
  p1.add(jlDate);
  p1.add(jtDate);

  JLabel j1 = new JLabel();
  j1.setBounds(300, 0, 445, 220);
  j1.setIcon(new ImageIcon("src/imags/WriteArticle.png"));


  JTextPane quoteJTArea = new JTextPane();
  quoteJTArea.setBounds(745, 0, 230, 400);
  GetRandomQuote getRandomQuote = new GetRandomQuote();
  SentenceDao sentenceDao = new SentenceDao();
  Sentence sentence = sentenceDao.getSentence();
  quoteJTArea.setContentType("text/html");
  quoteJTArea.setText("<html>每日一言:<hr>" + getRandomQuote.getRandomQuote() + "<html><hr>" + "<html>每日金句:<hr>" + sentence.getContent() + "<html><hr>");
  quoteJTArea.setFont(new Font("简体", Font.PLAIN, 21));
  quoteJTArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  quoteJTArea.setEnabled(false);
  quoteJTArea.insertIcon(new ImageIcon("src/imags/1.png"));

  jTextPane = new JTextPane();
  jTextPane.setText("Dear Writer:\n"+"This Some Tip:\n1、编辑文本请单击编辑器按钮编辑文章\n"+"2、单击保存/继续编辑按钮可以实现保存和查询（查询条件为文章标题加作者名）\n"+"3、单击清空按钮会清空ID和作者以外的其他内容(创造时间自己生成)\n"+"4、单击发送按钮会将内容发送到本博客系统中\n"+"最后希望您能给本系统带来优质的文章内容和有效的技术分享\n"+"\t\t\t\t\t\t\t\t\tYours sincerely\n"+"\t\t\t\t\t\t\t\t\tRecall");
  jTextPane.setBounds(0, 250, 745, 375);
 // jTextPane.setContentType("text/html");
  jTextPane.setBackground(new Color(0xD9D2D2));


  JPanel p2 = new JPanel();
  p2.setLayout(null);
  p2.setBounds(0, 220, 745, 30);
  p2.setBackground(Color.black);

  JLabel jLabel = new JLabel("内容创作区:");
  jLabel.setBounds(10, 0, 150, 30);
  jLabel.setFont(new Font("微软简体", Font.PLAIN, 20));
  jLabel.setForeground(Color.white);

  JPanel p3 = new JPanel();
  p3.setLayout(new GridLayout(4, 1));
  JButton OpenNotepad = new JButton("打开编辑器");
  OpenNotepad.setForeground(new Color(0x0CDEBB));
  OpenNotepad.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {



    editor.setVisible(true);
    editor.setLocationRelativeTo(null);
//    Thread thread1=new Thread(new Runnable() {
//     @Override
//     public void run() {
//      editor.setVisible(true);
//      editor.setLocationRelativeTo(null);
//      jTextPane.setText(new Notepad().note.getContent());
//     }
//    });
//    thread1.run();

   }
  });

  //保存/继续编辑
  JButton Store = new JButton("保存/继续编辑");
  Store.setForeground(Color.green);
  Store.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    Article article=new Article();
    ArticleDao articleDao=new ArticleDao();
    //查询未编辑完成的文章
    if ("".equals(jtAuthor.getText())==false&&"".equals(jtName.getText())==false&&"".equals(jtTitle.getText())&&"".equals(jtType.getText())&&"".equals(jtStatus.getText())){
       article=articleDao.SelectNameAuthor(jtName.getText(), jtAuthor.getText());
       jtName.setText(article.getPost_name());
       jtAuthor.setText(article.getPost_author());
       jtTitle.setText(article.getPost_title());
       jtType.setText(article.getPost_type());
       jtStatus.setText(article.getPost_status());
       jtDate.setText(String.valueOf(article.getPost_date()));
       editor.textArea.setText(article.getPost_content());
       if (article.getPost_name()==null||article.getPost_author()==null){
        jtDate.setText("");
        JOptionPane.showMessageDialog(null, "未查询到该文章！换个信息再试试吧！");
       }else {
        JOptionPane.showMessageDialog(null, "成功查询到文章"+"\n作者："+article.getPost_author()+"\n文章标题："+article.getPost_name());
       }
    }else {    //保存
     Article article1=addArticle();
     ArticleDao articleDao1=new ArticleDao();
     if ("".equals(article1.getPost_author())){
      JOptionPane.showMessageDialog(null, "未填写作者信息保存失败");
     }
     else if ("".equals(article1.getPost_name())||"".equals(article1.getPost_content())){

      int result=JOptionPane.showConfirmDialog(null, "确认保存文章？可能缺少文章标题或内容，请勿恶意添加文章！！！","添加确认框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
      if (result==JOptionPane.YES_OPTION){

       boolean flag=articleDao1.addArticle(article1);
       if (flag)
       {
        JOptionPane.showMessageDialog(null, "文章添加成功！如果发现恶意添加文章将会封号处理！！！");
       }
       else{
        JOptionPane.showMessageDialog(null, "添加文章失败!");
       }
      }
      else if (result==JOptionPane.CANCEL_OPTION);
     }
     else {
      if (articleDao1.addArticle(article1))
      {
       JOptionPane.showMessageDialog(null, "文章添加成功！");
      }
      else{
       JOptionPane.showMessageDialog(null, "添加文章失败!");
      }
     }



//
//     article=addArticle();
//     articleDao.addArticle(article);
//     JOptionPane.showMessageDialog(null, "保存成功");
    }




   }
  });


  //清空
  JButton Clean = new JButton("清空");
  Clean.setForeground(Color.red);
  Clean.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    Article article=updateOtherIdAuthor();
    ArticleDao articleDao=new ArticleDao();
    if (articleDao.UpdateOtherIdAuthor(article)){
     jtAuthor.setText(article.getPost_author());
     jtName.setText(article.getPost_name());
     JOptionPane.showMessageDialog(null, "清空成功");
    }else {
     JOptionPane.showMessageDialog(null, "清空失败");
    }
   }
  });

  JButton Send = new JButton("发送");
  Send.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    Article article=addArticle();
    ArticleDao articleDao=new ArticleDao();
    if ("".equals(article.getPost_author())){
        JOptionPane.showMessageDialog(null, "未填写作者信息添加失败");
    }
    else if ("".equals(article.getPost_name())||"".equals(article.getPost_content())){

     int result=JOptionPane.showConfirmDialog(null, "确认添加文章？,缺少文章文章标题或内容","添加确认框",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
     if (result==JOptionPane.YES_OPTION){

      boolean flag=articleDao.addArticle(article);
      if (flag)
      {
       JOptionPane.showMessageDialog(null, "文章添加成功！如果发现恶意添加文章将会封号处理！！！");
      }
      else{
       JOptionPane.showMessageDialog(null, "添加文章失败!");
      }
     }
     else if (result==JOptionPane.CANCEL_OPTION);
    }
    else {
     if (articleDao.addArticle(article))
     {
      JOptionPane.showMessageDialog(null, "文章添加成功！");
     }
     else{
      JOptionPane.showMessageDialog(null, "添加文章失败!");
     }
    }
    }
  });
  Send.setForeground(new Color(0x8F8B8B));
  p3.setBounds(745, 400, 230, 190);

  jTextPane.setEnabled(false);

  p3.add(OpenNotepad);
  p3.add(Store);
  p3.add(Clean);
  p3.add(Send);


  p2.add(jLabel);
  add(p1);
  add(j1);
  add(quoteJTArea);
  add(jTextPane);
  add(p2);
  add(p3);
 }




 public Article addArticle(){
   Article article=new Article();
   Date nowDate=new Date();
   SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String format =dateFormat.format(nowDate);
   article.setPost_name(jtName.getText());
   article.setPost_author(jtAuthor.getText());
   article.setPost_title(jtTitle.getText());
   article.setPost_type(jtType.getText());
   article.setPost_status(jtStatus.getText());
   article.setPost_date(nowDate);
   article.setPost_content(editor.textArea.getText());


    return article;
 }

 public Article updateOtherIdAuthor(){
  Article article=new Article();
  article.setPost_name(jtName.getText());
  article.setPost_author(jtAuthor.getText());
  article.setPost_title(null);
  article.setPost_type(null);
  article.setPost_status(null);
  article.setPost_date(null);
  article.setPost_content(null);

  return article;


 }
}
