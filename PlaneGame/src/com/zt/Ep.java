package com.zt;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 敌机类
 * @author 张韬
 *
 */
public class Ep extends FlyObject{
//继承父类
	
	//血量
	int hp;
	//速度
	int sp;
	//构造函数
	public Ep() {
		// TODO Auto-generated constructor stub
		//产生一个随机数
		Random r=new Random();
		int index=r.nextInt(14)+1;
		String path;
		
		if(index<10) {
			path="src/img/ep0"+index+".png";
		}else {
			path="src/img/ep"+index+".png";
		}
		
		//获取图片
		img=App.getImg(path);
		
		w=img.getWidth();
		h=img.getHeight();
		
		//定义位置
		x=r.nextInt(512-w);
		y=-h;
		
		hp=index;
		
		sp=17-index;
	}

	//每个飞机的移动
	public void move() {
		// TODO Auto-generated method stub
		y+=sp;
		
	}
	
	public boolean  shootBy(Dan d){
		boolean bool=x<=d.x+d.w&&x>=d.x-w&&y>=d.y-h&&y<=d.y+d.h;
		   return bool;
	   }
	
	public boolean  hit(HeroFly d){
		boolean bool=x<=d.x+d.w&&x>=d.x-w&&y>=d.y-h&&y<=d.y+d.h;
		  return bool;
	   }
	}	
		
	

