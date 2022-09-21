package com.tedu.shootgame.day03_shootgame;
//子弹类---飞行物

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	
	/*定义图片*/
	public static BufferedImage image;
	
	/*静态代码块---加载图片*/
	static {
		image = loadImage("bullet.png");
	}
	
	/*定义y坐标增量*/
	private int speed;
	
	/*构造方法*/
	public Bullet(int x, int y) {
		super(8,14,x,y);
		speed = 3;
	}

	@Override
	public void step() {
		y -= speed;//向上
	}

	@Override
	public BufferedImage getImage() {
		if(isLife()) {
			return image;
		}else if(isDead()){
			state = REMOVE;
		}
		return null;
	}

	@Override
	public boolean outOfBounds() {
		return this.y <= -this.height;
	}

}
