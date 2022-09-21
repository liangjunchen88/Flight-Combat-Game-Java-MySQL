package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
 * ���ݿ���ɾ��Ĳ���
 * @author Andrew
 *
 */

public class CRUD {
	//�������Ӷ���
	Connection conn = null;
	//����statement����
	Statement st = null;
	//������������
	ResultSet rs = null;
	
	@Test//��Ӳ���
	public void insertUser() {
		try {
			//��ȡ���Ӷ���
			conn = JDBCUtils.getConnection();
			//��ȡ����sql����
			st = conn.createStatement();
			//��дsql���
			String sql = "insert into user(username,password) values('����','3265'),('����','3265')";
			/*
			 * executeUpdate ר��ִ����ɾ�ģ�����ֵΪint=��Ӱ�������
			 * executeQuery ר��ִ�в�ѯ������ֵΪResultSet����
			 */
			//ִ��sql���
			int num = st.executeUpdate(sql);
			//�ж��Ƿ����ɹ�
			if(num > 0) {
				System.out.println("����ɹ�");
			}else {
				System.out.println("����ʧ��");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//ɾ������
	public void deltetUser() {
		try {
			//��ȡ���Ӷ���
			conn = JDBCUtils.getConnection();
			//��ȡ����sql����
			st = conn.createStatement();
			//��дsql���
			String sql = "delete from user where userName='����'";
			/*
			 * executeUpdate ר��ִ����ɾ�ģ�����ֵΪint=��Ӱ�������
			 * executeQuery ר��ִ�в�ѯ������ֵΪResultSet����
			 */
			//ִ��sql���
			int num = st.executeUpdate(sql);
			//�ж��Ƿ����ɹ�
			if(num > 0) {
				System.out.println("ɾ���ɹ�");
			}else {
				System.out.println("ɾ��ʧ��");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//�޸Ĳ���
	public void updatetUser() {
		try {
			//��ȡ���Ӷ���
			conn = JDBCUtils.getConnection();
			//��ȡ����sql����
			st = conn.createStatement();
			//��дsql���
			String sql = "update user set password = 4321 where userName='����'";
			/*
			 * executeUpdate ר��ִ����ɾ�ģ�����ֵΪint=��Ӱ�������
			 * executeQuery ר��ִ�в�ѯ������ֵΪResultSet����
			 */
			//ִ��sql���
			int num = st.executeUpdate(sql);
			//�ж��Ƿ����ɹ�
			if(num > 0) {
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ��");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//��ѯ���в���
	public void selectUser() {
		try {
			//��ȡ���Ӷ���
			conn = JDBCUtils.getConnection();
			//��ȡ����sql����
			st = conn.createStatement();
			//��дsql���
			String sql = "select * from user";
			/*
			 * executeUpdate ר��ִ����ɾ�ģ�����ֵΪint=��Ӱ�������
			 * executeQuery ר��ִ�в�ѯ������ֵΪResultSet����
			 */
			//ִ��sql���
			rs = st.executeQuery(sql);
			//�ж��Ƿ����ɹ�
			while(rs.next()) {
				System.out.println("userId��" + rs.getInt("userId"));
				System.out.println("userName��" + rs.getString("userName"));
				System.out.println("password��" + rs.getString("password"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
			JDBCUtils.close(conn,st,rs);
		}
	}
	
	@Test//��ѯ��������
	public void selectOneUser() {
		try {
			//��ȡ���Ӷ���
			conn = JDBCUtils.getConnection();
			//��ȡ����sql����
			st = conn.createStatement();
			//��дsql���
			String sql = "select userName from user where userId = 2";
			/*
			 * executeUpdate ר��ִ����ɾ�ģ�����ֵΪint=��Ӱ�������
			 * executeQuery ר��ִ�в�ѯ������ֵΪResultSet����
			 */
			//ִ��sql���
			rs = st.executeQuery(sql);
			//�ж��Ƿ����ɹ�
			if(rs.next()) {
				System.out.println("userName��" + rs.getString("userName"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
			JDBCUtils.close(conn,st,rs);
		}
	}
}
