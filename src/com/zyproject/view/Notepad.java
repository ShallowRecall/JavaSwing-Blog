package com.zyproject.view;

import com.zyproject.model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 文本编辑器-版本二
 */
public class  Notepad extends JFrame implements ActionListener {
    JMenuBar menuBar = new JMenuBar();//菜单
    /*窗体菜单大致由菜单栏、菜单和菜单项三部分组成，对于一个窗体，首先要添加一个JMenuBar,然后在JMenu中添加JMenultem*/
    /*菜单栏*/
    JMenu file = new JMenu("文件"),
            edit = new JMenu("编辑"),
            format = new JMenu("格式"),
            view = new JMenu("查看"),
            help = new JMenu("帮助");
    /*菜单项*/
    JMenuItem[] menuItem = {new JMenuItem("新建"), new JMenuItem("打开"),
            new JMenuItem("保存"), new JMenuItem("退出"),
            new JMenuItem("全选"), new JMenuItem("复制"),
            new JMenuItem("剪切"), new JMenuItem("粘贴"),
            new JMenuItem("查找"), new JMenuItem("替换"),
            new JMenuItem("自动换行"), new JMenuItem("字体"),
            new JMenuItem("普通"), new JMenuItem("粗体"),
            new JMenuItem("斜体"), new JMenuItem("字号"),
            new JMenuItem("12"), new JMenuItem("24"),
            new JMenuItem("36"), new JMenuItem("颜色"),
            new JMenuItem("背景"), new JMenuItem("状态栏"),
            new JMenuItem("关于")
    };
    //这是在JMenu中添加JMenuItem，JMenuItem是最小单元
    JTextArea textArea;
    private JScrollPane js;
    private JPanel jp;
    private FileDialog openFileDialog;
    private FileDialog saveFileDialog;
    private Toolkit toolKit;
    private Clipboard clipboard;
    private String fileName;
    private JMenu add;
    Note note=new Note();

