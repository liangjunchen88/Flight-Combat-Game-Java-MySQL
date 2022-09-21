package com.tedu.shootgame.day03_shootgame;
//�ӵ���---������

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	
	/*����ͼƬ*/
	public static BufferedImage image;
	
	/*��̬�����---����ͼƬ*/
	static {
		image = loadImage("bullet.png");
	}
	
	/*����y��������*/
	private int speed;
	
	/*���췽��*/
	public Bullet(int x, int y) {
		super(8,14,x,y);
		speed = 3;
	}

	@Override
	public void step() {
		y -= speed;//����
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
