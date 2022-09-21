package jdbc.jdbcUils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 写一个控制台登录程序
 * 1.提示用户输入用户名及密码
 * 2.获取用户输入的数据
 * 3.jdbc数据库连接技术访问数据库
 * 4.查询数据并验证登录
 * 5.提示登录成功或失败
 * 提示
 * 采用面向对象编程思想
 * 1.创建login方法实现获取数据
 * 2.创建check方法实现登录验证
 * 3.main方法测试程序
 * @author Andrew
 *
 */
public class Login {
	
	//定义连接对象
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void login() {
		System.out.println("请输入用户名：");
		Scanner scan = new Scanner(System.in);
		String username = scan.next();
		System.out.println("请输入密码：");
		String password = scan.next();
		boolean res = check(username, password);
		if(res) {
			System.out.println("登录成功");
		}else {
			System.out.println("登录失败");
		}
	}
	
	//验证是否登录成功
	public boolean check(String username, String password) {
		try {
			//获取连接对象
			conn = JDBCUtils.getConnection();
			String sql = "select username,password from user where userName=? and password=?";
			//获取传递sql的statement对象
			ps = conn.prepareStatement(sql);
			//设置查询参数
			ps.setString(1, username);
			ps.setString(2, password);
			//执行查询，返回结果集
			rs = ps.executeQuery();
			//判断方法1
			if(rs.next()) {//判断是否有下一个元素
				return true;
			}else {
				return false;
			}
			//判断方法2 -- 有错误
			/*while(rs.next()) {
				if(username == rs.getString("username") && password == rs.getString("password")) {
					return true;
				}else {
					return false;
				}
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		new Login().login();
	}
}
