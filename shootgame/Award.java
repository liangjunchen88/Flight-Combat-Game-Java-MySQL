package com.tedu.shootgame.day03_shootgame;
/**
 * �����ӿ�
 * 1.��ȡ˫������
 * 2.��ȡ����ֵ
 * @author Andre
 *
 */
public interface Award {
	/*
	 * �����߼�
	 * ���影��ֵΪ0��1
	 * ��Ϊ0����Ϊ˫��������
	 * ��Ϊ1����Ϊ��ȡ����ֵ��
	 */
	public int DOUBLE_FIRE = 0;//����ֵ
	public int LIFT = 1;//����ֵ
	
	/*��ȡ��������*/
	public int getAwardType();
}
