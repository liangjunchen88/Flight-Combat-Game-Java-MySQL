package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;
//С�л�---������÷�

public class Airplane extends FlyingObject implements Enemy{
	
	/*����ͼƬ��*/
	private static BufferedImage[] images;
	
	/*��̬�����---���ȼ�������ͼƬ*/
	static {
		images = new BufferedImage[5];//��ʼ��ͼƬ���飬����Ϊ5
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("airplane" + i + ".png");
		}
	}
	
	/*�����л���������*/
	private int speed;
	
	/*���췽��*/
	public Airplane() {
		super(49,36);
		speed = 3;
	}

	@Override
	public int getScore() {
		return 1;//�÷�
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
