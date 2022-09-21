package com.tedu.shootgame.day03_shootgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//�����---������
public class Sky extends FlyingObject{
	
	/*���屳��ͼƬ*/
	private static BufferedImage image;
	
	/*��̬�����---����ͼƬ*/
	static {
		image = loadImage("background.png");
	}
	
	//����ͼƬ��������S
	private int speed;
	//����ڶ���ͼƬ��y����---������һ��ͼ
	private int y1;
	
	/*���췽��*/
	public Sky() {
		super(ShootGame.WIDTH, ShootGame.HEIGHT, 0, 0);
		speed = 1;
		y1 = - this.height;//�ڶ���ͼƬ�ĳ�ʼλ��
	}

	@Override
	public void step() {
		y += speed;
		y1 += speed;//ͬʱ����
		if(y >= ShootGame.HEIGHT) {
			y = - ShootGame.HEIGHT;
		}
		if(y1 >= ShootGame.HEIGHT) {
			y1 = - ShootGame.HEIGHT;
		}
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public boolean outOfBounds() {
		return false;
	}
	
	//��дpaintObject���������أ�
	@Override
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
		g.drawImage(getImage(), x, y1, null);//���Ƶڶ���ͼƬ
	}

}
