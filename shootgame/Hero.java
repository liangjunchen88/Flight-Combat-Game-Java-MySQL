package com.tedu.shootgame.day03_shootgame;

import java.awt.image.BufferedImage;

//Ӣ�ۻ�---������

public class Hero extends FlyingObject{
	
	/*����ͼƬ��*/
	private static BufferedImage[] images;
	
	/*��̬�����---���ȼ�������ͼƬ*/
	static {
		images = new BufferedImage[2];//��ʼ��ͼƬ���飬����Ϊ2
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("hero" + i + ".png");
		}
	}
	
	/*����˫������������ֵ*/
	private int life;
	private int doubleFire;//������������С�շ�������

	/*���췽��*/
	public Hero() {
		super(97,124, 150, 400);
		life = 3;
		doubleFire = 0;//����Ϊ����
	}
	
	/*������ƶ���Ӣ�ۻ��м�*/
	public void moveTo(int x, int y) {
		this.x = x - this.width / 2;
		this.y = y - this.height / 2;
	}

	@Override
	public void step() {
		// ����Ҫд��Ӣ�ۻ�������˶�
	}
	
	int index = 0;//�����±�����
	
	@Override
	public BufferedImage getImage() {
		if(isLife()) {
			return images[index ++ %2]; //β������Ч��
		}
		return null;
	}
	
	/*Ӣ�ۻ������ӵ��㷨*/
	public Bullet[] shoot() {
		//��ȡ1/4��Ӣ�ۻ���
		int xStep = this.width / 4;
		int yStep = 20;//�̶��ӵ����ͷ�ľ���Ϊ20
		//�ж��Ƿ�Ϊ˫������
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
				//�����ӵ�ϻ
				Bullet[] bullet = new Bullet[2];
				//1/4��
				bullet[0] = new Bullet(this.x + 1 * xStep, this.y - yStep);
				//3/4��
				bullet[1] = new Bullet(this.x + 3 * xStep, this.y - yStep);
				//��ÿ����һ�Σ�����ֵ��2
				doubleFire -= 2;
				return bullet;
			}
		}else {
			Bullet[] bullet = new Bullet[1];
			bullet[0] = new Bullet(this.x + 2 * xStep, this.y - yStep);
			return bullet;
		}
		//ԭ����
		/*if(doubleFire > 0) {
		//�����ӵ�ϻ
		Bullet[] bullet = new Bullet[2];
		//1/4��
		bullet[0] = new Bullet(this.x + 1 * xStep, this.y - yStep);
		//3/4��
		bullet[1] = new Bullet(this.x + 3 * xStep, this.y - yStep);
		//��ÿ����һ�Σ�����ֵ��2
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
	
	/*��������ֵ*/
	public void addLife() {
		life ++;
	}
	
	/*��������ֵ*/
	public void subtractLife() {
		life --;
	}
	
	/*��ȡ����ֵ*/
	public int getLife() {
		return life;
	}
	
	/*���ӻ���*/
	public void addDoubleFire() {
		doubleFire += 40;
	}
	
	/*��������*/
	public void clearDoubleFire() {
		doubleFire = 0;
	}
}
