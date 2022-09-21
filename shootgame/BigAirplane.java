package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;
//大敌机---飞行物、得分

public class BigAirplane extends FlyingObject implements Enemy{
	
	/*定义图片集*/
	private static BufferedImage[] images;
	
	/*静态代码块---优先加载所有图片*/
	static {
		images = new BufferedImage[5];//初始化图片数组，长度为5
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("bigplane" + i + ".png");
		}
	}
	
	/*大敌机发射子弹*/
	int xStep = 35;
	int yStep = 20;//固定子弹与机头的距离为20
	public EnemyBullet[] enemyShoot() {
		EnemyBullet[] bullet = new EnemyBullet[1];
		
		bullet[0] = new EnemyBullet(this.x + xStep, this.y + yStep+99);//改动3
		
		return bullet;
	}
	
	/*定义大敌机坐标增量*/
	private int speed;
	
	/*构造方法*/
	public BigAirplane() {
		super(69,99);
		speed = 2;
	}

	@Override
	public int getScore() {
		return 3;//得分
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
