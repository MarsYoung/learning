package com.marsyoung.file;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
 
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class FindFileApp extends JFrame {

	private static final long serialVersionUID = 8119185320549329260L;
	
	private JLabel fileDirectoryLabel;
	private JLabel keywordLabel;
	private JFileChooser fileDirectoryChooser;
	private JTextField fileTextField;
	private JLabel consoleLabel;
	private JTextArea consoleArea;
	
	
	private JButton btLog;
    private JTextField tfUser;
    private JPasswordField tfPwd;
    private JCheckBox pwdKeep;
    private JComboBox adminType;
 
    public FindFileApp() {
        super("海底捞针");
        super.setSize(380, 292);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centered(this);
        btLog = new JButton("登     录");
        btLog.setBounds(new Rectangle(93, 220, 180, 30));//参数分别是坐标x，y，宽，高
        this.setLayout(null);//设置布局管理器为空
        this.add(btLog);
        tfUser = new JTextField();
        tfUser.setBounds(new Rectangle(73, 115, 220, 25));
        this.add(tfUser);
        tfPwd = new JPasswordField();
        tfPwd.setBounds(new Rectangle(73, 150, 220, 25));
        this.add(tfPwd);
        pwdKeep = new JCheckBox("记住密码");
        pwdKeep.setBounds(new Rectangle(68, 185, 110, 25));
        this.add(pwdKeep);
        adminType = new JComboBox(new String[] { "普通职员", "管理员", "高级管理员" });
        adminType.setBounds(new Rectangle(183, 185, 100, 25));
        this.add(adminType);
 
    }
    //布局居中方法
    public void centered(Container container) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int w = container.getWidth();
        int h = container.getHeight();
        container.setBounds((screenSize.width - w) / 2,
                (screenSize.height - h) / 2, w, h);
    }
}
