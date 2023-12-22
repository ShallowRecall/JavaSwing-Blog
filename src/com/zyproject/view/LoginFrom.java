//登录界面
package com.zyproject.view;

import com.zyproject.Dao.AdminDao;
import com.zyproject.Dao.UserDao;
import com.zyproject.Dao.WriterDao;
import com.zyproject.model.Admin;
import com.zyproject.model.User;
import com.zyproject.model.UserType;
import com.zyproject.model.Writer;
import com.zyproject.tool.CodeUtil;
import com.zyproject.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginFrom extends JFrame implements ActionListener, MouseListener {
    static Admin admin;
    private final JLabel jLabelCode;
    public LoginFrom(){
        final JLabel jlLoginName,jlName,jlPassword;
        final JTextField jtName,jtPassword;
        final JPanel p1,p2;
        final JButton butSign;
        final JCheckBox jcRem;
        setTitle("登录界面");
        setSize(849,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);


        p1=new JPanel();
        p1.setOpaque(false);
        p1.setBounds(113,113,600,376);
        p1.setLayout(null);
        JLabel jLabel1=new JLabel();
        jLabel1.setSize(600,376);
        jLabel1.setIcon(new ImageIcon("src/imags/background1.png"));
        p1.add(jLabel1);
        this.add(p1);

        JLabel jLabel2=new JLabel();
        jLabel2.setBounds(111,142,203,136);
        jLabel2.setIcon(new ImageIcon("src/imags/login_img.png"));
        jLabel1.add(jLabel2);

        JLabel jLabel3=new JLabel("个人博客系统");
        jLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 23));
        jLabel3.setBounds(140,70,300,100);
        jLabel1.add(jLabel3);

        jlLoginName=new JLabel("用户登录");
        jlLoginName.setBounds(335,40,100,100);
        jLabel1.add(jlLoginName);
        jlLoginName.setFont(new Font("宋体", Font.PLAIN, 16));

        p2=new JPanel();
        p2.setOpaque(false);
        p2.setBounds(330,105,250,250);
        p2.setLayout(null);
        jLabel1.add(p2);

        jlName=new JLabel("用户名：");
        jlName.setIcon(new ImageIcon("src/imags/username.png"));
        jlName.setBounds(0,10,100,30);
        jtName=new JTextField(16);
        jtName.setBounds(100,10,100,30);


        jlPassword=new JLabel("密码：");
        jlPassword.setIcon(new ImageIcon("src/imags/password.png"));
        jlPassword.setBounds(0,50,100,30);
        jtPassword=new JPasswordField(16);
        jtPassword.setBounds(100,50,100,30);

        JLabel jlCode=new JLabel("验证码：");
        jlCode.setIcon(new ImageIcon("src/imags/Code.png"));
        jlCode.setBounds(5,90,100,30);
        JTextField jtCode=new JTextField();
        jtCode.setBounds(100,90,60,30);


        jLabelCode=new JLabel();
        jLabelCode.setFont(new Font("微软雅黑",Font.PLAIN,16));
        jLabelCode.setBounds(170,90,80,30);

        //获取验证码类
        String code= CodeUtil.getCode();
        jLabelCode.setText(code);
        jLabelCode.addMouseListener(this);


        //用户类型选择框
        JLabel lbluserType = new JLabel("用户类型：");
        lbluserType.setIcon(new ImageIcon("src/imags/userType.png"));
        lbluserType.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        lbluserType.setBounds(120,280,100,30);
        JComboBox cbouserType = new JComboBox();
        cbouserType.setModel(new DefaultComboBoxModel(new UserType[]{UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
        cbouserType.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        cbouserType.setBounds(200,280,100,30);

        jLabel1.add(lbluserType);
        jLabel1.add(cbouserType);


        //记住密码勾选框
        jcRem=new JCheckBox("记住密码");
        jcRem.setBounds(5,140,100,30);
        p2.add(jcRem);



        JLabel jlForget=new JLabel("忘记密码");
        jlForget.setBounds(145,140,100,30);
        p2.add(jlForget);

        //登录按钮
        butSign=new JButton();
        butSign.setBounds(5,170,200,40);
        butSign.setIcon(new ImageIcon("src/imags/sign.png"));
        butSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jtName.getText();
                String password =jtPassword.getText();
                UserType selectedItem = (UserType) cbouserType.getSelectedItem();
                if (StringUtil.isEmpty(username)){
                    JOptionPane.showMessageDialog(LoginFrom.this,"用户名不能为空！");
                }
                if(StringUtil.isEmpty(password)){
                    JOptionPane.showMessageDialog(LoginFrom.this,"密码不能为空！");
                }
                if ("管理员".equals(selectedItem.getName())){
                    AdminDao adminDao=new AdminDao();
                   // Admin admin;
                    admin=adminDao.login(username);
                    adminDao.closeDao();
                    if (admin == null) {
                        JOptionPane.showMessageDialog(null, "用户名不正确！");
                    } else if (admin.getPassword().equals(password)) {
                        if (jtCode.getText().equals(jLabelCode.getText())){
                            JOptionPane.showMessageDialog(null, "欢迎【" + selectedItem.getName() +
                                    "】：" + admin.getUserName()+ "登录本系统！");
                            MainForm frmMain = new MainForm(selectedItem, admin);
                            frmMain.setVisible(true);
                        }else
                            JOptionPane.showMessageDialog(null, "验证码错误！");

                    } else {
                        JOptionPane.showMessageDialog(null, "密码不正确！");
                    }
                }else if ("作者".equals(selectedItem.getName())){
                    WriterDao writerDao=new WriterDao();
                    Writer writer=writerDao.login(username);
                    writerDao.closeDao();
                    if (writer == null) {
                        JOptionPane.showMessageDialog(null, "用户名不正确！");
                    } else if (writer.getPassword().equals(password)) {

                        if (jtCode.getText().equals(jLabelCode.getText())){
                            JOptionPane.showMessageDialog(null, "欢迎【" + selectedItem.getName() +
                                    "】：" + writer.getUserName()+ "登录本系统！");
                            MainForm frmMain = new MainForm(selectedItem, writer);
                            frmMain.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "密码不正确！");
                        }
                        }else
                        JOptionPane.showMessageDialog(null, "验证码错误！");
                }else if ("普通用户".equals(selectedItem.getName())){
                    UserDao userDao=new UserDao();
                    User user=userDao.login(username);
                    userDao.closeDao();
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "用户名不正确！");
                    } else if (user.getPassword().equals(password)) {

                        if (jtCode.getText().equals(jLabelCode.getText())){
                            JOptionPane.showMessageDialog(null, "欢迎【" + selectedItem.getName() +
                                    "】：" + user.getUserName()+ "登录本系统！");
                            MainForm frmMain = new MainForm(selectedItem,user);
                            frmMain.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "密码不正确！");
                        }
                        }else
                        JOptionPane.showMessageDialog(null, "验证码错误！");
                }

            }
        });

        p2.add(butSign);

        p2.add(jlName);
        p2.add(jtName);

        p2.add(jlPassword);
        p2.add(jtPassword);

        p2.add(jlCode);
        p2.add(jtCode);
        p2.add(jLabelCode);



        ImageIcon backgroundImag=new ImageIcon("src/imags/background.png");
        JLabel jLabel=new JLabel(backgroundImag);
        ((JComponent)this.getContentPane()).setOpaque(false);
        jLabel.setBounds(0,0,backgroundImag.getIconWidth(),backgroundImag.getIconHeight());
        this.getLayeredPane().add(jLabel,new Integer(Integer.MIN_VALUE));
    }


    public static void main(String[] args) {
        new LoginFrom();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==jLabelCode){
            String code=CodeUtil.getCode();
            jLabelCode.setText(code);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
