package com.zt;

public class Dan extends FlyObject{

	
	public Dan(HeroFly m) {
		// TODO Auto-generated constructor stub
		img=App.getImg("src/img/fire.png");
		
		//获取子弹的大小
		w=img.getWidth()/4;
		h=img.getHeight()/4;
		//出现的地方
		x=m.x+m.w/2-w/2;
		y=m.y-h;
	}
	
	public void move() {
		// TODO Auto-generated method stub
		y-=5;
		
	}
	
	
}
