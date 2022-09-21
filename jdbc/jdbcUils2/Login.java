package jdbc.jdbcUils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * дһ������̨��¼����
 * 1.��ʾ�û������û���������
 * 2.��ȡ�û����������
 * 3.jdbc���ݿ����Ӽ����������ݿ�
 * 4.��ѯ���ݲ���֤��¼
 * 5.��ʾ��¼�ɹ���ʧ��
 * ��ʾ
 * �������������˼��
 * 1.����login����ʵ�ֻ�ȡ����
 * 2.����check����ʵ�ֵ�¼��֤
 * 3.main�������Գ���
 * @author Andrew
 *
 */
public class Login {
	
	//�������Ӷ���
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void login() {
		System.out.println("�������û�����");
		Scanner scan = new Scanner(System.in);
		String username = scan.next();
		System.out.println("���������룺");
		String password = scan.next();
		boolean res = check(username, password);
		if(res) {
			System.out.println("��¼�ɹ�");
		}else {
			System.out.println("��¼ʧ��");
		}
	}
	
	//��֤�Ƿ��¼�ɹ�
	public boolean check(String username, String password) {
		try {
			//��ȡ���Ӷ���
			conn = JDBCUtils.getConnection();
			String sql = "select username,password from user where userName=? and password=?";
			//��ȡ����sql��statement����
			ps = conn.prepareStatement(sql);
			//���ò�ѯ����
			ps.setString(1, username);
			ps.setString(2, password);
			//ִ�в�ѯ�����ؽ����
			rs = ps.executeQuery();
			//�жϷ���1
			if(rs.next()) {//�ж��Ƿ�����һ��Ԫ��
				return true;
			}else {
				return false;
			}
			//�жϷ���2 -- �д���
			/*while(rs.next()) {
				if(username == rs.getString("username") && password == rs.getString("password")) {
					return true;
				}else {
					return false;
				}
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		new Login().login();
	}
}
