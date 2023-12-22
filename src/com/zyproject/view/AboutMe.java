package com.zyproject.view;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
public class AboutMe extends JInternalFrame {
    public AboutMe (){
        setTitle("关于我");
        setBounds(340, 100,320 ,420 );
        setClosable(true);
        setIconifiable(true);
        JEditorPane jEditorPane=new JEditorPane();
        jEditorPane.setEditable(false);
        jEditorPane.setContentType("text/html;charset=utf-8");
        jEditorPane.setText("<a href='http://blog.qianyiwb.com'>浅忆博客</a> </p><a href='http://qybk.qianyiwb.com'>Shallow Memory Blog</a> </p><a href='http://bk.qianyiwb.com'>Recall Blog</a>");
        jEditorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType()== HyperlinkEvent.EventType.ACTIVATED){
                    if (Desktop.isDesktopSupported()){
                        try{
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        jEditorPane.setBounds(0,350,300, 30);
        jEditorPane.setOpaque(false);
        JLabel jLabel1=new JLabel();
        jLabel1.setBounds(0, 30, 320, 320);
        jLabel1.setIcon(new ImageIcon("src/imags/微信码.jpeg"));
        add(jEditorPane);
        add(jLabel1);
    }
}
