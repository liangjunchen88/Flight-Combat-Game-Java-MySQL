package com.tedu.shootgame.day03_shootgame;
//敌人子弹类---飞行物

import java.awt.image.BufferedImage;

public class EnemyBullet extends FlyingObject{
	/*定义图片*/
	public static BufferedImage image;
	
	/*静态代码块---加载图片*/
	static {
		image = loadImage("waterdrop.png");
	}
	
	/*定义y坐标增量*/
	private int speed;
	
	/*构造方法*/
	public EnemyBullet(int x, int y) {
		super(17,25,x,y);
		speed = 3;
	}

	@Override
	public void step() {
		y += speed;//向下
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
		//改动1
		return this.y >= 700-this.height;
	}
}
