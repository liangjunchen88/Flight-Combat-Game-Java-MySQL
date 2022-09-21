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
 * Utils ���ݿ⹤����
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
			//��ȡdb.properties�ļ������ݿ�������Ϣ
			InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc\\jdbcUtils\\db.properties");
			//����properties����
			//��ȡ���ö���
			Properties prop = new Properties();
			//���ö������Input������
			prop.load(in);
			//��ȡ���ݿ���������
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			//�������ݿ�����
			Class.forName(driver);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//��ȡ���Ӷ���
	public static Connection getConnection() throws Exception{
		return DriverManager.getConnection(url,username,password);
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
	public void testJDBC() throws Exception{
		if(JDBCUtils.getConnection() == null) {
			System.out.println("����ʧ��");
		}else {
			System.out.println("���ӳɹ�!");
		}
	}
}
