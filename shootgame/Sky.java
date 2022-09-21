package com.tedu.shootgame.day03_shootgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//天空类---飞行物
public class Sky extends FlyingObject{
	
	/*定义背景图片*/
	private static BufferedImage image;
	
	/*静态代码块---加载图片*/
	static {
		image = loadImage("background.png");
	}
	
	//定义图片坐标增量S
	private int speed;
	//定义第二张图片的y坐标---本质是一张图
	private int y1;
	
	/*构造方法*/
	public Sky() {
		super(ShootGame.WIDTH, ShootGame.HEIGHT, 0, 0);
		speed = 1;
		y1 = - this.height;//第二张图片的初始位置
	}

	@Override
	public void step() {
		y += speed;
		y1 += speed;//同时下落
		if(y >= ShootGame.HEIGHT) {
			y = - ShootGame.HEIGHT;
		}
		if(y1 >= ShootGame.HEIGHT) {
			y1 = - ShootGame.HEIGHT;
		}
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public boolean outOfBounds() {
		return false;
	}
	
	//重写paintObject方法（重载）
	@Override
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
		g.drawImage(getImage(), x, y1, null);//绘制第二张图片
	}

}
