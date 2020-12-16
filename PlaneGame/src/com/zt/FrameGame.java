package com.zt;

import java.awt.Color;
import java.io.FileNotFoundException;

import javax.swing.JFrame;



public class FrameGame extends JFrame{

	public FrameGame() {
				//设置标题
				this.setTitle("飞机大战");
				//设置窗口
				this.setBounds(0,0, 512, 768);
				//居中效果
				this.setLocationRelativeTo(null);
				//关闭按钮默认退出程序
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				//窗体大小不能改变
				this.setResizable(false);
	}
	
	
	
	public static void main(String[] args)  {
		//创建窗体对象
		FrameGame fg= new FrameGame();
		//创建面板
		PanelGame pg=new PanelGame(fg);
		
	
		
		
		pg.action();
		//将面板添加到窗体中去
		fg.add(pg);
		//显示界面
		fg.setVisible(true);
		
		
	}
	
}
