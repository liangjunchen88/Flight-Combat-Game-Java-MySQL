package jdbc.jdbcUils2;
/**
 * 数据库连接的第二种方式 数据库连接池
 * 	用户每次请求都需要想数据库获取连接，而数据库创建连接通常需要消耗相对较大的资源，
 * 	创建时间长，极易造成数据库服务器内存溢出，极大的浪费了资源。
 * --数据库连接池
 * 	-负责分配、管理、释放数据库连接，允许应用程序重复使用一个现有的数据库连接，而不是新建一个连接。
 * 
 * 	1.DBCP:没有自动回收空闲的连接的功能
 * 	2.c3p0：可以自动回收
 * 
 * @author Andrew
 *
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class JDBCUtils {
	
	private static ComboPooledDataSource ds = null;
	
	static {
		ds = new ComboPooledDataSource();
	}
	
	//获取连接池
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
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
		public void testJDBC() throws SQLException{
			if(getConnection() == null) {
				System.out.println("连接失败");
			}else {
				System.out.println("连接成功!");
			}
		}
}
