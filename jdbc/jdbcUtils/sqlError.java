package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * sqlע������
 * 	ͨ������ļ�����ƴ���ַ��������sql��·
 * 	��·���������һ��Ϊ�٣�����治�ü����ˡ��������һ��Ϊ�棬�����Ҳ��������
 * where 1=1 or username = ... ����ԶΪ��
 * @author Andrew
 *
 */

public class sqlError {
	public static void main(String[] args) {
		//select * from user where username='' or '1=1'
		//login("'or' 1=1","3265");//sqlע��
		login("'or' 1=1","'or' 1=1");//sqlע��
		//login("����","3265");
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
