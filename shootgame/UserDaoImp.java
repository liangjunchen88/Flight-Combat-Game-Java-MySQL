package com.tedu.shootgame.day03_shootgame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.jdbcUtils.JDBCUtils;

public class UserDaoImp implements UserDao{
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public void save(String username, int score){
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into sgscore(username,score) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, score);
			int res = ps.executeUpdate();
			if(res>0) {
				System.out.println("插入成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, ps, rs);
		}
	}
	
	@Override
	public String[] getScoreAndName() {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, ps, rs);
		}
		return results;
	}
}
