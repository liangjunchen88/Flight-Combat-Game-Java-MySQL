package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * sql注入问题
 * 	通过巧妙的技巧来拼接字符串，造成sql短路
 * 	短路：与运算第一个为假，则后面不用计算了。或运算第一个为真，则后面也不用算了
 * where 1=1 or username = ... 则永远为真
 * @author Andrew
 *
 */

public class sqlError {
	public static void main(String[] args) {
		//select * from user where username='' or '1=1'
		//login("'or' 1=1","3265");//sql注入
		login("'or' 1=1","'or' 1=1");//sql注入
		//login("张三","3265");
	}
	
	public static void login(String username, String password) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			//String sql = "select * from user where userName = '" +username+ "' and password ='" +password+"'";
			String sql = "select * from user where userName=? and password=?";
			//st = conn.createStatement();
			st.getConnection().prepareStatement(sql);
			
			rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("userName"));
				System.out.println(rs.getString("password"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, st, rs);
		}
	}
}
