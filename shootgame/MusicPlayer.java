package com.tedu.shootgame.day03_shootgame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread{
	@Override
	public void run() {
		//创建文件输入流
		try {
			FileInputStream fis = new FileInputStream("src\\com\\tedu\\shootgame\\day03_shootgame\\Music\\07.人生ベリーハードモード.mp3");
			//创建文件缓冲流
			BufferedInputStream bis = new BufferedInputStream(fis);
			//创建播放对象
			Player shootPlayer = new Player(bis);
			shootPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
