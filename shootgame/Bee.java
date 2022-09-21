package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;
import java.util.Random;

//小蜜蜂类---飞行物、奖励

public class Bee extends FlyingObject implements Award{
	
	/*定义图片集*/
	private static BufferedImage[] images;
	
	/*静态代码块---加载图片*/
	static {
		images = new BufferedImage[5];
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("bee" + i + ".png");
		}
	}
	
	private int xSpeed;
	private int ySpeed;
	private int awardType;//奖励类型
	
	/*构造方法*/
	public Bee() {
		super(60,50);
		xSpeed = 1;
		ySpeed = 2;
		Random rand = new Random();
		awardType = rand.nextInt(2);//0或1
	}

	@Override
	public int getAwardType() {
		return awardType;
	}

	@Override
	public void step() {
		x += xSpeed;
		y += ySpeed;
		//判断蜜蜂碰到左右边界
		if(x <= 0 || x >= ShootGame.WIDTH - this.width) {
			xSpeed *= -1;
		}
	}
	
	int deadIndex =1;

	@Override
	public BufferedImage getImage() {
		if(isLife()) {
			return images[0];
		}else if(isDead()) {
			BufferedImage img = images[deadIndex ++];
			if(deadIndex == images.length) {
				state = REMOVE;
			}
			return img;
		}
		return null;
	}

	@Override
	public boolean outOfBounds() {
		return this.y >= ShootGame.HEIGHT;
	}
}
