//管理员界面
package com.zyproject.view;

import com.zyproject.model.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private final JPanel contentPane;
    private final JDesktopPane desktopPane;
    public static UserType userType;
    public static Object userObject;
   // JLabel jLabelMain=new JLabel();
    public MainForm(UserType userType,Object userObject){
        this.userType=userType;
        this.userObject=userObject;
        UserType selectedItem = userType;
        setTitle("管理员界面");
        setSize(998,695);
        setResizable(false);

        JMenuBar jMenuBar=new JMenuBar();
        //用户管理
        JMenu jMenuUser=new JMenu("用户系统");
        jMenuUser.setIcon((new ImageIcon("src/imags/用户.png")));
        //所有用户
        JMenuItem jMenuItemAllUser=new JMenuItem("所有用户");
        jMenuItemAllUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowUser showUser=new ShowUser();
                showUser.setVisible(true);
                desktopPane.add(showUser);
            }
        });
        //添加用户
        JMenuItem jMenuItemAddUser=new JMenuItem("添加用户");
        jMenuItemAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUser addUser=new AddUser();
                addUser.setVisible(true);
                desktopPane.add(addUser);
            }
        });
        //个人资料
        JMenuItem jMenuItemPersonalData=new JMenuItem("个人资料");
        jMenuItemPersonalData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PerData perData=new PerData();
                perData.setVisible(true);
                desktopPane.add(perData);
            }
        });

        //身份认定
        JMenuItem jMenuItemIdentity=new JMenuItem("身份确认");
        jMenuItemIdentity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Identity identity=new Identity();
                identity.setVisible(true);
                desktopPane.add(identity);
            }
        });




        jMenuBar.add(jMenuUser);
        jMenuUser.add(jMenuItemAllUser);
        jMenuUser.add(jMenuItemAddUser);
        jMenuUser.add(jMenuItemPersonalData);
        jMenuUser.add(jMenuItemIdentity);

        //文章管理
        JMenu jMenuArticle=new JMenu("文章管理");
        jMenuArticle.setIcon(new ImageIcon("src/imags/文章.png"));
        //所有文章
        JMenuItem jMenuItemAllArticle=new JMenuItem("所有文章");
        jMenuItemAllArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowArticle showArticle=new ShowArticle();
                showArticle.setVisible(true);
                desktopPane.add(showArticle);
            }
        });
        //写文章
        JMenuItem jMenuItemWriterArticle=new JMenuItem("写文章");
        jMenuItemWriterArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteArticle writeArticle=new WriteArticle();
                writeArticle.setVisible(true);
                desktopPane.add(writeArticle);
            }
        });
        //分类
        JMenuItem jMenuItemSort=new JMenuItem("分类");
        //标签
        JMenuItem jMenuItemLabel=new JMenuItem("标签");
        jMenuBar.add(jMenuArticle);
        jMenuArticle.add(jMenuItemAllArticle);
        jMenuArticle.add(jMenuItemAllArticle);
        jMenuArticle.add(jMenuItemWriterArticle);
   //     jMenuArticle.add(jMenuItemSort);
   //     jMenuArticle.add(jMenuItemLabel);

        //系统设置
        JMenu jMenuInstall=new JMenu("系统设置");
        jMenuInstall.setIcon(new ImageIcon("src/imags/系统设置.png"));
        //退出系统
        JMenuItem jMenuItemQuit=new JMenuItem("退出系统");
        jMenuItemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(1);    //程序全部退出
                dispose();           //仅退出窗口
            }
        });
        //关于我们
        JMenuItem jMenuItemAboutMe=new JMenuItem("关于我们");
        jMenuItemAboutMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutMe aboutMe=new AboutMe();
                aboutMe.setVisible(true);
                desktopPane.add(aboutMe);
            }
        });




        jMenuBar.add(jMenuInstall);
        jMenuInstall.add(jMenuItemQuit);
        jMenuInstall.add(jMenuItemAboutMe);


        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);




        desktopPane=new JDesktopPane();
        desktopPane.setOpaque(false);
        contentPane.add(desktopPane,BorderLayout.CENTER);

        setLocationRelativeTo(null);

        ImageIcon backgroundImag=new ImageIcon("src/imags/preview.png");
        JLabel jLabel=new JLabel(backgroundImag);
        ((JComponent)this.getContentPane()).setOpaque(false);
        jLabel.setBounds(0,0,backgroundImag.getIconWidth(),backgroundImag.getIconHeight());
        this.getLayeredPane().add(jLabel,new Integer(Integer.MIN_VALUE));



        setJMenuBar(jMenuBar);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);




        //权限管理

        if ("作者".equals(selectedItem.getName())){
           jMenuUser.setEnabled(false);

        }

        if ("普通用户".equals(selectedItem.getName())){
            jMenuUser.setEnabled(false);
            jMenuItemWriterArticle.setEnabled(false);
        }

    }

    public static void main(String[] args) {
       new MainForm(userType,userObject);
    }
}
