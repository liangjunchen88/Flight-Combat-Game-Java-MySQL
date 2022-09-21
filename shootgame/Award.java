package com.tedu.shootgame.day03_shootgame;
/**
 * 奖励接口
 * 1.获取双倍火力
 * 2.获取生命值
 * @author Andre
 *
 */
public interface Award {
	/*
	 * 奖励逻辑
	 * 定义奖励值为0或1
	 * 若为0，则为双倍火力。
	 * 若为1，则为获取生命值。
	 */
	public int DOUBLE_FIRE = 0;//火力值
	public int LIFT = 1;//生命值
	
	/*获取奖励类型*/
	public int getAwardType();
}
