package jdbc.jdbcUils2;
/**
 * ���ݿ����ӵĵڶ��ַ�ʽ ���ݿ����ӳ�
 * 	�û�ÿ��������Ҫ�����ݿ��ȡ���ӣ������ݿⴴ������ͨ����Ҫ������Խϴ����Դ��
 * 	����ʱ�䳤������������ݿ�������ڴ������������˷�����Դ��
 * --���ݿ����ӳ�
 * 	-������䡢�����ͷ����ݿ����ӣ�����Ӧ�ó����ظ�ʹ��һ�����е����ݿ����ӣ��������½�һ�����ӡ�
 * 
 * 	1.DBCP:û���Զ����տ��е����ӵĹ���
 * 	2.c3p0�������Զ�����
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
	
	//��ȡ���ӳ�
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	//�ر����� �ͷ���Դ
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
				System.out.println("����ʧ��");
			}else {
				System.out.println("���ӳɹ�!");
			}
		}
}
