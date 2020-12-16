package com.zt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

/**
 * 游戏主界面
 * 继承Jpanel
 * @author 
 *
 */

public class PanelGame extends JPanel {
	
	
	/**
	 * 构造初始画面办
	 * @throws FileNotFoundException 
	 */
	BufferedImage bg;
	//英雄机
	HeroFly h=new HeroFly();
	//敌机  集合
//	Ep ep=new Ep();
	List<Ep>epList=new ArrayList<Ep>();
	
//获取子弹;
	List<Dan>dList=new ArrayList<Dan>();
	//分数
	int score;
	//游戏结束
	boolean ganeover=false;
	
	//游戏开始方法
	public void action() {
		//通过线程控制整体的运作
		
		new Thread() {
			//重写run
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true) {
					if(!ganeover) {
					//加入大量敌机，进入地图
					enterEp();
					//飞机移动
					epMove();
					
					
					//加入子弹
					enterDan(h);
					//子弹移动
					Move();
					//子弹碰撞
					shootDan();
					
					//英雄机碰撞
					shootHero();
					
				}
					//线程休息
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//图片重绘
					repaint();
				}
			}

			
		}.start();
		
		
		new Thread() {
			//重写run
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true) {
					if(!ganeover) {
				
					//加入子弹
					enterDan(h);
					//子弹移动
					Move();
					//子弹碰撞
					shootDan();
					
					//英雄机碰撞
					shootHero();
					
				}
					//线程休息
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//图片重绘
					repaint();
				}
			}

			
		}.start();
		
	}
	
	private void shootHero() {
		// TODO Auto-generated method stub
		for(int i=0;i<epList.size();i++) {
			//获取敌机
			Ep ep=epList.get(i);
			boolean bool=ep.hit(h);
			if(bool) {
				//英雄机血量减少
				h.hp--;
				//敌机消失
				epList.remove(ep);
				score+=100;
				
				if(h.hp<0) {
					//游戏结束
					ganeover=true;
					
					}
				}
			}
	};
	protected void epMove() {
		// TODO Auto-generated method stub
		
		//遍历飞机集合
		for(int i=0;i<epList.size();i++) {
			//获取飞机
			Ep ep=epList.get(i);
			//每一个都要移动
			ep.move();
			
			
			
		}
	}

	protected void Move() {
		// TODO Auto-generated method stub
		
		//遍历子弹集合
		for(int i=0;i<dList.size();i++) {
			//获取子弹
			Dan d=dList.get(i);
			//每一个都要移动
			d.move();
			
			
			
		}
	}
	
	
	protected void shootDan() {
		// TODO Auto-generated method stub
		
		//遍历子弹集合
		for(int i=0;i<dList.size();i++) {
			//获取子弹
			Dan d=dList.get(i);
			//每一个都要移动
			bang(d);
			
			
		}
	}
	private void bang(Dan d) {
		// TODO Auto-generated method stub
		for(int i=0;i<epList.size();i++) {
			//获取敌机
			Ep e=epList.get(i);
			//进行碰撞
			boolean bool= e.shootBy(d);
			//如果bool为true 表示碰到  false表示没有
			if(bool) {
				//碰到了
				e.hp--;
				if(e.hp==0){
					
					epList.remove(e);
					score+=100;
				}
				dList.remove(d);
				
				}
			}
	}
	/**
	 * 敌机进入地图
	 * @param frame
	 */
	//定义一个数字来记载飞机的数量
	int epIndex;
	private void enterEp() {
		epIndex++;
		if(epIndex>=5	) {
		// TODO Auto-generated method stub
		//获取敌机对象
		Ep ep=new Ep();
		epList.add(ep);
		epIndex=0;
		
		}
	}
	
	int Index;
	private void enterDan(HeroFly m) {
		Index++;
		if(Index>=20) {
		// TODO Auto-generated method stub
		//获取子弹对象
		Dan d=new Dan(h);
		dList.add(d);
		Dan d2=new Dan(h);
		d2.x-=40;
		dList.add(d2);
		Dan d3=new Dan(h);
		d3.x+=40;
		dList.add(d3);
		Index=0;
		
		
		}
	}
	
	public PanelGame(FrameGame frame) {
		
		this.setBackground(Color.pink);
		
		//初始化图片信息
		bg=App.getImg("src/img/bg2.jpg");
		
		/**
		 * 获取鼠标适配器
		 * 监听鼠标的行为方法
		 * 将鼠标适配器加入到监听器中
			
		 */
		MouseAdapter adapter=new MouseAdapter() {
			
			@Override
			//鼠标移动的方法
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				//获取鼠标的位置
				int mx=e.getX();
				int my=e.getY();
				//飞机移动
				h.mouseToMove(mx,my);
				//方法重绘
				repaint();
			}
		};
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		
		/**
		 * 键盘监听器
		 */
		KeyAdapter keydad=new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//键盘按下去
				super.keyPressed(e);
				
				//获取相应按键来做相应的事情
				int num=e.getKeyCode();

				if(num==KeyEvent.VK_UP) {
					//向上
					h.moveUp();
					repaint();
				}else if(num==KeyEvent.VK_LEFT) {
					//左
					h.moveLeft();
					repaint();
				}else if(num==KeyEvent.VK_RIGHT) {
					//右
					h.moveRight();
					repaint();
				}else if(num==KeyEvent.VK_DOWN) {
					//下
					h.moveDown();
					
				
					repaint();
				
				}
			}
			
			
		};
		frame.addKeyListener(keydad);
		
		
		
	}
	
	
	//专门画图片
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.drawImage(bg, 0, 0,null);
		
		
		
	
		//画子弹

		for(int i=0;i<dList.size();i++) {
			
			Dan d=dList.get(i);
			g.drawImage(d.img, d.x, d.y, d.w, d.h, null);
		}
		
		//敌机的集合,画出所有的敌机
			for(int i=0;i<epList.size();i++) {
			//获取敌机
			Ep ep=epList.get(i);
			g.drawImage(ep.img, ep.x, ep.y, ep.w, ep.h, null);
		}
			
			//画英雄机
			g.drawImage(h.img, h.x, h.y, h.w, h.h, null);
		//画分数
			g.setColor(Color.white);
			g.setFont(new Font("楷体",Font.BOLD,20));
			g.drawString("分数："+score, 10, 25);
		//画英雄机的血量
		for(int i=0;i<h.hp;i++){
			g.drawImage(h.img,400+i*35,10,30,30, null);
		}
		
		//画游戏结束
		if(ganeover) {
			g.setColor(Color.red);
			g.setFont(new Font("楷体",Font.BOLD,50));
			g.drawString("菜鸡，这都不会！！！", 100, 300);
		}
	}
}
