package com.tedu.shootgame.day03_shootgame;
//MVC����ģʽ��Dao�㣬ҵ��㣬д�Է����Ķ���
public interface UserDao {
	
	public void save(String username, int score);
	public String[] getScoreAndName();
}
