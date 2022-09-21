package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;
//��л�---������÷�

public class BigAirplane extends FlyingObject implements Enemy{
	
	/*����ͼƬ��*/
	private static BufferedImage[] images;
	
	/*��̬�����---���ȼ�������ͼƬ*/
	static {
		images = new BufferedImage[5];//��ʼ��ͼƬ���飬����Ϊ5
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("bigplane" + i + ".png");
		}
	}
	
	/*��л������ӵ�*/
	int xStep = 35;
	int yStep = 20;//�̶��ӵ����ͷ�ľ���Ϊ20
	public EnemyBullet[] enemyShoot() {
		EnemyBullet[] bullet = new EnemyBullet[1];
		
		bullet[0] = new EnemyBullet(this.x + xStep, this.y + yStep+99);//�Ķ�3
		
		return bullet;
	}
	
	/*�����л���������*/
	private int speed;
	
	/*���췽��*/
	public BigAirplane() {
		super(69,99);
		speed = 2;
	}

	@Override
	public int getScore() {
		return 3;//�÷�
	}

	@Override
	public void step() {
		y += speed;//����
		
	}
	
	//�������˵��±�
	int deadIndex = 1;

	@Override
	public BufferedImage getImage() {
		if(isLife()) {//��״̬Ϊ���ţ��򷵻ص�һ��ͼ
			return images[0];
		}else if(isDead()) {//�����ײ��ӵڶ���ͼƬ��ʼ�����л�---��ըЧ��
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
		return this.y >= ShootGame.HEIGHT;//�ж��Ƿ�ﵽ����ײ�
	}
}
