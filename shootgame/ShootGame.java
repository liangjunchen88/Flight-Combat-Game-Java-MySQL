package com.tedu.shootgame.day03_shootgame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jdbc.jdbcUtils.*;

public class ShootGame extends JPanel{
	
	public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;//��ȡ��Ļ���
	public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;//��ȡ��Ļ�߶�
	public static final int WIDTH = 400;//������
	public static final int HEIGHT = 700;//����߶�
	public static int jframeX = width / 2 - WIDTH / 2;//ˮƽ����
	public static int jframeY = height / 2 - HEIGHT / 2;//��ֱ����
	
	/*
	 * ������Ϸ��״̬
	 * ���� ���� ��ͣ ����
	 */
	public static final int START = 0;//����
	public static final int RUNNING = 1;//����
	public static final int PAUSE = 2;//��ͣ
	public static final int GAME_OVER = 3;//����
	
	/*��Ϸ��ǰ״̬*/
	public int state = START;
	
	/*����ͼƬ*/
	public static BufferedImage start;//����ͼ
	public static BufferedImage pause;//��ͣͼ
	public static BufferedImage gameover;//����ͼ
	
	/*��̬�����---���ȼ�������ͼƬ*/
	static {
		start = FlyingObject.loadImage("start.png");
		pause = FlyingObject.loadImage("pause.png");
		gameover = FlyingObject.loadImage("gameover.png");
	}
	
	/*����ʵ����*/
	private Sky sky = new Sky();//��ն���
	private Hero hero = new Hero();//Ӣ�ۻ�����
	private FlyingObject[] enemies = {};//��������
	private Bullet[] bullets = {};//�ӵ�����
	private EnemyBullet[] enemyBullets = {};//�����ӵ�����
	
	/*���ɵ��˵ĺ����㷨*/
	public FlyingObject nextOne() {
		/*ʵ���������*/
		Random rand = new Random();
		int type = rand.nextInt(20);//0����19�����
		if(type < 4) {//0-3�����۷�
			return new Bee();
		}else if(type < 12) {
			return new Airplane();
		}else {
			return new BigAirplane();
		}
	}
	
	/*�����볡*/
	int enterIndex = 0;//�����볡������
	public void enterAction() {
		enterIndex ++;//ÿ10ms��1 �Ͷ�ʱ��ʱ���й�
		if(enterIndex % 40 == 0) {//400ms
			//��ȡ���˶���
			FlyingObject obj = nextOne();
			//��Դ��������
			enemies = Arrays.copyOf(enemies, enemies.length + 1);
			//�������ķ����������������һλ
			enemies[enemies.length - 1] = obj;
		}
	}
	
