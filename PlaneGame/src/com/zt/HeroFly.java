package com.zt;
/**
 * 英雄飞机
 * @author 张韬
 *
 */

import java.awt.image.BufferedImage;

public class HeroFly extends FlyObject{

	int hp=3;
	
	
	/**
	 * 通过构造来初始化英雄机的信息
	 */
	public HeroFly() {
		//获取图片对象
		img=App.getImg("src/img/hero.png");
		
		//获取飞机的大小
		w=img.getWidth();
		h=img.getHeight();
		//出现的地方
		x=512/2-w/2;
		y=768-h;
		
	}

	public void mouseToMove(int mx,int my) {
		// TODO Auto-generated method stub
		x=mx-w/2;
		y=my-h/2;
	
		
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		y-=10;
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		x-=10;
	}

	public void moveRight() {
		// TODO Auto-generated method stub
		x+=10;
	}

	public void moveDown() {
		// TODO Auto-generated method stub
		y+=10;
	}
}
