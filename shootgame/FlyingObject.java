package com.tedu.shootgame.day03_shootgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * 飞行物的总类---抽象类
 * 1.包含了飞行物的所有公共属性、构造方法、方法等
 * 2.使用实体类去继承这个抽象类，实现抽象类定义的方法
 * @author Andrew
 *
 */
public abstract class FlyingObject {
	/*定义飞行物的基本属性*/
	//飞行物的状态
	public static final int LIFE = 0;//活着
	public static final int DEAD = 1;//GG
	public static final int REMOVE = 2;//删除
	public int state = LIFE;//当前状态
	
	//飞行的宽、高、x/y坐标
	protected int width;//宽
	protected int height;//高
	protected int x;
	protected int y;
	
	/*构造方法*/
	/*
	 * 两种构造方法
	 * 1.专门为英雄机、天空、子弹提供的
	 * 2.专门为大、小敌机、蜜蜂提供的
	 */
	//1.英雄机
	public FlyingObject(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	//2.敌机
	public FlyingObject(int width, int height) {
		this.width = width;
		this.height = height;
		//设置飞行物x/y初始坐标范围
		Random rand = new Random();//实例化随机数对象
		x = rand.nextInt(ShootGame.WIDTH - this.width);
		y = - this.height;
	}
	
	/*读取图片*/
	public static BufferedImage loadImage(String filename) {
		try {
			//读取图片
			//BufferedImage img = ImageIO.read(FlyingObject.class.getResource(filename));
			BufferedImage img2 = ImageIO.read(FlyingObject.class.getResource("Picture\\"+filename));
			return img2;
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException();//抛出异常
		}
	}
	
	/*飞行物的移动--抽象方法没有方法体--实现在子类*/
	public abstract void step();
	
	/*获取图片*/
	public abstract BufferedImage getImage();
	
	/*判断是否活着*/
	public boolean isLife() {
		return state == LIFE;
	}
	
	/*判断是否死了*/
	public boolean isDead() {
		return state == DEAD;
	}
	
	/*判断是否删除*/
	public boolean isRemove() {
		return state == REMOVE;
	}
	
	/*绘制当前对象：Graphics*/
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
	
	/*检查飞行物是否越界*/
	public abstract boolean outOfBounds();
	
	/*碰撞算法---this:敌人 other:子弹或者英雄机*/
	public boolean hit(FlyingObject other) {
		//计算最大和最小边界
		int x1 = this.x - other.width;
		int x2 = this.x + this.width;
		int y1 = this.y - other.width;
		int y2 = this.y + this.height;
		//获取英雄机或子弹的x/y坐标
		int x = other.x;
		int y = other.y;
		//返回判断结果：只要英雄机或子弹在限定范围内，即true
		return x >= x1 && x <= x2 && y >= y1 && y <=y2;
	}
	
	/*消灭飞行物*/
	public void goDead() {
		state = DEAD;//死了，但没有完全死。（DEAD，but not REMOVE)
	}
	
	
	
}