    //构造函数
    public  Notepad() {
        fileName = "无标题";
        toolKit = Toolkit.getDefaultToolkit();
        clipboard = toolKit.getSystemClipboard();
        textArea = new JTextArea();
        js = new JScrollPane(textArea);//把文本区放到一个滚动窗格中
        jp = new JPanel();
        openFileDialog = new FileDialog(this, "打开", FileDialog.LOAD);//加载
        saveFileDialog = new FileDialog(this, "另存为", FileDialog.SAVE);//保存
        js.setVerticalScrollBarPolicy//设置垂直滚动条
                (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jp.setLayout(new GridLayout(1, 1));/*作用是设置布局管理器为GridLayout,将界面区域划分为1行1列，每个添加到该界面的组件占一个格子的空间*/
        jp.add(js);//将面板中的滚动面板加载到窗体
        add(jp);//将面板加载到窗体
        setTitle("文本编辑器");
        setFont(new Font("Times New Roman", Font.PLAIN, 15));//设置字体
        setBackground(Color.white);//背景颜色
        setSize(800, 600);//窗体的高度、宽度
        setJMenuBar(menuBar);//显示菜单

        add=new JMenu("添加");
        JMenuItem jMenuItem=new JMenuItem("添加");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                note.setContent(textArea.getText());
                WriteArticle writeArticle=new WriteArticle();
                writeArticle.jTextPane.setText(note.getContent());
            }
        });



       // add.add(jMenuItem);
      //  menuBar.add(add);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(view);
        menuBar.add(help);
        //将menuItem中前4个菜单项放入菜单栏中的文件栏下
        for (int i = 0; i < 4; i++) {
            file.add(menuItem[i]);
        }
        //将menuItem中接下去7个菜单项放入菜单栏中的编辑栏下
        for (int i = 4; i < 10; i++) {
            edit.add(menuItem[i]);
        }
        format.add(menuItem[10]);//为格式栏添加自动换行项
        JMenu optionsMenu1 = new JMenu("字体");//字体项中还有选择项
        format.add(optionsMenu1);//为格式栏添加字体项中的其它选择项
        optionsMenu1.add(menuItem[12]);
        optionsMenu1.add(menuItem[13]);
        optionsMenu1.add(menuItem[14]);
        JMenu optionsMenu2 = new JMenu("字号");
        format.add(optionsMenu2);//字号项中的选择项
        optionsMenu2.add(menuItem[16]);
        optionsMenu2.add(menuItem[17]);
        optionsMenu2.add(menuItem[18]);
        format.add(menuItem[19]);//为格式栏添加颜色项
        format.add(menuItem[20]);//为格式栏添加背景项
        view.add(menuItem[21]);//为查看栏添加状态栏项
        help.add(menuItem[22]);//为帮助栏添加关于项
        //给各个项增加监听功能
        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i].addActionListener(this);
        }
    }

    /*在输入文本框中显示输入文本框中的文本，并在其前后加上其他字符*/
    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        /*新建*/
        if (eventSource == menuItem[0]) {
            textArea.setText("");
        }
        /*打开*/
        else if (eventSource == menuItem[1]) {
            openFileDialog.setVisible(true);
            fileName = openFileDialog.getDirectory()
                    + openFileDialog.getFile();
            if (fileName != null) {
                openFile(fileName);
            }
        }
        /*保存*/
        else if (eventSource == menuItem[2]) {
            saveFileDialog.setVisible(true);
            fileName = saveFileDialog.getDirectory()
                    + saveFileDialog.getFile();
            if (fileName != null) {
                writeFile(fileName);
            }
        }
        /*退出*/
        else if (eventSource == menuItem[3]) {
            //System.exit(1);
            dispose();
        }
        /*全选*/
        else if (eventSource == menuItem[4]) {
            textArea.selectAll();
        }
        /*复制*/
        else if (eventSource == menuItem[5]) {
            String text = textArea.getSelectedText();
            StringSelection selection = new StringSelection(text);
            clipboard.setContents(selection, null);
        }
        /*剪切*/
        else if (eventSource == menuItem[6]) {
            String text = textArea.getSelectedText();
            StringSelection selection = new StringSelection(text);
            clipboard.setContents(selection, null);
            textArea.replaceRange("", textArea.getSelectionStart(),
                    textArea.getSelectionEnd());
        }
        /*粘贴*/
        else if (eventSource == menuItem[7]) {
            Transferable contents = clipboard.getContents(this);
            if (contents == null) {
                return;
            }
            String text;
            text = "";
            try {
                text = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception ex) {
            }
            textArea.replaceRange(text, textArea.getSelectionStart(),
                    textArea.getSelectionEnd());
        }

        /*查找*/
        else if (eventSource == menuItem[8]) {
            search();
        }
        /*替换*/
        else if (eventSource == menuItem[9]) {
            replace();
        }
        /*自动换行*/
        else if (eventSource == menuItem[10]) {
            if (textArea.getLineWrap()) {
                textArea.setLineWrap(false);
            } else {
                textArea.setLineWrap(true);
            }
        }
        /*字体中的普通*/
        else if (eventSource == menuItem[12]) {
            afile();
        }
        /*字体中的粗体*/
        else if (eventSource == menuItem[13]) {
            bfile();
        }
        /*字体中的斜体*/
        else if (eventSource == menuItem[14]) {
            cfile();
        }
        /*字体中的字号12*/
        else if (eventSource == menuItem[16]) {
            Font font = textArea.getFont();
            int style = font.getStyle();
            textArea.setFont(new Font(font.getName(), style, 12));
        }
        /*字体中的字号24*/
        else if (eventSource == menuItem[17]) {
            Font font = textArea.getFont();
            int style = font.getStyle();
            textArea.setFont(new Font(font.getName(), style, 24));
        }
        /*字体中的字号36*/
        else if (eventSource == menuItem[18]) {
            Font font = textArea.getFont();
            int style = font.getStyle();
            textArea.setFont(new Font(font.getName(), style, 36));
        }
        /*颜色*/
        else if (eventSource == menuItem[19]) {
            Color newColor = JColorChooser.showDialog(this, "颜色", textArea.getForeground());
            if (newColor != null) {
                textArea.setForeground(newColor);
            }
        }
        /*背景*/
        else if (eventSource == menuItem[20]) {
            Color newColor = JColorChooser.showDialog(this, "调色板", textArea.getBackground());
            if (newColor != null) {
                textArea.setBackground(newColor);
            }
        }
        /*状态*/
        else if (eventSource == menuItem[21]) {
            String view = "文本编辑器\n"
                    + "状态良好\n";
            JOptionPane.showConfirmDialog(null, view, "状态",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
        /*关于*/
        else if (eventSource == menuItem[22]) {
            String help = "文本编辑器\n"
                    + "制作者：Jack魏整理制作\n"
                    + "百度搜索：Jack魏";
            JOptionPane.showConfirmDialog(null, help, "关于文件",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*字体中的普通字体调用*/
    public void afile() {
        textArea.setFont(new Font("楷体", Font.PLAIN, textArea.getFont().getSize()));
    }

    /*字体中的粗体字体调用*/
    public void bfile() {
        textArea.setFont(new Font("楷体", Font.BOLD, textArea.getFont().getSize()));
    }

    /*字体中的斜体字体调用*/
    public void cfile() {
        textArea.setFont(new Font("楷体", Font.ITALIC, textArea.getFont().getSize()));
    }

    /*打开文件*/
    public void openFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader readIn = new FileReader(file);
            int size = (int) file.length();
            int charsRead = 0;
            char[] content = new char[size];
            while (readIn.ready()) {
                charsRead += readIn.read(content, charsRead, size - charsRead);
            }
            readIn.close();
            textArea.setText(new String(content, 0, charsRead));
        } catch (Exception e) {
            System.out.println("Error opening file!");
        }
    }

    /*保存文件*/
    public void writeFile(String fileName) {
        try {
            File file = new File(fileName);
            FileWriter write = new FileWriter(file);
            write.write(textArea.getText());
            write.close();
        } catch (Exception e) {
            System.out.println("Error closing file!");
        }
    }

    /*编辑菜单下查找选择项的调用*/
    public void search() {
        final JDialog findDialog = new JDialog(this, "查找", true);
        Container con = findDialog.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchContentLabel = new JLabel(" 查找内容:");
        final JTextField findText = new JTextField(15);
        ButtonGroup bGroup = new ButtonGroup();
        final JRadioButton up = new JRadioButton("向上");//注意
        final JRadioButton down = new JRadioButton("向下");
        up.setSelected(true);
        bGroup.add(up);
        bGroup.add(down);
        JButton search = new JButton("查找");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = 0, b = 0;
                int FindStartPos = textArea.getCaretPosition();
                String str1, str2;
                str1 = textArea.getText();
                str2 = findText.getText();
                if (up.isSelected()) {
                    if (textArea.getSelectedText() == null) {
                        a = str1.lastIndexOf(str2, FindStartPos - 1);
                    } else {
                        a = str1.lastIndexOf(str2, FindStartPos - findText.getText().length() - 1);
                    }
                } else if (down.isSelected()) {
                    if (textArea.getSelectedText() == null) {
                        a = str1.indexOf(str2, FindStartPos);
                    } else {
                        a = str1.indexOf(str2, FindStartPos - findText.getText().length() + 1);
                    }
                }
                if (a > -1) {
                    if (up.isSelected()) {
                        textArea.setCaretPosition(a);
                        b = findText.getText().length();
                        textArea.select(a, a + b);
                    } else if (down.isSelected()) {
                        textArea.setCaretPosition(a);
                        b = findText.getText().length();
                        textArea.select(a, a + b);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "找不到您查找的内容!", "记事本",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel direction = new JPanel();
        direction.setBorder(BorderFactory.createTitledBorder("方向"));//注意
        direction.add(up);
        direction.add(down);
        topPanel.add(searchContentLabel);
        topPanel.add(findText);
        bottomPanel.add(direction);
        topPanel.add(search);
        con.add(topPanel);
        con.add(centerPanel);
        con.add(bottomPanel);
        findDialog.setSize(450, 200);
        findDialog.setResizable(true);
        findDialog.setLocation(150, 150);
        findDialog.setVisible(true);
    }

    /*编辑菜单下替换选择项的调用*/
    public void replace() {
        final JDialog findDialog = new JDialog(this, "替换", true);
        Container con = findDialog.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchContentLabel = new JLabel("查找内容:");
        JLabel replaceContentLabel = new JLabel("替换为:");
        final JTextField findText = new JTextField(15);
        final JTextField replaceText = new JTextField(15);
        final JButton replace = new JButton("替换");
        replace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setCaretPosition(0);
                int a = 0, b = 0, replaceCount = 0;
                if (findText.getText().length() == 0) {
                    JOptionPane.showMessageDialog(findDialog, "请填写查找内容!",
                            "提示", JOptionPane.WARNING_MESSAGE);
                    findText.requestFocus(true);
                    return;
                }
                while (a > -1) {
                    int FindStartPos = textArea.getCaretPosition();
                    String str1, str2;
                    str1 = textArea.getText();
                    str2 = findText.getText();
                    if (textArea.getSelectedText() == null) {
                        a = str1.indexOf(str2, FindStartPos);
                    } else {
                        a = str1.indexOf(str2, FindStartPos - findText.getText().length() + 1);
                    }
                    if (a > -1) {
                        textArea.setCaretPosition(a);
                        b = findText.getText().length();
                        textArea.select(a, a + b);
                    } else {
                        if (replaceCount == 0) {
                            JOptionPane.showMessageDialog(findDialog, "找不到您查找的内容!", "文本编辑器",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(findDialog, "成功替换"
                                    + replaceCount + "个匹配内容!", "替换成功", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    if (replaceText.getText().length() > 0 && textArea.getSelectedText() != null) {
                        textArea.replaceSelection(replaceText.getText());
                        replaceCount++;
                    }
                }
            }
        });
        JPanel centerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel replacePanel = new JPanel();
        replacePanel.add(replace);
        topPanel.add(searchContentLabel);
        topPanel.add(findText);
        centerPanel.add(replaceContentLabel);
        centerPanel.add(replaceText);
        con.add(topPanel);
        con.add(centerPanel);
        con.add(replacePanel);
        findDialog.setSize(450, 200);
        findDialog.setResizable(true);
        findDialog.setLocation(150, 150);
        findDialog.setVisible(true);

    }

    public static void main(String[] args) {
       Notepad notepad=new Notepad();
        notepad.setVisible(true);
    }

}

