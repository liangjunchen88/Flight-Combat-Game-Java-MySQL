package jdbc.jdbcUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

/**
 * Utils 数据库工具类
 * @author Andre
 *
 */
public class JDBCUtils {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	
	static {
		try {
			//读取db.properties文件的数据库连接信息
			InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc\\jdbcUtils\\db.properties");
			//创建properties对象
			//获取配置对象
			Properties prop = new Properties();
			//配置对象加载Input输入流
			prop.load(in);
			//获取数据库连接驱动
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			//加载数据库驱动
			Class.forName(driver);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//获取连接对象
	public static Connection getConnection() throws Exception{
		return DriverManager.getConnection(url,username,password);
	}
	
	//关闭连接 释放资源
	public static void close(Connection conn, Statement st, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testJDBC() throws Exception{
		if(JDBCUtils.getConnection() == null) {
			System.out.println("连接失败");
		}else {
			System.out.println("连接成功!");
		}
	}
}
