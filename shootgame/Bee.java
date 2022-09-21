package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;
import java.util.Random;

//С�۷���---���������

public class Bee extends FlyingObject implements Award{
	
	/*����ͼƬ��*/
	private static BufferedImage[] images;
	
	/*��̬�����---����ͼƬ*/
	static {
		images = new BufferedImage[5];
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("bee" + i + ".png");
		}
	}
	
	private int xSpeed;
	private int ySpeed;
	private int awardType;//��������
	
	/*���췽��*/
	public Bee() {
		super(60,50);
		xSpeed = 1;
		ySpeed = 2;
		Random rand = new Random();
		awardType = rand.nextInt(2);//0��1
	}

	@Override
	public int getAwardType() {
		return awardType;
	}

	@Override
	public void step() {
		x += xSpeed;
		y += ySpeed;
		//�ж��۷��������ұ߽�
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
