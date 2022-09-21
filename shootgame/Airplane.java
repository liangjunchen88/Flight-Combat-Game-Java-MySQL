package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;
//小敌机---飞行物、得分

public class Airplane extends FlyingObject implements Enemy{
	
	/*定义图片集*/
	private static BufferedImage[] images;
	
	/*静态代码块---优先加载所有图片*/
	static {
		images = new BufferedImage[5];//初始化图片数组，长度为5
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("airplane" + i + ".png");
		}
	}
	
	/*定义大敌机坐标增量*/
	private int speed;
	
	/*构造方法*/
	public Airplane() {
		super(49,36);
		speed = 3;
	}

	@Override
	public int getScore() {
		return 1;//得分
	}

	@Override
	public void step() {
		y += speed;//向下
		
	}
	
	//定义死了的下标
	int deadIndex = 1;

	@Override
	public BufferedImage getImage() {
		if(isLife()) {//若状态为活着，则返回第一张图
			return images[0];
		}else if(isDead()) {//如果碰撞则从第二张图片开始进行切换---爆炸效果
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
		return this.y >= ShootGame.HEIGHT;//判断是否达到窗体底部
	}

	
}
