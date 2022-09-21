package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
 * 数据库增删查改操作
 * @author Andrew
 *
 */

public class CRUD {
	//定义连接对象
	Connection conn = null;
	//定义statement对象
	Statement st = null;
	//定义结果集对象
	ResultSet rs = null;
	
	@Test//添加操作
	public void insertUser() {
		try {
			//获取连接对象
			conn = JDBCUtils.getConnection();
			//获取传递sql对象
			st = conn.createStatement();
			//编写sql语句
			String sql = "insert into user(username,password) values('张四','3265'),('赵柳','3265')";
			/*
			 * executeUpdate 专门执行增删改，返回值为int=受影响的行数
			 * executeQuery 专门执行查询，返回值为ResultSet对象
			 */
			//执行sql语句
			int num = st.executeUpdate(sql);
			//判断是否插入成功
			if(num > 0) {
				System.out.println("插入成功");
			}else {
				System.out.println("插入失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭连接
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//删除操作
	public void deltetUser() {
		try {
			//获取连接对象
			conn = JDBCUtils.getConnection();
			//获取传递sql对象
			st = conn.createStatement();
			//编写sql语句
			String sql = "delete from user where userName='张四'";
			/*
			 * executeUpdate 专门执行增删改，返回值为int=受影响的行数
			 * executeQuery 专门执行查询，返回值为ResultSet对象
			 */
			//执行sql语句
			int num = st.executeUpdate(sql);
			//判断是否插入成功
			if(num > 0) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭连接
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//修改操作
	public void updatetUser() {
		try {
			//获取连接对象
			conn = JDBCUtils.getConnection();
			//获取传递sql对象
			st = conn.createStatement();
			//编写sql语句
			String sql = "update user set password = 4321 where userName='赵柳'";
			/*
			 * executeUpdate 专门执行增删改，返回值为int=受影响的行数
			 * executeQuery 专门执行查询，返回值为ResultSet对象
			 */
			//执行sql语句
			int num = st.executeUpdate(sql);
			//判断是否插入成功
			if(num > 0) {
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭连接
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//查询所有操作
	public void selectUser() {
		try {
			//获取连接对象
			conn = JDBCUtils.getConnection();
			//获取传递sql对象
			st = conn.createStatement();
			//编写sql语句
			String sql = "select * from user";
			/*
			 * executeUpdate 专门执行增删改，返回值为int=受影响的行数
			 * executeQuery 专门执行查询，返回值为ResultSet对象
			 */
			//执行sql语句
			rs = st.executeQuery(sql);
			//判断是否插入成功
			while(rs.next()) {
				System.out.println("userId：" + rs.getInt("userId"));
				System.out.println("userName：" + rs.getString("userName"));
				System.out.println("password：" + rs.getString("password"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭连接
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//查询单个操作
	public void selectOneUser() {
		try {
			//获取连接对象
			conn = JDBCUtils.getConnection();
			//获取传递sql对象
			st = conn.createStatement();
			//编写sql语句
			String sql = "select userName from user where userId = 2";
			/*
			 * executeUpdate 专门执行增删改，返回值为int=受影响的行数
			 * executeQuery 专门执行查询，返回值为ResultSet对象
			 */
			//执行sql语句
			rs = st.executeQuery(sql);
			//判断是否插入成功
			if(rs.next()) {
				System.out.println("userName：" + rs.getString("userName"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭连接
			JDBCUtils.close(conn,st,rs);
		}
	}
}
