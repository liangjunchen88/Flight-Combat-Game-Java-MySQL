package com.tedu.shootgame.day03_shootgame;
//�����ӵ���---������

import java.awt.image.BufferedImage;

public class EnemyBullet extends FlyingObject{
	/*����ͼƬ*/
	public static BufferedImage image;
	
	/*��̬�����---����ͼƬ*/
	static {
		image = loadImage("waterdrop.png");
	}
	
	/*����y��������*/
	private int speed;
	
	/*���췽��*/
	public EnemyBullet(int x, int y) {
		super(17,25,x,y);
		speed = 3;
	}

	@Override
	public void step() {
		y += speed;//����
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
		//�Ķ�1
		return this.y >= 700-this.height;
	}
}