	/*�ӵ��볡*/
	int shootIndex = 0;
	public void shootAction() {
		shootIndex ++;
		if(shootIndex % 30 == 0) {
			Bullet[] bs = hero.shoot();//Ӣ�ۻ������ӵ�
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);//����
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);//ע�����
		}
	}
	
	/*�����ӵ��볡*/
	int enemyShootIndex = 0;
	public void enemyShootAction() {
		enemyShootIndex ++;
		if(enemyShootIndex % 90 == 0) {
			for (int i = 0; i < enemies.length; i++) {
				if(enemies[i] instanceof BigAirplane) {
					BigAirplane ba = (BigAirplane)enemies[i];
					if(ba.isLife()) {
						EnemyBullet[] bs = ba.enemyShoot();//��л������ӵ�
						enemyBullets = Arrays.copyOf(enemyBullets, enemyBullets.length + bs.length);//����
						System.arraycopy(bs, 0, enemyBullets, enemyBullets.length - bs.length, bs.length);//ע�����
					}
					
				}
			}
		}
	}
	
	/*�������ƶ�*/
	public void stepAction() {
		sky.step();
		hero.step();
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].step();//�����ƶ�
		}
		for(int i = 0; i < bullets.length; i++) {
			bullets[i].step();//�ӵ��ƶ�
		}
		for(int i = 0; i < enemyBullets.length; i++) {
			enemyBullets[i].step();//�����ӵ��ƶ�
		}
	}
	
	/*ɾ��Խ��ķ�����*/
	public void outOfBoundsAction() {
		//����û��Խ��ĵ��������±�͸���
		int index = 0;
		FlyingObject[] enemyLives = new FlyingObject[enemies.length];
		//����ÿһ�����˲��ж��Ƿ�Խ��
		for (int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			if( ! f.outOfBounds()) {
				enemyLives[index] = f;//��ûԽ��ĵ��˴洢
				index ++;
			}else {
				if(f.isLife()) {
					if(f instanceof BigAirplane || f instanceof Airplane) {
						hero.subtractLife();
					}
				}
			}
		}
		//����Խ��������¸�����Դ������
		enemies = Arrays.copyOf(enemyLives, index);
		
		//��ʼ���±꣬���������ӵ�������
		index = 0;
		//���岻Խ���ӵ�������
		Bullet[] bulletLives = new Bullet[bullets.length];
		//�洢���жϲ�Խ����ӵ�
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if( ! b.outOfBounds()) {
				//�洢
				bulletLives[index] = b;
				index ++;
			}
		}
		//����Խ���ӵ����¸�����Դ������
		bullets = Arrays.copyOf(bulletLives, index);
		
		//��ʼ���±꣬�������������ӵ�������
		index = 0;
		//���岻Խ���ӵ�������
		EnemyBullet[] enemyBulletLives = new EnemyBullet[enemyBullets.length];
		//�洢���жϲ�Խ����ӵ�
		for (int i = 0; i < enemyBullets.length; i++) {
			EnemyBullet b = enemyBullets[i];
			if( ! b.outOfBounds()) {
				//�洢
				enemyBulletLives[index] = b;
				index ++;
			}
		}
		//����Խ���ӵ����¸�����Դ������
		enemyBullets = Arrays.copyOf(enemyBulletLives, index);
	}
	
	public int score = 0;//��ҵ÷�
	/*�������ӵ���ײ*/
	public void bulletBangAction() {
		for (int i = 0; i < bullets.length; i++) {//��ȡÿ���ӵ�
			Bullet b = bullets[i];
			for (int j = 0; j < enemies.length; j++) {
				//��ȡÿ������
				FlyingObject f = enemies[j];
				//�ж��Ƿ���ײ
				if(f.hit(b) && f.isLife() && b.isLife()) {
					f.goDead();//�������
					b.goDead();//�ӵ���ʧ
					/*��ײ��Ĵ����߼�*/
					//�ж��Ƿ�Ϊ��С�л�---��̬�ӿ�����
					if(f instanceof Enemy) {
						//������ת��Ϊ��С�л��Ľӿ�����
						Enemy en = (Enemy)f;
						score += en.getScore();
					}
					//�ж��佱������ΪAward
					if(f instanceof Award) {
						//������ת��ΪAward�ӿ�����
						Award aw = (Award)f;
						int type = aw.getAwardType();
						switch (type) {
						case Award.DOUBLE_FIRE:
							hero.addDoubleFire();//Ӣ�ۻ����˫������
							break;
						case Award.LIFT:
							hero.addLife();//Ӣ�ۻ���������ֵ
							break;
						}
					}
				}
			}
		}
	}
	
	/*Ӣ�ۻ��������ײ*/
	public void heroBangAction() {
		//�������е���
		for(int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			//�ж��Ƿ���ײ
			if(f.hit(hero) && f.isLife() && hero.isLife()) {
				f.goDead();//�������
				hero.subtractLife();//Ӣ�ۻ���Ѫ
				hero.clearDoubleFire();//���˫������
			}
		}
	}
	
	//�Ķ�4 ��ײ����---
	public void enemyHit_LCR() {
		for(int i=0;i<enemyBullets.length;i++) {
			EnemyBullet b=enemyBullets[i];
			if(b.hit(hero)&&hero.isLife()&&b.isLife()) {
				b.goDead();
				hero.subtractLife();
				hero.clearDoubleFire();
			}
		}
	}
	
	/*
	//��ʦ�汾���ݿ�
	UserDao udi = new UserDaoImp();*/
	//�Լ�V2�汾
	public String username;
	public CRUD3 c3 = new CRUD3();
	/*�ж���Ϸ�Ƿ����*/
	public void checkGameOverAction() {
		if(hero.getLife() == 0) {
			state = GAME_OVER;
			/* V1��ȡ�û���
			//��ȡ�û���
			System.out.println("�������û�����");
			Scanner scan = new Scanner(System.in);
			String username = scan.next();
			//�������������ݿ�
			CRUD3 c3 = new CRUD3();
			c3.insertScoreV2(username,score);*/
			
			/*V1�汾
			//�������������ݿ�
			CRUD3 c3 = new CRUD3();
			c3.insertScore(score);
			//������ݿ�
			c3.selectScore();*/
			
			//�Լ�V2�汾
			//�������������ݿ�
			c3.insertScoreV2(username,score);
			//������ݿ�
			c3.selectScore();
			
			/*
			//��ʦ�汾���ݿ�
			udi.save("Jazz123", score);*/
		}
	}
	
	/*��������ִ��*/
	public void action() {
		//��������������
		MouseAdapter l = new MouseAdapter() {//�����ڲ���
			/*��д����ƶ��¼�*/
			public void mouseMoved(MouseEvent e) {
				//�жϵ�ǰ״̬�Ƿ�Ϊ����״̬
				if(state == RUNNING){
					int x = e.getX();//��ȡ���X����
					int y = e.getY();//��ȡ���Y����
					hero.moveTo(x, y);
				}
			}
			
			/*��д������¼�*/
			public void mouseClicked(MouseEvent e) {
				//���ݲ�ͬ��״̬������ͬ�Ĵ���
				switch (state) {
				//�ж��������ʼ״̬�����������������״̬
				case START:
					//state = RUNNING;
					
					
					//�Լ�V2�汾
					//���ݿ⣬��ȡ�û���
					System.out.println("�������û�����");
					Scanner scan = new Scanner(System.in);
					username = scan.next();
					state = PAUSE;
					break;
				//�ж�����ڽ���״̬�������꣬��д��ʼ
				case GAME_OVER:
					state = START;
					score = 0;//��������
					sky = new Sky();
					hero = new Hero();
					enemies = new FlyingObject[0];
					bullets = new Bullet[0];
					enemyBullets = new EnemyBullet[0];
					break;
				}
			}
			
			/*��д����Ƴ��¼�*/
			@Override
			public void mouseExited(MouseEvent e) {
				if(state == RUNNING) {
					state = PAUSE;//���� --> ��ͣ
				}
			}
			
			/*��д��������¼�*/
			@Override
			public void mouseEntered(MouseEvent e) {
				if(state == PAUSE) {
					state = RUNNING;//��ͣ --> ����
				}
			}
		};
		
		//����������¼�
		this.addMouseListener(l);
		//�������ƶ��¼�
		this.addMouseMotionListener(l);
		
		//���嶨ʱ������
		Timer timer = new Timer();
		int intervel = 10;//10ms
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(state == RUNNING) {
					enterAction();//�����볡
					shootAction();//�ӵ��볡
					stepAction();//�������볡
					outOfBoundsAction();//ɾ��Խ�������
					bulletBangAction();//�ӵ��������ײ
					heroBangAction();//Ӣ�ۻ��������ײ
					checkGameOverAction();//������Ϸ�Ƿ����
					
					enemyHit_LCR();
					enemyShootAction();// �Ķ�2
					
				}
				repaint();//�ػ�
			}
		}, intervel, intervel);//��ʱ�ƻ�
	}
	
	/*��дpaint����*/
	@Override
	public void paint(Graphics g) {
		sky.paintObject(g);//�����
		hero.paintObject(g);//��Ӣ�ۻ�
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].paintObject(g);//�����ӵ�
		}
		for (int i = 0; i < enemyBullets.length; i++) {
			enemyBullets[i].paintObject(g);//���Ƶ����ӵ�
		}
		for (int i = 0; i < enemies.length; i++) {
			enemies[i].paintObject(g);//���Ƶ���
		}
		/*���Ƶ÷ְ�*/
		g.drawString("SCORE" + score, 10, 25);
		/*��������ֵ*/
		g.drawString("LIFE" + hero.getLife(), 10, 45);
		/*��ͬ״̬�»���ͬ��ͼ*/
		switch (state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			
			/*
			//��ʦ�汾���ݿ�
			g.setFont(new Font("����",Font.PLAIN,20));
			g.drawString("���а�", 170, 320);
			String results[] = udi.getScoreAndName();
			int y =360;//���а���һ���ݵĵ�һ��λ������
			for (int i = 0; i < 10; i++) {
				if(results != null) {
					g.setFont(new Font("",Font.PLAIN,15));
					g.drawString(results[i], 130, y);
					y += 30;
				}
			}*/
			
			//�Լ�V2�汾
			g.setFont(new Font("����",Font.PLAIN,20));
			g.drawString("���а�", 170, 320);
			String results[] = c3.selectScoreV2();
			int y =360;//���а���һ���ݵĵ�һ��λ������
			for (int i = 0; i < 10; i++) {
				if(results != null) {
					g.setFont(new Font("",Font.PLAIN,15));
					g.drawString(results[i], 130, y);
					y += 30;
				}
			}
			break;
		}
	}
	
	//����������
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setTitle("shootgame");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds(jframeX, jframeY, WIDTH, HEIGHT);
		//ʵ������ǰ�����
		ShootGame sg = new ShootGame();
		jf.add(sg);//����������ڴ���������
		jf.setVisible(true);
		sg.action();
		new MusicPlayer().start();//���ű�������
	}
}
/*
 * ע���ַ��ֵ������
 * Person p1 = new Person();
 * Person p2 = new Person();
 * p1��p2����
 * Person p3=p2;
 * 
 * boolean(p1 == p2) = false
 * boolean(p2 == p3) = true
 * ��ַ����ֵ���ɸ�д����
 */