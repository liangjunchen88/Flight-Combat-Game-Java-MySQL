package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;


/**
 * JDBC
 * 	Java Database Connectivity
 * java.sql和javax.sql包支持
 * 驱动mysql-connector-java-5.1.40-bin.jar//mysql驱动jar包，可直接下载
 * @author Andrew
 *
 */

public class JDBCTest {
	
	@Test //单元测试--可以直接运行该方法
	public void JDBCFirstDemo() throws Exception{
		//要连接的数据库URL地址
		String url = "jdbc:mysql://localhost:3306/xjtu";
		//连接用户名
		String username = "root";
		//连接密码
		String password = "root";
		
		//1.加载驱动
		Class.forName("com.mysql.cj.jdbc.Driver");    
	    //2.获取数据库连接
	    //mysql8.0得加上时区时间(?serverTimezone=UTC) 似乎不加也可以
	    //本地数据库可以省略IP地址和端口号
	    Connection conn = DriverManager.getConnection(url,username, password);
		if(conn == null) {
			System.out.println("连接失败");
		}else {
			System.out.println("连接成功");
		}
		//3.获取用于向数据库发送sql的statement对象
		Statement st = conn.createStatement();
		//4.书写sql语句
		
		 String sql = "select * from user";
		//命令1 String sql = "select * from user";
		//命令2 String sql = "SELECT NAME,stuScore,pwd,gradeName FROM student s INNER JOIN grade g ON s.`gradeId` = g.`gradeID` INNER JOIN score c ON s.`scoreId` = c.`scoreId`ORDER BY stuScore DESC";
		//王文龙记录81
		
		//5.向数据库发送sql，并获取结果对象集
		ResultSet rs = st.executeQuery(sql);
		//6.遍历数据库
		while(rs.next()) {
			System.out.println("userId：" + rs.getInt("userId"));
			System.out.println("userName：" + rs.getString("userName"));
			System.out.println("password：" + rs.getString("password"));
			/* 命令1
			System.out.println("userId：" + rs.getInt("userId"));
			System.out.println("userName：" + rs.getString("userName"));
			System.out.println("password：" + rs.getString("password"));*/
			/* 命令2
			System.out.println("name：" + rs.getString("name"));
			System.out.println("stuScore：" + rs.getInt("stuScore"));
			System.out.println("password：" + rs.getString("pwd"));
			System.out.println("gradeName：" + rs.getString("gradeName"));*/
			
		}
		//7.关闭连接 释放资源
		rs.close();
		st.close();
		conn.close();
	}
/*	在主函数中查看，可以直接查看错误报告
 * public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/xjtu?serverTimezone=UTC";
		//连接用户名
		String username = "root";
		//连接密码
		String password = "root";
		
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");    
			    //获取数据库连接
			    //mysql8.0得加上时区时间(?serverTimezone=UTC)
			    //本地数据库可以省略IP地址和端口号
			    Connection conn = DriverManager.getConnection(url,username, password);
			if(conn == null) {
				System.out.println("连接失败");
			}else {
				System.out.println("连接成功");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}


