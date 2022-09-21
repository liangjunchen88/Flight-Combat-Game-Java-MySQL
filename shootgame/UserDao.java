package com.tedu.shootgame.day03_shootgame;
//MVC三层模式的Dao层，业务层，写对方法的定义
public interface UserDao {
	
	public void save(String username, int score);
	public String[] getScoreAndName();
}
