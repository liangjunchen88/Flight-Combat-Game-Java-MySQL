package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

/**
 * PreparedStatement
 * 	是Statement的一个子类，它的实例对象，可以通过调用方法获得，可以避免sql注入问题
 * 	Statement缺点：频繁使数据库编译sql语句，可能造成数据缓冲区的溢出。
 * 	PreparedStatement可以对sql语句进行预编译，从而提高数据库的执行效率，并且它对象与sql中的参数，采用占位符的形式替换，简化sql语句的编写
 * @author Andrew
 *
 */
public class CRUD2 {
	PreparedStatement ps = null;
	Connection conn = null;
	ResultSet rs = null;
	
	@Test
	public void insertUser() {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into user(userName,password)"+"values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "张麻子");
			ps.setString(2, "123456789");
			int num = ps.executeUpdate();
			if(num > 0) {
				System.out.println("插入成功");
			}else {
				System.out.println("插入失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteUser() {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "delete from user where userId=?";
			ps = conn.prepareStatement(sql);
			//setInt(参数索引,参数值)
			ps.setInt(1, 18);
			int num = ps.executeUpdate();
			if(num > 0) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
	}
	
	@Test
	public void updateUser() {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "update user set userName=?,password=?"+"where userId=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "李麻子");
			ps.setString(2, "3265");
			ps.setInt(3, 15);
			int num = ps.executeUpdate();
			if(num > 0) {
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectUser() {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from  user where userId=?";
			ps = conn.prepareStatement(sql);
			//setInt(参数索引,参数值)
			ps.setInt(1, 15);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("password"));
				System.out.println(rs.getString("username"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		
	}
}
