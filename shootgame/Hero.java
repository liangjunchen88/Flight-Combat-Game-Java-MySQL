package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;

//英雄机---飞行物

public class Hero extends FlyingObject{
	
	/*定义图片集*/
	private static BufferedImage[] images;
	
	/*静态代码块---优先加载所有图片*/
	static {
		images = new BufferedImage[2];//初始化图片数组，长度为2
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("hero" + i + ".png");
		}
	}
	
	/*定义双倍火力和生命值*/
	private int life;
	private int doubleFire;//骆驼命名法、小驼峰命名法

	/*构造方法*/
	public Hero() {
		super(97,124, 150, 400);
		life = 3;
		doubleFire = 0;//骆驼为单倍
	}
	
	/*将鼠标移动至英雄机中间*/
	public void moveTo(int x, int y) {
		this.x = x - this.width / 2;
		this.y = y - this.height / 2;
	}

	@Override
	public void step() {
		// 不需要写，英雄机随鼠标运动
	}
	
	int index = 0;//定义下标索引
	
	@Override
	public BufferedImage getImage() {
		if(isLife()) {
			return images[index ++ %2]; //尾气喷射效果
		}
		return null;
	}
	
	/*英雄机发射子弹算法*/
	public Bullet[] shoot() {
		//获取1/4的英雄机宽
		int xStep = this.width / 4;
		int yStep = 20;//固定子弹与机头的距离为20
		//判断是否为双倍火力
		if(doubleFire > 0) {
			if(doubleFire > 100) {
				Bullet[] bullet = new Bullet[4];
				bullet[0] = new Bullet(this.x - 1 * xStep, this.y - yStep);
				bullet[1] = new Bullet(this.x + 1 * xStep, this.y - yStep);
				bullet[2] = new Bullet(this.x + 3 * xStep, this.y - yStep);
				bullet[3] = new Bullet(this.x + 5 * xStep, this.y - yStep);
				/*bullet[4] = new Bullet(this.x + 3 * xStep, this.y - yStep);
				bullet[5] = new Bullet(this.x + 5 * xStep, this.y - yStep);
				bullet[6] = new Bullet(this.x + 7 * xStep, this.y - yStep);
				bullet[7] = new Bullet(this.x + 9 * xStep, this.y - yStep);*/
				doubleFire -= 2;
				return bullet;
			}else {
				//定义子弹匣
				Bullet[] bullet = new Bullet[2];
				//1/4处
				bullet[0] = new Bullet(this.x + 1 * xStep, this.y - yStep);
				//3/4处
				bullet[1] = new Bullet(this.x + 3 * xStep, this.y - yStep);
				//当每发射一次，火力值减2
				doubleFire -= 2;
				return bullet;
			}
		}else {
			Bullet[] bullet = new Bullet[1];
			bullet[0] = new Bullet(this.x + 2 * xStep, this.y - yStep);
			return bullet;
		}
		//原代码
		/*if(doubleFire > 0) {
		//定义子弹匣
		Bullet[] bullet = new Bullet[2];
		//1/4处
		bullet[0] = new Bullet(this.x + 1 * xStep, this.y - yStep);
		//3/4处
		bullet[1] = new Bullet(this.x + 3 * xStep, this.y - yStep);
		//当每发射一次，火力值减2
		doubleFire -= 2;
		return bullet;
	}else {
		Bullet[] bullet = new Bullet[1];
		bullet[0] = new Bullet(this.x + 2 * xStep, this.y - yStep);
		return bullet;
	}*/
	}

	@Override
	public boolean outOfBounds() {
		return false;
	}
	
	/*增加生命值*/
	public void addLife() {
		life ++;
	}
	
	/*减少生命值*/
	public void subtractLife() {
		life --;
	}
	
	/*获取生命值*/
	public int getLife() {
		return life;
	}
	
	/*增加火力*/
	public void addDoubleFire() {
		doubleFire += 40;
	}
	
	/*火力清零*/
	public void clearDoubleFire() {
		doubleFire = 0;
	}
}
