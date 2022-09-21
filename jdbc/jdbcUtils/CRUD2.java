package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

/**
 * PreparedStatement
 * 	��Statement��һ�����࣬����ʵ�����󣬿���ͨ�����÷�����ã����Ա���sqlע������
 * 	Statementȱ�㣺Ƶ��ʹ���ݿ����sql��䣬����������ݻ������������
 * 	PreparedStatement���Զ�sql������Ԥ���룬�Ӷ�������ݿ��ִ��Ч�ʣ�������������sql�еĲ���������ռλ������ʽ�滻����sql���ı�д
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
			ps.setString(1, "������");
			ps.setString(2, "123456789");
			int num = ps.executeUpdate();
			if(num > 0) {
				System.out.println("����ɹ�");
			}else {
				System.out.println("����ʧ��");
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
			//setInt(��������,����ֵ)
			ps.setInt(1, 18);
			int num = ps.executeUpdate();
			if(num > 0) {
				System.out.println("ɾ���ɹ�");
			} else {
				System.out.println("ɾ��ʧ��");
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
			ps.setString(1, "������");
			ps.setString(2, "3265");
			ps.setInt(3, 15);
			int num = ps.executeUpdate();
			if(num > 0) {
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ��");
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
			//setInt(��������,����ֵ)
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
