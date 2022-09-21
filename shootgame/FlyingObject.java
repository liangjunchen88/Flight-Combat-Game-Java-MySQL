package com.tedu.shootgame.day03_shootgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * �����������---������
 * 1.�����˷���������й������ԡ����췽����������
 * 2.ʹ��ʵ����ȥ�̳���������࣬ʵ�ֳ����ඨ��ķ���
 * @author Andrew
 *
 */
public abstract class FlyingObject {
	/*���������Ļ�������*/
	//�������״̬
	public static final int LIFE = 0;//����
	public static final int DEAD = 1;//GG
	public static final int REMOVE = 2;//ɾ��
	public int state = LIFE;//��ǰ״̬
	
	//���еĿ��ߡ�x/y����
	protected int width;//��
	protected int height;//��
	protected int x;
	protected int y;
	
	/*���췽��*/
	/*
	 * ���ֹ��췽��
	 * 1.ר��ΪӢ�ۻ�����ա��ӵ��ṩ��
	 * 2.ר��Ϊ��С�л����۷��ṩ��
	 */
	//1.Ӣ�ۻ�
	public FlyingObject(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	//2.�л�
	public FlyingObject(int width, int height) {
		this.width = width;
		this.height = height;
		//���÷�����x/y��ʼ���귶Χ
		Random rand = new Random();//ʵ�������������
		x = rand.nextInt(ShootGame.WIDTH - this.width);
		y = - this.height;
	}
	
	/*��ȡͼƬ*/
	public static BufferedImage loadImage(String filename) {
		try {
			//��ȡͼƬ
			//BufferedImage img = ImageIO.read(FlyingObject.class.getResource(filename));
			BufferedImage img2 = ImageIO.read(FlyingObject.class.getResource("Picture\\"+filename));
			return img2;
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException();//�׳��쳣
		}
	}
	
	/*��������ƶ�--���󷽷�û�з�����--ʵ��������*/
	public abstract void step();
	
	/*��ȡͼƬ*/
	public abstract BufferedImage getImage();
	
	/*�ж��Ƿ����*/
	public boolean isLife() {
		return state == LIFE;
	}
	
	/*�ж��Ƿ�����*/
	public boolean isDead() {
		return state == DEAD;
	}
	
	/*�ж��Ƿ�ɾ��*/
	public boolean isRemove() {
		return state == REMOVE;
	}
	
	/*���Ƶ�ǰ����Graphics*/
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}
	
	/*���������Ƿ�Խ��*/
	public abstract boolean outOfBounds();
	
	/*��ײ�㷨---this:���� other:�ӵ�����Ӣ�ۻ�*/
	public boolean hit(FlyingObject other) {
		//����������С�߽�
		int x1 = this.x - other.width;
		int x2 = this.x + this.width;
		int y1 = this.y - other.width;
		int y2 = this.y + this.height;
		//��ȡӢ�ۻ����ӵ���x/y����
		int x = other.x;
		int y = other.y;
		//�����жϽ����ֻҪӢ�ۻ����ӵ����޶���Χ�ڣ���true
		return x >= x1 && x <= x2 && y >= y1 && y <=y2;
	}
	
	/*���������*/
	public void goDead() {
		state = DEAD;//���ˣ���û����ȫ������DEAD��but not REMOVE)
	}
	
	
	
}
