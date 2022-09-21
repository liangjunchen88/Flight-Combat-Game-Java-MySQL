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
	
	public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;//获取屏幕宽度
	public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;//获取屏幕高度
	public static final int WIDTH = 400;//窗体宽度
	public static final int HEIGHT = 700;//窗体高度
	public static int jframeX = width / 2 - WIDTH / 2;//水平居中
	public static int jframeY = height / 2 - HEIGHT / 2;//垂直居中
	
	/*
	 * 定义游戏的状态
	 * 启动 运行 暂停 结束
	 */
	public static final int START = 0;//启动
	public static final int RUNNING = 1;//运行
	public static final int PAUSE = 2;//暂停
	public static final int GAME_OVER = 3;//结束
	
	/*游戏当前状态*/
	public int state = START;
	
	/*定义图片*/
	public static BufferedImage start;//启动图
	public static BufferedImage pause;//暂停图
	public static BufferedImage gameover;//结束图
	
	/*静态代码块---优先加载所有图片*/
	static {
		start = FlyingObject.loadImage("start.png");
		pause = FlyingObject.loadImage("pause.png");
		gameover = FlyingObject.loadImage("gameover.png");
	}
	
	/*定义实体类*/
	private Sky sky = new Sky();//天空对象
	private Hero hero = new Hero();//英雄机对象
	private FlyingObject[] enemies = {};//敌人数组
	private Bullet[] bullets = {};//子弹数组
	private EnemyBullet[] enemyBullets = {};//敌人子弹数组
	
	/*生成敌人的核心算法*/
	public FlyingObject nextOne() {
		/*实例化随机数*/
		Random rand = new Random();
		int type = rand.nextInt(20);//0——19随机数
		if(type < 4) {//0-3产生蜜蜂
			return new Bee();
		}else if(type < 12) {
			return new Airplane();
		}else {
			return new BigAirplane();
		}
	}
	
	/*敌人入场*/
	int enterIndex = 0;//敌人入场计数器
	public void enterAction() {
		enterIndex ++;//每10ms加1 和定时器时间有关
		if(enterIndex % 40 == 0) {//400ms
			//获取敌人对象
			FlyingObject obj = nextOne();
			//对源数组扩容
			enemies = Arrays.copyOf(enemies, enemies.length + 1);
			//将产生的飞行物放在数组的最后一位
			enemies[enemies.length - 1] = obj;
		}
	}
	
	/*子弹入场*/
	int shootIndex = 0;
	public void shootAction() {
		shootIndex ++;
		if(shootIndex % 30 == 0) {
			Bullet[] bs = hero.shoot();//英雄机发射子弹
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);//扩容
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);//注意理解
		}
	}
	
	/*敌人子弹入场*/
	int enemyShootIndex = 0;
	public void enemyShootAction() {
		enemyShootIndex ++;
		if(enemyShootIndex % 90 == 0) {
			for (int i = 0; i < enemies.length; i++) {
				if(enemies[i] instanceof BigAirplane) {
					BigAirplane ba = (BigAirplane)enemies[i];
					if(ba.isLife()) {
						EnemyBullet[] bs = ba.enemyShoot();//大敌机发射子弹
						enemyBullets = Arrays.copyOf(enemyBullets, enemyBullets.length + bs.length);//扩容
						System.arraycopy(bs, 0, enemyBullets, enemyBullets.length - bs.length, bs.length);//注意理解
					}
					
				}
			}
		}
	}
	
	/*飞行物移动*/
	public void stepAction() {
		sky.step();
		hero.step();
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].step();//敌人移动
		}
		for(int i = 0; i < bullets.length; i++) {
			bullets[i].step();//子弹移动
		}
		for(int i = 0; i < enemyBullets.length; i++) {
			enemyBullets[i].step();//敌人子弹移动
		}
	}
	
	/*删除越界的飞行物*/
	public void outOfBoundsAction() {
		//定义没有越界的敌人数组下标和个数
		int index = 0;
		FlyingObject[] enemyLives = new FlyingObject[enemies.length];
		//遍历每一个敌人并判断是否越界
		for (int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			if( ! f.outOfBounds()) {
				enemyLives[index] = f;//将没越界的敌人存储
				index ++;
			}else {
				if(f.isLife()) {
					if(f instanceof BigAirplane || f instanceof Airplane) {
						hero.subtractLife();
					}
				}
			}
		}
		//将不越界敌人重新复制在源数组中
		enemies = Arrays.copyOf(enemyLives, index);
		
		//初始化下标，用来描述子弹的索引
		index = 0;
		//定义不越界子弹的数组
		Bullet[] bulletLives = new Bullet[bullets.length];
		//存储并判断不越界的子弹
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if( ! b.outOfBounds()) {
				//存储
				bulletLives[index] = b;
				index ++;
			}
		}
		//将不越界子弹重新复制在源数组中
		bullets = Arrays.copyOf(bulletLives, index);
		
		//初始化下标，用来描述敌人子弹的索引
		index = 0;
		//定义不越界子弹的数组
		EnemyBullet[] enemyBulletLives = new EnemyBullet[enemyBullets.length];
		//存储并判断不越界的子弹
		for (int i = 0; i < enemyBullets.length; i++) {
			EnemyBullet b = enemyBullets[i];
			if( ! b.outOfBounds()) {
				//存储
				enemyBulletLives[index] = b;
				index ++;
			}
		}
		//将不越界子弹重新复制在源数组中
		enemyBullets = Arrays.copyOf(enemyBulletLives, index);
	}
	
	public int score = 0;//玩家得分
	/*敌人与子弹碰撞*/
	public void bulletBangAction() {
		for (int i = 0; i < bullets.length; i++) {//获取每个子弹
			Bullet b = bullets[i];
			for (int j = 0; j < enemies.length; j++) {
				//获取每个敌人
				FlyingObject f = enemies[j];
				//判断是否碰撞
				if(f.hit(b) && f.isLife() && b.isLife()) {
					f.goDead();//消灭敌人
					b.goDead();//子弹消失
					/*碰撞后的处理逻辑*/
					//判断是否为大小敌机---多态接口类型
					if(f instanceof Enemy) {
						//将敌人转化为大小敌机的接口类型
						Enemy en = (Enemy)f;
						score += en.getScore();
					}
					//判断其奖励类型为Award
					if(f instanceof Award) {
						//将敌人转换为Award接口类型
						Award aw = (Award)f;
						int type = aw.getAwardType();
						switch (type) {
						case Award.DOUBLE_FIRE:
							hero.addDoubleFire();//英雄机获得双倍火力
							break;
						case Award.LIFT:
							hero.addLife();//英雄机增加生命值
							break;
						}
					}
				}
			}
		}
	}
	
	/*英雄机与敌人碰撞*/
	public void heroBangAction() {
		//遍历所有敌人
		for(int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			//判断是否碰撞
			if(f.hit(hero) && f.isLife() && hero.isLife()) {
				f.goDead();//消灭敌人
				hero.subtractLife();//英雄机掉血
				hero.clearDoubleFire();//清空双倍火力
			}
		}
	}
	
	//改动4 碰撞代码---
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
	//老师版本数据库
	UserDao udi = new UserDaoImp();*/
	//自己V2版本
	public String username;
	public CRUD3 c3 = new CRUD3();
	/*判断游戏是否结束*/
	public void checkGameOverAction() {
		if(hero.getLife() == 0) {
			state = GAME_OVER;
			/* V1获取用户名
			//获取用户名
			System.out.println("请输入用户名：");
			Scanner scan = new Scanner(System.in);
			String username = scan.next();
			//将分数存入数据库
			CRUD3 c3 = new CRUD3();
			c3.insertScoreV2(username,score);*/
			
			/*V1版本
			//将分数存入数据库
			CRUD3 c3 = new CRUD3();
			c3.insertScore(score);
			//输出数据库
			c3.selectScore();*/
			
			//自己V2版本
			//将分数存入数据库
			c3.insertScoreV2(username,score);
			//输出数据库
			c3.selectScore();
			
			/*
			//老师版本数据库
			udi.save("Jazz123", score);*/
		}
	}
	
	/*启动程序执行*/
	public void action() {
		//创建监听器对象
		MouseAdapter l = new MouseAdapter() {//匿名内部类
			/*重写鼠标移动事件*/
			public void mouseMoved(MouseEvent e) {
				//判断当前状态是否为运行状态
				if(state == RUNNING){
					int x = e.getX();//获取鼠标X坐标
					int y = e.getY();//获取鼠标Y坐标
					hero.moveTo(x, y);
				}
			}
			
			/*重写鼠标点击事件*/
			public void mouseClicked(MouseEvent e) {
				//根据不同的状态做出不同的处理
				switch (state) {
				//判断如果在起始状态，当点击鼠标进入运行状态
				case START:
					//state = RUNNING;
					
					
					//自己V2版本
					//数据库，读取用户名
					System.out.println("请输入用户名：");
					Scanner scan = new Scanner(System.in);
					username = scan.next();
					state = PAUSE;
					break;
				//判断如果在结束状态，点击鼠标，重写开始
				case GAME_OVER:
					state = START;
					score = 0;//分数归零
					sky = new Sky();
					hero = new Hero();
					enemies = new FlyingObject[0];
					bullets = new Bullet[0];
					enemyBullets = new EnemyBullet[0];
					break;
				}
			}
			
			/*重写鼠标移出事件*/
			@Override
			public void mouseExited(MouseEvent e) {
				if(state == RUNNING) {
					state = PAUSE;//运行 --> 暂停
				}
			}
			
			/*重写鼠标移入事件*/
			@Override
			public void mouseEntered(MouseEvent e) {
				if(state == PAUSE) {
					state = RUNNING;//暂停 --> 运行
				}
			}
		};
		
		//添加鼠标操作事件
		this.addMouseListener(l);
		//添加鼠标移动事件
		this.addMouseMotionListener(l);
		
		//定义定时器对象
		Timer timer = new Timer();
		int intervel = 10;//10ms
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(state == RUNNING) {
					enterAction();//敌人入场
					shootAction();//子弹入场
					stepAction();//飞行物入场
					outOfBoundsAction();//删除越界飞行物
					bulletBangAction();//子弹与敌人碰撞
					heroBangAction();//英雄机与敌人碰撞
					checkGameOverAction();//检验游戏是否结束
					
					enemyHit_LCR();
					enemyShootAction();// 改动2
					
				}
				repaint();//重绘
			}
		}, intervel, intervel);//定时计划
	}
	
	/*重写paint方法*/
	@Override
	public void paint(Graphics g) {
		sky.paintObject(g);//画天空
		hero.paintObject(g);//画英雄机
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].paintObject(g);//绘制子弹
		}
		for (int i = 0; i < enemyBullets.length; i++) {
			enemyBullets[i].paintObject(g);//绘制敌人子弹
		}
		for (int i = 0; i < enemies.length; i++) {
			enemies[i].paintObject(g);//绘制敌人
		}
		/*绘制得分板*/
		g.drawString("SCORE" + score, 10, 25);
		/*绘制生命值*/
		g.drawString("LIFE" + hero.getLife(), 10, 45);
		/*不同状态下画不同的图*/
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
			//老师版本数据库
			g.setFont(new Font("宋体",Font.PLAIN,20));
			g.drawString("排行榜", 170, 320);
			String results[] = udi.getScoreAndName();
			int y =360;//排行榜下一数据的第一个位置坐标
			for (int i = 0; i < 10; i++) {
				if(results != null) {
					g.setFont(new Font("",Font.PLAIN,15));
					g.drawString(results[i], 130, y);
					y += 30;
				}
			}*/
			
			//自己V2版本
			g.setFont(new Font("宋体",Font.PLAIN,20));
			g.drawString("排行榜", 170, 320);
			String results[] = c3.selectScoreV2();
			int y =360;//排行榜下一数据的第一个位置坐标
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
	
	//主程序启动
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setTitle("shootgame");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds(jframeX, jframeY, WIDTH, HEIGHT);
		//实例化当前类对象
		ShootGame sg = new ShootGame();
		jf.add(sg);//将画板添加在窗体容器中
		jf.setVisible(true);
		sg.action();
		new MusicPlayer().start();//播放背景音乐
	}
}
/*
 * 注意地址和值的区别
 * Person p1 = new Person();
 * Person p2 = new Person();
 * p1和p2不等
 * Person p3=p2;
 * 
 * boolean(p1 == p2) = false
 * boolean(p2 == p3) = true
 * 地址和数值，可复写方法
 */