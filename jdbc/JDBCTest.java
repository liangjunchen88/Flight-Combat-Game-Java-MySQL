package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;


/**
 * JDBC
 * 	Java Database Connectivity
 * java.sql��javax.sql��֧��
 * ����mysql-connector-java-5.1.40-bin.jar//mysql����jar������ֱ������
 * @author Andrew
 *
 */

public class JDBCTest {
	
	@Test //��Ԫ����--����ֱ�����и÷���
	public void JDBCFirstDemo() throws Exception{
		//Ҫ���ӵ����ݿ�URL��ַ
		String url = "jdbc:mysql://localhost:3306/xjtu";
		//�����û���
		String username = "root";
		//��������
		String password = "root";
		
		//1.��������
		Class.forName("com.mysql.cj.jdbc.Driver");    
	    //2.��ȡ���ݿ�����
	    //mysql8.0�ü���ʱ��ʱ��(?serverTimezone=UTC) �ƺ�����Ҳ����
	    //�������ݿ����ʡ��IP��ַ�Ͷ˿ں�
	    Connection conn = DriverManager.getConnection(url,username, password);
		if(conn == null) {
			System.out.println("����ʧ��");
		}else {
			System.out.println("���ӳɹ�");
		}
		//3.��ȡ���������ݿⷢ��sql��statement����
		Statement st = conn.createStatement();
		//4.��дsql���
		
		 String sql = "select * from user";
		//����1 String sql = "select * from user";
		//����2 String sql = "SELECT NAME,stuScore,pwd,gradeName FROM student s INNER JOIN grade g ON s.`gradeId` = g.`gradeID` INNER JOIN score c ON s.`scoreId` = c.`scoreId`ORDER BY stuScore DESC";
		//��������¼81
		
		//5.�����ݿⷢ��sql������ȡ�������
		ResultSet rs = st.executeQuery(sql);
		//6.�������ݿ�
		while(rs.next()) {
			System.out.println("userId��" + rs.getInt("userId"));
			System.out.println("userName��" + rs.getString("userName"));
			System.out.println("password��" + rs.getString("password"));
			/* ����1
			System.out.println("userId��" + rs.getInt("userId"));
			System.out.println("userName��" + rs.getString("userName"));
			System.out.println("password��" + rs.getString("password"));*/
			/* ����2
			System.out.println("name��" + rs.getString("name"));
			System.out.println("stuScore��" + rs.getInt("stuScore"));
			System.out.println("password��" + rs.getString("pwd"));
			System.out.println("gradeName��" + rs.getString("gradeName"));*/
			
		}
		//7.�ر����� �ͷ���Դ
		rs.close();
		st.close();
		conn.close();
	}
/*	���������в鿴������ֱ�Ӳ鿴���󱨸�
 * public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/xjtu?serverTimezone=UTC";
		//�����û���
		String username = "root";
		//��������
		String password = "root";
		
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");    
			    //��ȡ���ݿ�����
			    //mysql8.0�ü���ʱ��ʱ��(?serverTimezone=UTC)
			    //�������ݿ����ʡ��IP��ַ�Ͷ˿ں�
			    Connection conn = DriverManager.getConnection(url,username, password);
			if(conn == null) {
				System.out.println("����ʧ��");
			}else {
				System.out.println("���ӳɹ�");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}


