package jdbc.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

/**
 * 用来实现shootgame排行榜功能
 * 1.将每一局的分数添加进数据库（用户名无需添加，默认自增即可）
 * 2.显示分数
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
	public void insertScoreV2(String myName, int myScore) {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into sgScore(userName,score)"+"values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, myName);
			ps.setInt(2, myScore);
			int num = ps.executeUpdate();
			/*if(num > 0) {
				System.out.println("插入成功");
			}else {
				System.out.println("插入失败");
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
	public void updateScore() {
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
	public void selectScore() {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from  sgScore ORDER BY score DESC";
			ps = conn.prepareStatement(sql);
			//setInt(参数索引,参数值);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.print("用户id:" + rs.getInt("userId"));
				System.out.print(" 用户名：" + rs.getString("userName"));
				System.out.println(" 分数：" + rs.getInt("score"));
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
			//setInt(参数索引,参数值);
			rs = ps.executeQuery();
			for (int i = 0; i < results.length; i++) {
				if(rs.next()) {
					results[i] = "第"+(i+1)+"名："+rs.getString("username")+"："+rs.getInt("score");
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
