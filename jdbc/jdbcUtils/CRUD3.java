package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

/**
 * ����ʵ��shootgame���а���
 * 1.��ÿһ�ֵķ�����ӽ����ݿ⣨�û���������ӣ�Ĭ���������ɣ�
 * 2.��ʾ����
 * @author Andrew
 *
 */
public class CRUD3 {
	PreparedStatement ps = null;
	Connection conn = null;
	ResultSet rs = null;
	public static void main(String[] args) {
		CRUD3 c3 = new CRUD3();
		c3.insertScore(3265);
	}
	
	@Test
	public void insertScore(int myScore) {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into sgScore(score)"+"values(?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, myScore);
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
	public void insertScoreV2(String myName, int myScore) {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into sgScore(userName,score)"+"values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, myName);
			ps.setInt(2, myScore);
			int num = ps.executeUpdate();
			/*if(num > 0) {
				System.out.println("����ɹ�");
			}else {
				System.out.println("����ʧ��");
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteScore() {
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
	public void updateScore() {
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
	public void selectScore() {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from  sgScore ORDER BY score DESC";
			ps = conn.prepareStatement(sql);
			//setInt(��������,����ֵ);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.print("�û�id:" + rs.getInt("userId"));
				System.out.print(" �û�����" + rs.getString("userName"));
				System.out.println(" ������" + rs.getInt("score"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
	}
	
	@Test
	public String[] selectScoreV2() {
		String[] results = new String[1024];
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from  sgScore ORDER BY score DESC";
			ps = conn.prepareStatement(sql);
			//setInt(��������,����ֵ);
			rs = ps.executeQuery();
			for (int i = 0; i < results.length; i++) {
				if(rs.next()) {
					results[i] = "��"+(i+1)+"����"+rs.getString("username")+"��"+rs.getInt("score");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		return results;
	}
}
