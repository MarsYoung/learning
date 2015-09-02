package com.marsyoung.file;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class FindFileApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8119185320549329260L;

	private JLabel fileDirectoryLabel;
	private JLabel keywordLabel;
	private JFileChooser fileDirectoryChooser;
	private JTextField keywordText;
	private JTextField fileDirectoryText;
	private JLabel consoleLabel;
	private JTextArea consoleArea;
	private JButton searchButton;

	public FindFileApp() {
		super("海底捞针");
		super.setSize(600, 400);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centered(this);
		this.setLayout(null);// 设置布局管理器为空
		fileDirectoryLabel = new JLabel("搜索路径：");
		fileDirectoryLabel.setBounds(new Rectangle(0, 0, 180, 30));// 参数分别是坐标x，y，宽，高
		this.add(fileDirectoryLabel);
		keywordLabel = new JLabel("关键词：");
		keywordLabel.setBounds(new Rectangle(0, 50, 180, 30));// 参数分别是坐标x，y，宽，高
		this.add(keywordLabel);
		consoleLabel = new JLabel("搜索结果：");
		consoleLabel.setBounds(new Rectangle(0, 80, 180, 30));// 参数分别是坐标x，y，宽，高
		this.add(consoleLabel);
		fileDirectoryText = new JTextField();
		fileDirectoryText.setBounds(new Rectangle(200, 0, 180, 30));
		this.add(fileDirectoryText);
		keywordText = new JTextField();
		keywordText.setBounds(new Rectangle(200, 50, 180, 30));
		this.add(keywordText);
		consoleArea = new JTextArea();
		consoleArea.setBounds(0, 400, 400, 400);
		this.add(consoleArea);
		searchButton = new JButton("搜索");
		searchButton.setBounds(300, 300, 180, 30);
		searchButton.addActionListener(this);
		this.add(searchButton);
	}

	// 布局居中方法
	public void centered(Container container) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int w = container.getWidth();
		int h = container.getHeight();
		container.setBounds((screenSize.width - w) / 2, (screenSize.height - h) / 2, w, h);
	}

	public static void main(String[] args) {
		new FindFileApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (fileDirectoryText.getText().equals("")) {
			chooseFileDirectory();
		} else if (keywordText.getText().equals("")) {
		} else {
			new FindFiles().find(new HashSet<File>(), fileDirectoryText.getText(), keywordText.getText());
		}

	}

	public void chooseFileDirectory() {
		fileDirectoryChooser = new JFileChooser();
		fileDirectoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileDirectoryChooser.showDialog(new JLabel(), "选择");
		if (result == JFileChooser.APPROVE_OPTION) {
			fileDirectoryText.setText(fileDirectoryChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	void find(Set<File> files, String path, String keyWord) {
		File currentDir=new File(path);
		if (!currentDir.exists())
			return;
		File[] subfiles = currentDir.listFiles();
		if (subfiles != null) {
			for (File file : subfiles) {
				// 如果是文件夹，则继续遍历
				find(files, file.getAbsolutePath(), keyWord);
				// 如果是文件则判断是否符合keyWord
				if (FileUtils.getFile(file.getAbsolutePath()).isFile()) {
					try {
						FileUtils.readLines(file).forEach(st->{
							if(StringUtils.contains(st, keyWord)){
								files.add(file);
								consoleArea.append(file.getAbsolutePath()+"\n");
							}
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
