package com.tedu.shootgame.day03_shootgame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread{
	@Override
	public void run() {
		//�����ļ�������
		try {
			FileInputStream fis = new FileInputStream("src\\com\\tedu\\shootgame\\day03_shootgame\\Music\\07.�����٥�`�ϩ`�ɥ�`��.mp3");
			//�����ļ�������
			BufferedInputStream bis = new BufferedInputStream(fis);
			//�������Ŷ���
			Player shootPlayer = new Player(bis);
			shootPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
